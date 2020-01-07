package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

/**
 * FileName: AddShopCarBean
 * Author: 曹伟
 * Date: 2019/11/27 14:13
 * Description:添加购物车返回bean
 */

@Keep
public class AddShopCarBean {

    /**
     * goods_num : 2
     */

    private String goods_num;

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }
}
