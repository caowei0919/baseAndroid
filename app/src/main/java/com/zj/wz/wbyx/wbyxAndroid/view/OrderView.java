package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderListBean;

/**
 * FileName: OrderView
 * Author: 曹伟
 * Date: 2019/10/22 11:05
 * Description: 订单v层
 */

public interface OrderView extends MvpView {
    /**
     * 获取订单列表成功
     * @param response
     */
    void getOrderListSuccess(OrderListBean response);
}
