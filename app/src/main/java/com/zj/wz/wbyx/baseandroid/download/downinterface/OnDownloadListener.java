package com.zj.wz.wbyx.baseandroid.download.downinterface;


import com.zj.wz.wbyx.baseandroid.download.DownloadException;

/**
 * FileName: OnDownloadListener
 * Author: 曹伟
 * Date: 2019/9/16 15:09
 * Description:
 */

public interface OnDownloadListener {

    void onDownloadProgress(long finished, long length);

    void onDownloadPaused();

    void onDownloadCanceled();

    void onDownloadCompleted();

    void onDownloadFailed(DownloadException de);
}
