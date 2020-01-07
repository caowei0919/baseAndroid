package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: OrderBean
 * Author: 曹伟
 * Date: 2019/10/22 16:04
 * Description:订单列表
 */
@Keep
public class OrderBean {

    /**
     * shop_id : 1
     * warehouse_id : 6
     * shop_type : warehouse
     * shop_name : 窝边超市
     * shop_icon :
     * pay_type : ali
     * order_id : 313567
     * order_sn : 20191015181303190feaac8a9a15e4
     * order_status : 已关闭
     * order_status_code : 5
     * order_creat_t : 2019-10-15 18:13:03
     * num_total : 4
     * price_total : 5.04
     * exp_t : 1571135283
     * deliv_t :
     * freight_p : 5.00
     * service_p : 0
     * is_commented : 0
     * goods : [{"id":"658","goods_id":"51","sku_id":"140","title":"海南红心火龙果","img":"http://img.wobianmall.com/uploads/2019/06/10/aca7a669ad25f40154cc93209218d563.jpg","specs":["32G"],"spec_txt":"32G","price":"0.01","price_total":"0.04","num_total":4}]
     */

    private String shop_id;
    private String warehouse_id;
    private String shop_type;
    private String shop_name;
    private String shop_icon;
    private String pay_type;
    private String order_id;
    private String order_sn;
    private String order_status;
    private String order_status_code;
    private String order_creat_t;
    private String num_total;
    private String price_total;
    private String exp_t;
    private String deliv_t;
    private String freight_p;
    private String service_p;
    private String is_commented;
    private List<GoodsBean> goods;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_icon() {
        return shop_icon;
    }

    public void setShop_icon(String shop_icon) {
        this.shop_icon = shop_icon;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_status_code() {
        return order_status_code;
    }

    public void setOrder_status_code(String order_status_code) {
        this.order_status_code = order_status_code;
    }

    public String getOrder_creat_t() {
        return order_creat_t;
    }

    public void setOrder_creat_t(String order_creat_t) {
        this.order_creat_t = order_creat_t;
    }

    public String getNum_total() {
        return num_total;
    }

    public void setNum_total(String num_total) {
        this.num_total = num_total;
    }

    public String getPrice_total() {
        return price_total;
    }

    public void setPrice_total(String price_total) {
        this.price_total = price_total;
    }

    public String getExp_t() {
        return exp_t;
    }

    public void setExp_t(String exp_t) {
        this.exp_t = exp_t;
    }

    public String getDeliv_t() {
        return deliv_t;
    }

    public void setDeliv_t(String deliv_t) {
        this.deliv_t = deliv_t;
    }

    public String getFreight_p() {
        return freight_p;
    }

    public void setFreight_p(String freight_p) {
        this.freight_p = freight_p;
    }

    public String getService_p() {
        return service_p;
    }

    public void setService_p(String service_p) {
        this.service_p = service_p;
    }

    public String getIs_commented() {
        return is_commented;
    }

    public void setIs_commented(String is_commented) {
        this.is_commented = is_commented;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * id : 658
         * goods_id : 51
         * sku_id : 140
         * title : 海南红心火龙果
         * img : http://img.wobianmall.com/uploads/2019/06/10/aca7a669ad25f40154cc93209218d563.jpg
         * specs : ["32G"]
         * spec_txt : 32G
         * price : 0.01
         * price_total : 0.04
         * num_total : 4
         */

        private String id;
        private String goods_id;
        private String sku_id;
        private String title;
        private String img;
        private String spec_txt;
        private String price;
        private String price_total;
        private String num_total;
        private List<String> specs;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getSku_id() {
            return sku_id;
        }

        public void setSku_id(String sku_id) {
            this.sku_id = sku_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getSpec_txt() {
            return spec_txt;
        }

        public void setSpec_txt(String spec_txt) {
            this.spec_txt = spec_txt;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice_total() {
            return price_total;
        }

        public void setPrice_total(String price_total) {
            this.price_total = price_total;
        }

        public String getNum_total() {
            return num_total;
        }

        public void setNum_total(String num_total) {
            this.num_total = num_total;
        }

        public List<String> getSpecs() {
            return specs;
        }

        public void setSpecs(List<String> specs) {
            this.specs = specs;
        }
    }
}
