package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.VersionBean;

/**
 * FileName: SettingView
 * Author: 曹伟
 * Date: 2019/10/10 18:36
 * Description:设置View层
 */

public interface SettingView extends MvpView {
    /**
     * 获取服务器版本信息成功
     * @param versionBean
     */
    void checkVersionSuccess(VersionBean versionBean);
}
