package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

/**
 * FileName: GroupItemBean
 * Author: 曹伟
 * Date: 2019/10/21 15:21
 * Description: 拼团列表详情实体
 */
@Keep
public class GroupItemBean {

    /**
     * group_list_id : 155
     * activity_id : 85
     * group_status : 0
     * group_number : 10
     * warehouse_id : 0
     * goods_id : 131
     * image : http://img.wobianmall.com/uploads/2019/10/12/87c937cdb73a91a010fa5a8d1e74aefa.jpg
     * goods_name : CHAMPION冠军男女款连帽卫衣
     * is_host : 1      //1位团长
     * sell_price : 690.00
     * group_price : 0.50
     * join_number : 0
     * now_time : 1571652492
     * share_title : 三个商品拼团测试标题
     * share_content : 三个商品拼团测试标题内容
     * share_img : http://img.wobianmall.com/uploads/2019/10/16/2827eff004038e7eb176a2e901dfaf71.jpg
     * share_url : http://testh5.wobianmall.com/groupPassiveDetail?warehouse_id=6&area_id=310113&group_id=155
     * addtime : 2019-10-17 16:04:49
     */

    private String group_list_id;
    private String activity_id;
    private String group_status;
    private String group_number;
    private String warehouse_id;
    private String goods_id;
    private String image;
    private String goods_name;
    private String is_host;
    private String sell_price;
    private String group_price;
    private int join_number;
    private int now_time;
    private String share_title;
    private String share_content;
    private String share_img;
    private String share_url;
    private String addtime;

    public String getGroup_list_id() {
        return group_list_id;
    }

    public void setGroup_list_id(String group_list_id) {
        this.group_list_id = group_list_id;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getGroup_status() {
        return group_status;
    }

    public void setGroup_status(String group_status) {
        this.group_status = group_status;
    }

    public String getGroup_number() {
        return group_number;
    }

    public void setGroup_number(String group_number) {
        this.group_number = group_number;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getIs_host() {
        return is_host;
    }

    public void setIs_host(String is_host) {
        this.is_host = is_host;
    }

    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }

    public String getGroup_price() {
        return group_price;
    }

    public void setGroup_price(String group_price) {
        this.group_price = group_price;
    }

    public int getJoin_number() {
        return join_number;
    }

    public void setJoin_number(int join_number) {
        this.join_number = join_number;
    }

    public int getNow_time() {
        return now_time;
    }

    public void setNow_time(int now_time) {
        this.now_time = now_time;
    }

    public String getShare_title() {
        return share_title;
    }

    public void setShare_title(String share_title) {
        this.share_title = share_title;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }

    public String getShare_img() {
        return share_img;
    }

    public void setShare_img(String share_img) {
        this.share_img = share_img;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
