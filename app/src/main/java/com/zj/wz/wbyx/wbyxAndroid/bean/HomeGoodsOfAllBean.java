package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

/**
 * FileName: HomeGoodsOfAllBean
 * Author: 曹伟
 * Date: 2019/11/8 19:06
 * Description: 首页全部类型商品bean
 */

@Keep
public class HomeGoodsOfAllBean {

    /**
     * warehouse_id : 6
     * goods_id : 130
     * store_nums : 95
     * goods_type : 2
     * image : http://img.wobianmall.com/uploads/2019/09/24/06a0f3c1d4aabe9961f8919a3eb3dfff.jpg
     * name : 超市进口水果
     * title : null
     * sell_price : 5.00
     * vip_price : 3.00
     * sale : 0
     */
    private String goods_tips ;
    private String inlet ;
    private String group_id ;

    public String getGoods_tips() {
        return goods_tips;
    }

    public void setGoods_tips(String goods_tips) {
        this.goods_tips = goods_tips;
    }

    public String getInlet() {
        return inlet;
    }

    public void setInlet(String inlet) {
        this.inlet = inlet;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    private String warehouse_id;
    private String goods_id;
    private String store_nums;
    private String goods_type;
    private String image;
    private String name;
    private String title;
    private String sell_price;
    private String vip_price;
    private String sale;

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getStore_nums() {
        return store_nums;
    }

    public void setStore_nums(String store_nums) {
        this.store_nums = store_nums;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }

    public String getVip_price() {
        return vip_price;
    }

    public void setVip_price(String vip_price) {
        this.vip_price = vip_price;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
