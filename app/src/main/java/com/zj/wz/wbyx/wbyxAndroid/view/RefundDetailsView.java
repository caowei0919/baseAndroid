package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ReasonBean;

import java.util.List;

/**
 * FileName: RefundDetailsView
 * Author: 曹伟
 * Date: 2019/11/20 17:17
 * Description:申请退款V层
 */

public interface RefundDetailsView extends MvpView {
    void getOrderDetailSuccess(OrderDetailBean response);

    void getReasonSuccess(List<ReasonBean> reasonBeans);

    void getRefundSuccess();

}
