package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefundDetailsBean;

/**
 * FileName: ConfirmRefundDetailsView
 * Author: 曹伟
 * Date: 2019/11/20 17:20
 * Description:退款详情V层
 */

public interface ConfirmRefundDetailsView extends MvpView {
    /**
     * 获取退款详情成功
     * @param response
     */
    void getRefundDetailsSuccess(RefundDetailsBean response);

    /**
     * 取消退款
     */
    void cancelRefundSuccess();
}
