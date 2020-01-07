package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;

/**
 * FileName: WXEntryView
 * Author: 曹伟
 * Date: 2019/11/25 13:37
 * Description:
 */

public interface BindPhoneView extends MvpView {
    void sendMessageSuccess();

    void sendMessageFailed();

    void LoginSuccess();

}
