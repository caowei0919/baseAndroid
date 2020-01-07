package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

/**
 * FileName: RefundCheckBean
 * Author: 曹伟
 * Date: 2019/9/19 16:08
 * Description:
 */
@Keep
public class RefundCheckBean {
    private OrderDetailBean.OrderBean.GoodsBean bean ;
    private boolean isCheck = false ;

    public RefundCheckBean(OrderDetailBean.OrderBean.GoodsBean bean) {
        this.bean = bean;
    }

    public OrderDetailBean.OrderBean.GoodsBean getBean() {
        return bean;
    }

    public void setBean(OrderDetailBean.OrderBean.GoodsBean bean) {
        this.bean = bean;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
