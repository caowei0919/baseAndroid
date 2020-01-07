package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.VersionBean;

/**
 * FileName: UpdateVersionView
 * Author: 曹伟
 * Date: 2019/10/11 9:41
 * Description:下载更新V层
 */

public interface UpdateVersionView extends MvpView {

    /**
     * 检查版本
     * @param versionBean
     */
    void checkVersionSuccess(VersionBean versionBean);

    /**
     * 下载进度
     * @param progress
     */
    void downloadProgress(int progress);

    /**
     * 下载完成
     */
    void downloadCompleted();

    /**
     * 下载失败
     */
    void downloadFailed();
}
