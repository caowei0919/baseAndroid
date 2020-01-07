package com.zj.wz.wbyx.baseandroid.download.downinterfaceimpl;

import com.zj.wz.wbyx.baseandroid.download.DownloadConfiguration;
import com.zj.wz.wbyx.baseandroid.download.DownloadException;
import com.zj.wz.wbyx.baseandroid.download.DownloadInfo;
import com.zj.wz.wbyx.baseandroid.download.DownloadManager;
import com.zj.wz.wbyx.baseandroid.download.DownloadStatus;
import com.zj.wz.wbyx.baseandroid.download.downinterface.ConnectTask;
import com.zj.wz.wbyx.baseandroid.download.downinterface.DownLoadCallBack;
import com.zj.wz.wbyx.baseandroid.download.downinterface.DownloadStub;
import com.zj.wz.wbyx.baseandroid.download.downinterface.DownloadTask;
import com.zj.wz.wbyx.baseandroid.download.downinterface.OnConnectListener;
import com.zj.wz.wbyx.baseandroid.download.downinterface.OnDownloadListener;
import com.zj.wz.wbyx.baseandroid.room.ThreadInfo;
import com.zj.wz.wbyx.baseandroid.room.ThreadInfoDataSource;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;


/**
 * FileName: DownloadStubImpl
 * Author: 曹伟
 * Date: 2019/9/16 15:05
 * Description:
 */

public class DownloadStubImpl implements DownloadStub, OnConnectListener, OnDownloadListener {

    private Executor mExecutor;

    private String mUri;
    private File mFolder;
    private String mFilename;
    private String mTag;

    private DownloadConfiguration mConfig;

    private int mStatus;

    private DownloadInfo mDownloadInfo;

    private ConnectTask mConnectTask;

    private List<DownloadTask> mDownloadTasks;

    private DownLoadCallBack mDownLoadCallBack;

    public DownloadStubImpl(String uri, File folder, String filename, DownLoadCallBack downLoadCallBack, Executor executor, String key, DownloadConfiguration config) {
        mUri = uri;
        mFolder = folder;
        mFilename = filename;
        mDownLoadCallBack = downLoadCallBack;
        mExecutor = executor;
        mTag = key;
        mConfig = config;

        init();
    }

