package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.InviteCheckBean;

/**
 * FileName: InviteView
 * Author: 曹伟
 * Date: 2019/9/30 17:12
 * Description: 绑定邀请人V层
 */

public interface InviteView extends MvpView {
    /**
     * 检验邀请码成功
     * @param inviteCheckBeanBaseBean
     */
    void checkInviteSuccess(InviteCheckBean inviteCheckBeanBaseBean);

    /**
     * 绑定成功
     */
    void BindSuccess();
}
