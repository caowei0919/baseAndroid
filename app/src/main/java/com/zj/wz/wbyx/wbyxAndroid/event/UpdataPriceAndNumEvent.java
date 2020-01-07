package com.zj.wz.wbyx.wbyxAndroid.event;

import android.support.annotation.Keep;

/**
 * FileName: UpdataPriceAndNumEvent
 * Author: 曹伟
 * Date: 2019/10/25 16:14
 * Description: 购物车商品选择框点击事件
 */
@Keep
public class UpdataPriceAndNumEvent {
    private String allPrice ;   //总价
    private String allNum ; //总数量

    public String getAllNum() {
        return allNum;
    }

    public void setAllNum(String allNum) {
        this.allNum = allNum;
    }

    public String getAllPrice() {

        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public UpdataPriceAndNumEvent() {

    }
}
