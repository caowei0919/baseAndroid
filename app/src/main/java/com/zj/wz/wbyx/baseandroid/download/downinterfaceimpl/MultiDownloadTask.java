package com.zj.wz.wbyx.baseandroid.download.downinterfaceimpl;

import com.zj.wz.wbyx.baseandroid.download.DownloadInfo;
import com.zj.wz.wbyx.baseandroid.download.downinterface.OnDownloadListener;
import com.zj.wz.wbyx.baseandroid.room.ThreadInfo;
import com.zj.wz.wbyx.baseandroid.room.ThreadInfoDataSource;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;


/**
 * FileName: MultiDownloadTask
 * Author: 曹伟
 * Date: 2019/9/16 15:35
 * Description:
 */

public class MultiDownloadTask extends DownloadTaskImpl {

    public MultiDownloadTask(DownloadInfo downloadInfo, ThreadInfo threadInfo, OnDownloadListener listener) {
        super(downloadInfo, threadInfo, listener);
    }

    @Override
    protected void insertIntoDB(ThreadInfo info) {
        insertOrUpdateDb(info);
    }

    @Override
    protected int getResponseCode() {
        return HttpURLConnection.HTTP_PARTIAL;
    }

    @Override
    protected void updateDB(ThreadInfo info) {
        insertOrUpdateDb(info);
    }

    @Override
    protected Map<String, String> getHttpHeaders(ThreadInfo info) {
        Map<String, String> headers = new HashMap<String, String>();
        long start = info.getStartoffset() + info.getFinished();
        long end = info.getEndoffset();
        headers.put("Range", "bytes=" + start + "-" + end);
        return headers;
    }

    @Override
    protected RandomAccessFile getFile(File dir, String name, long offset) throws
            IOException {
        File file = new File(dir, name);
        RandomAccessFile raf = new RandomAccessFile(file, "rwd");
        raf.seek(offset);
        return raf;
    }

    protected void insertOrUpdateDb(ThreadInfo info) {
        ThreadInfoDataSource.getInstance(AndroidUtils.getContext())
                .insertOrUpdateDebugInfo(info);
    }
}