    private void init() {
        File destFile = new File(mFolder, mFilename);
        if (!destFile.exists() || !destFile.isFile()) {
            if (!FileUtils.isExistingFolder(mFolder)) {
                try {
                    FileUtils.mkdir(mFolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        mDownloadInfo = new DownloadInfo(mFilename, mUri, mFolder);
        mDownloadTasks = new LinkedList<>();
    }

    @Override
    public void start() {
        mStatus = DownloadStatus.STATUS_STARTED;
        connect();
    }

    private void connect() {
        mConnectTask = new ConnectTaskImpl(mUri, this);
        mExecutor.execute(mConnectTask);
    }

    @Override
    public void pause() {
        if (mConnectTask != null) {
            mConnectTask.cancel();
        }
        for (DownloadTask task : mDownloadTasks) {
            task.pause();
        }
    }

    @Override
    public synchronized void cancel() {
        if (mConnectTask != null) {
            mConnectTask.cancel();
        }
        for (DownloadTask task : mDownloadTasks) {
            task.cancel();
        }
    }

    @Override
    public boolean isRunning() {
        return mStatus == DownloadStatus.STATUS_STARTED || mStatus == DownloadStatus.STATUS_CONNECTING || mStatus == DownloadStatus.STATUS_CONNECTED || mStatus == DownloadStatus.STATUS_PROGRESS;
    }

    @Override
    public void onConnecting() {
        mStatus = DownloadStatus.STATUS_CONNECTING;
    }

    @Override
    public void onConnected(final long time, final long length, final boolean isAcceptRanges) {
        mStatus = DownloadStatus.STATUS_CONNECTED;
        DownloadManager.getInstance().getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mDownLoadCallBack.onConnected(length, isAcceptRanges);
            }
        });
        mDownloadInfo.setAcceptRanges(isAcceptRanges);
        mDownloadInfo.setLength(length);
        download(length, isAcceptRanges);
    }

    @Override
    public void onConnectCanceled() {
        mStatus = DownloadStatus.STATUS_CANCELED;
        DownloadManager.getInstance().deleteKey(mTag);
    }

    @Override
    public void onConnectFailed(final DownloadException de) {
        mStatus = DownloadStatus.STATUS_FAILED;
        DownloadManager.getInstance().getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mDownLoadCallBack.onFailed(de);
            }
        });
        DownloadManager.getInstance().deleteKey(mTag);
    }

    @Override
    public void onDownloadProgress(final long finished, final long length) {
        mStatus = DownloadStatus.STATUS_PROGRESS;
        final int percent = (int) (finished * 100 / length);
        DownloadManager.getInstance().getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mDownLoadCallBack.onProgress(finished, length, percent);
            }
        });
    }

    @Override
    public void onDownloadPaused() {
        if (isAllPaused()) {
            mStatus = DownloadStatus.STATUS_PAUSED;
            dealwithPaused();
            return;
        }

        if (isOneErrored()) {
            dealwithFailed(new DownloadException("download paused error"));
        }
    }

    @Override
    public synchronized void onDownloadFailed(final DownloadException de) {
        // download fail occur,cancel all task
        for (DownloadTask task : mDownloadTasks) {
            if (task.isDownloading()) {
                task.cancel();
            }
        }

        if (isOneErrored()) {
            dealwithFailed(de);
        }
    }

    @Override
    public synchronized void onDownloadCanceled() {
        if (isAllCanceled()) {
            dealwithCanceled(new DownloadException("download canceled"));
            return;
        }

        if (isOneErrored()) {
            dealwithFailed(new DownloadException("download canceled of failed"));
        }
    }

    @Override
    public synchronized void onDownloadCompleted() {
        if (isAllComplete()) {
            dealwithCompleted();
            return;
        }

        if (isAllPaused()) {
            dealwithPaused();
            return;
        }

        if (isOneErrored()) {
            dealwithFailed(new DownloadException("partial complet failed"));
        }

    }

    private synchronized void download(long length, boolean acceptRanges) {
        initDownloadTasks(length, acceptRanges);

        for (DownloadTask downloadTask : mDownloadTasks) {
            mExecutor.execute(downloadTask);
        }
    }

    private void initDownloadTasks(final long length, boolean acceptRanges) {
        mDownloadTasks.clear();
        if (acceptRanges) {
            List<ThreadInfo> threadInfos = ThreadInfoDataSource.getInstance(AndroidUtils.getContext())
                    .getThreadInfo(mTag);
            if (threadInfos.isEmpty() || threadInfos.size() != mConfig.getThreadNum()) {
                deleteFromDB();
                threadInfos.clear();
                multiThreadRangerInit(length, threadInfos);
            } else {
                File destFile = new File(mFolder, mFilename);
                if (!destFile.exists() || !destFile.isFile()) {
                    if (!FileUtils.isExistingFolder(mFolder)) {
                        try {
                            FileUtils.mkdir(mFolder);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    deleteFromDB();
                    threadInfos.clear();
                    multiThreadRangerInit(length, threadInfos);
                }
            }
            int finished = 0;
            for (ThreadInfo threadInfo : threadInfos) {
                finished += threadInfo.getFinished();
            }
            mDownloadInfo.setFinished(finished);
            for (ThreadInfo info : threadInfos) {
                mDownloadTasks.add(new MultiDownloadTask(mDownloadInfo, info, DownloadStubImpl.this));
            }
        } else {
            ThreadInfo info = getSingleThreadInfo();
            File destFile = new File(mFolder, mFilename);
            if (destFile.exists() && destFile.isFile()) {
                if (mDownloadInfo.getFinished() != 0 && mDownloadInfo.getLength() != 0 && mDownloadInfo
                        .getFinished() == mDownloadInfo.getLength()) {
                    //down complet
                    dealwithCompleted();
                    return;
                } else {
                    try {
                        FileUtils.delete(destFile.getAbsoluteFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            // not support mult thread and reset finished
            mDownloadInfo.setFinished(0L);
            mDownloadTasks.add(new SingleDownloadTask(mDownloadInfo, info, this));
        }
    }

    private void multiThreadRangerInit(long length, List<ThreadInfo> threadInfos) {
        final int threadNum = mConfig.getThreadNum();
        for (int i = 0; i < threadNum; i++) {
            final long average = length / threadNum;
            final long start = average * i;
            final long end;
            if (i == threadNum - 1) {
                end = length;
            } else {
                end = start + average - 1;
            }
            ThreadInfo threadInfo = new ThreadInfo();
            threadInfo.setId(i);
            threadInfo.setUri(mUri);
            threadInfo.setTag(mTag);
            threadInfo.setStartoffset(start);
            threadInfo.setEndoffset(end);
            threadInfo.setFinished(0L);
            threadInfos.add(threadInfo);
        }
    }

    private ThreadInfo getSingleThreadInfo() {
        ThreadInfo threadInfo = new ThreadInfo();
        threadInfo.setUri(mUri);
        threadInfo.setTag(mTag);
        threadInfo.setStartoffset(0L);
        threadInfo.setEndoffset(0L);
        threadInfo.setFinished(0L);
        return threadInfo;
    }

    private boolean isAllComplete() {
        boolean allFinished = true;
        for (DownloadTask task : mDownloadTasks) {
            if (!task.isComplete()) {
                allFinished = false;
                break;
            }
        }
        return allFinished;
    }

    private boolean isAllPaused() {
        boolean allPaused = true;
        for (DownloadTask task : mDownloadTasks) {
            if (task.isDownloading() || task.isFailed() || task.isCanceled()) {
                allPaused = false;
                break;
            }
        }
        return allPaused;
    }

    private boolean isAllCanceled() {
        boolean allCanceled = true;
        for (DownloadTask task : mDownloadTasks) {
            if (task.isDownloading()) {
                allCanceled = false;
                break;
            }
        }
        return allCanceled;
    }

    private boolean isOneErrored() {
        boolean isHasErrored = false;
        for (DownloadTask task : mDownloadTasks) {
            if (task.isDownloading()) {
                return false;
            }
        }

        for (DownloadTask task : mDownloadTasks) {
            if (task.isFailed() || task.isCanceled()) {
                isHasErrored = true;
                break;
            }
        }

        return isHasErrored;
    }


    private void dealwithPaused() {
        mStatus = DownloadStatus.STATUS_PAUSED;
        DownloadManager.getInstance().getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mDownLoadCallBack.onPaused(new File(mDownloadInfo.getDir(), mDownloadInfo.getName()));
            }
        });
    }

    private void dealwithCanceled(final DownloadException de) {
        mStatus = DownloadStatus.STATUS_CANCELED;
        deleteFromDB();
        DownloadManager.getInstance().deleteKey(mTag);
        if (new File(mDownloadInfo.getDir(), mDownloadInfo.getName()).exists()) {
            try {
                FileUtils.delete(new File(mDownloadInfo.getDir(), mDownloadInfo.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dealwithFailed(final DownloadException de) {
        mStatus = DownloadStatus.STATUS_FAILED;
        deleteFromDB();
        DownloadManager.getInstance().deleteKey(mTag);
        if (new File(mDownloadInfo.getDir(), mDownloadInfo.getName()).exists()) {
            try {
                FileUtils.delete(new File(mDownloadInfo.getDir(), mDownloadInfo.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DownloadManager.getInstance().getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mDownLoadCallBack.onFailed(de);
            }
        });
    }

    private void dealwithCompleted() {
        mStatus = DownloadStatus.STATUS_COMPLETED;
        DownloadManager.getInstance().deleteKey(mTag);
        DownloadManager.getInstance().getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mDownLoadCallBack.onCompleted(new File(mDownloadInfo.getDir(), mDownloadInfo
                        .getName()));
            }
        });
    }


    private void deleteFromDB() {
        ThreadInfoDataSource.getInstance(AndroidUtils.getContext())
                .deleteThreadInfo(mTag);
    }
}
