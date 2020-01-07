package com.zj.wz.wbyx.baseandroid.room;

import android.content.Context;
import android.support.annotation.NonNull;


import com.zj.wz.wbyx.baseandroid.utils.ThreadUtils;

import java.util.List;


/**
 * FileName: ThreadInfoDataSource
 * Author: 曹伟
 * Date: 2019/9/16 15:21
 * Description:
 */

public class ThreadInfoDataSource {

    private static volatile ThreadInfoDataSource INSTANCE;

    private ThreadInfoDao mThreadInfoDao;

    ThreadInfoDataSource(ThreadInfoDao threadInfoDao) {
        mThreadInfoDao = threadInfoDao;
    }

    public static ThreadInfoDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            synchronized (ThreadInfoDataSource.class) {
                if (INSTANCE == null) {
                    RoomDataBase database = RoomDataBase.getInstance(context);
                    INSTANCE = new ThreadInfoDataSource(database.threadInfoDao());
                }
            }
        }
        return INSTANCE;
    }

    public List<ThreadInfo> getThreadInfo(String tag) {
        return mThreadInfoDao.loadAll(tag);
    }

    public void insertOrUpdateDebugInfo(ThreadInfo threadInfo) {
        mThreadInfoDao.insertThreadInfo(threadInfo);
    }

    public void deleteThreadInfo(String tag) {
        // android room Cannot access database on the main thread since it may
        // potentially lock the UI for a long period of time.
        ThreadUtils.newSingleThreadExecutor(DebugInfo.class.getSimpleName())
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        mThreadInfoDao.deleteThreadInfo(tag);
                    }
                });
    }
}
