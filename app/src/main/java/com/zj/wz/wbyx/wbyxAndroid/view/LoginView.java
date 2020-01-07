package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.NeedKeyBean;

/**
 * FileName: LoginView
 * Author: 曹伟
 * Date: 2019/9/17 19:21
 * Description: 登录页面V层
 */

public interface LoginView extends MvpView {
    /**
     * 验证码发送成功
     */
    void sendMessageSuccess() ;

    /**
     * 登录成功
     */
    void LoginSuccess();

    /**
     * 验证码发送失败
     */
    void sendMessageFailed();

    /**
     * 微信授权后需要绑定手机号
     * @param key
     */
    void needBindPhone(String key);

}
