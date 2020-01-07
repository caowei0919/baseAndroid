package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;

/**
 * FileName: OrderDetailView
 * Author: 曹伟
 * Date: 2019/11/20 14:28
 * Description:订单详情V层
 */

public interface OrderDetailView extends MvpView {
    /**
     * 获取订单详情成功
     * @param response
     */
    void getOrderDetailSuccess(OrderDetailBean response);

    void getOnGoodsSuccess(HomeGoodsOfAllListBean response);
}
