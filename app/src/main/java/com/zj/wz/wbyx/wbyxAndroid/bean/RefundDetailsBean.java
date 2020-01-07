package com.zj.wz.wbyx.wbyxAndroid.bean;

import java.util.List;

/**
 * FileName: RefundDetailsBean
 * Author: 曹伟
 * Date: 2019/9/19 20:07
 * Description:
 */

public class RefundDetailsBean {


    /**
     * shop_name : 庆1号店铺
     * building_name : 测试7
     * goods_price : 2.00
     * status_code : 1
     * status : 已退款
     * created_at : 2019-09-09 10:24:38
     * service_price : 0.20
     * freight_price : 1.00
     * total_price : 3.20
     * reason : 非营业时间下单，送货时间太晚了
     * id : 312767
     * vip_order : yes
     * goods : [{"goods_img":"http://img.wobianmall.com//static/mall/20190525/b88e871ffbf5fcd208319b4f8cfdd20e.jpg","goods_name":"测得哦","price_vip":"2.00","price_normal":"3.00","specs":"盒","num":"1","total_price":"2.00"}]
     */

    private String shop_name;
    private String building_name;
    private String goods_price;
    private String status_code;
    private String status;
    private String created_at;
    private String service_price;
    private String freight_price;
    private String total_price;
    private String reason;
    private String id;
    private String vip_order;
    private List<GoodsBean> goods;

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getService_price() {
        return service_price;
    }

    public void setService_price(String service_price) {
        this.service_price = service_price;
    }

    public String getFreight_price() {
        return freight_price;
    }

    public void setFreight_price(String freight_price) {
        this.freight_price = freight_price;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVip_order() {
        return vip_order;
    }

    public void setVip_order(String vip_order) {
        this.vip_order = vip_order;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * goods_img : http://img.wobianmall.com//static/mall/20190525/b88e871ffbf5fcd208319b4f8cfdd20e.jpg
         * goods_name : 测得哦
         * price_vip : 2.00
         * price_normal : 3.00
         * specs : 盒
         * num : 1
         * total_price : 2.00
         */

        private String goods_img;
        private String goods_name;
        private String price_vip;
        private String price_normal;
        private String specs;
        private String num;
        private String total_price;

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getPrice_vip() {
            return price_vip;
        }

        public void setPrice_vip(String price_vip) {
            this.price_vip = price_vip;
        }

        public String getPrice_normal() {
            return price_normal;
        }

        public void setPrice_normal(String price_normal) {
            this.price_normal = price_normal;
        }

        public String getSpecs() {
            return specs;
        }

        public void setSpecs(String specs) {
            this.specs = specs;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }
    }
}
