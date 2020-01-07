package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.ApplyBuildBean;

/**
 * FileName: ApplyForOwnerView
 * Author: 曹伟
 * Date: 2019/9/25 15:49
 * Description: 申请店长View层
 */

public interface ApplyForOwnerView extends MvpView {
    /**
     * 获取申请楼长状态信息成功
     * @param response
     */
    void applyBuildStateSuccess(ApplyBuildBean response);

    /**
     * 提交楼长信息成功
     */
    void applyBuildSuccess();
}
