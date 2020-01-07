package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: ShopCarBean
 * Author: 曹伟
 * Date: 2019/10/24 15:39
 * Description: 购物车商品实体
 */
@Keep
public class ShopCarBean {


    private List<ShopBean> shop;
    private List<InvalidBean> invalid;

    public List<ShopBean> getShop() {
        return shop;
    }

    public void setShop(List<ShopBean> shop) {
        this.shop = shop;
    }

    public List<InvalidBean> getInvalid() {
        return invalid;
    }

    public void setInvalid(List<InvalidBean> invalid) {
        this.invalid = invalid;
    }

    public static class ShopBean {
        /**
         * goods : [{"goods_name":"窝边超市","goods_id":"97","product_id":"57","goods_type":"city","sku_id":"65","shop_sku_id":"65","goods_img":"http://img.wobianmall.com/uploads/2019/06/21/83430bc4ce7294f11a07dc5d8ab5eebf.jpg","price_vip":"4.00","price_normal":"6.00","limit_num":0,"cart_num":"4","qty":"81","freight_price":"5","service_price":0,"specs_txt":"盒","step_long":"2","cart_id":"1071","tip":"","show_status":0,"is_selected":"0"},{"goods_name":"越南红心火龙果1个 约650g","goods_id":"58","product_id":"39","goods_type":"city","sku_id":"41","shop_sku_id":"41","goods_img":"http://img.wobianmall.com/uploads/2019/06/10/3422703733a65830ab27d613e9859dee.jpg","price_vip":"14.00","price_normal":"19.00","limit_num":0,"cart_num":"4","qty":"8","freight_price":"5","service_price":0,"specs_txt":"600g","step_long":"4","cart_id":"1070","tip":"","show_status":0,"is_selected":"1"},{"goods_name":"海南红心火龙果","goods_id":"51","product_id":"41","goods_type":"city","sku_id":"43","shop_sku_id":"43","goods_img":"http://img.wobianmall.com/uploads/2019/06/10/aca7a669ad25f40154cc93209218d563.jpg","price_vip":"0.01","price_normal":"0.01","limit_num":0,"cart_num":"4","qty":"144","freight_price":"5","service_price":0,"specs_txt":"32G","step_long":"2","cart_id":"1069","tip":"","show_status":0,"is_selected":"1"}]
         * shop_type : city
         * shop_id : 6
         * shop_icon :
         * shop_name : 窝边超市
         * freight_price : 5
         * start_free_freight : 20
         */
        private boolean isSelected = false ;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        private String shop_type;
        private String shop_id;
        private String shop_icon;
        private String shop_name;
        private String freight_price;
        private String start_free_freight;
        private List<GoodsBean> goods;

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getShop_icon() {
            return shop_icon;
        }

        public void setShop_icon(String shop_icon) {
            this.shop_icon = shop_icon;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getFreight_price() {
            return freight_price;
        }

        public void setFreight_price(String freight_price) {
            this.freight_price = freight_price;
        }

        public String getStart_free_freight() {
            return start_free_freight;
        }

        public void setStart_free_freight(String start_free_freight) {
            this.start_free_freight = start_free_freight;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goods_name : 窝边超市
             * goods_id : 97
             * product_id : 57
             * goods_type : city
             * sku_id : 65
             * shop_sku_id : 65
             * goods_img : http://img.wobianmall.com/uploads/2019/06/21/83430bc4ce7294f11a07dc5d8ab5eebf.jpg
             * price_vip : 4.00
             * price_normal : 6.00
             * limit_num : 0
             * cart_num : 4
             * qty : 81
             * freight_price : 5
             * service_price : 0
             * specs_txt : 盒
             * step_long : 2
             * cart_id : 1071
             * tip :
             * show_status : 0
             * is_selected : 0
             */

            private String goods_name;
            private String goods_id;
            private String product_id;
            private String goods_type;
            private String sku_id;
            private String shop_sku_id;
            private String goods_img;
            private String price_vip;
            private String price_normal;
            private int limit_num;
            private String cart_num;
            private String qty;
            private String freight_price;
            private int service_price;
            private String specs_txt;
            private String step_long;
            private String cart_id;
            private String tip;
            private String show_status;
            private String is_selected;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public String getSku_id() {
                return sku_id;
            }

            public void setSku_id(String sku_id) {
                this.sku_id = sku_id;
            }

            public String getShop_sku_id() {
                return shop_sku_id;
            }

            public void setShop_sku_id(String shop_sku_id) {
                this.shop_sku_id = shop_sku_id;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
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

            public int getLimit_num() {
                return limit_num;
            }

            public void setLimit_num(int limit_num) {
                this.limit_num = limit_num;
            }

            public String getCart_num() {
                return cart_num;
            }

            public void setCart_num(String cart_num) {
                this.cart_num = cart_num;
            }

            public String getQty() {
                return qty;
            }

            public void setQty(String qty) {
                this.qty = qty;
            }

            public String getFreight_price() {
                return freight_price;
            }

            public void setFreight_price(String freight_price) {
                this.freight_price = freight_price;
            }

            public int getService_price() {
                return service_price;
            }

            public void setService_price(int service_price) {
                this.service_price = service_price;
            }

            public String getSpecs_txt() {
                return specs_txt;
            }

            public void setSpecs_txt(String specs_txt) {
                this.specs_txt = specs_txt;
            }

            public String getStep_long() {
                return step_long;
            }

            public void setStep_long(String step_long) {
                this.step_long = step_long;
            }

            public String getCart_id() {
                return cart_id;
            }

            public void setCart_id(String cart_id) {
                this.cart_id = cart_id;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }

            public String getShow_status() {
                return show_status;
            }

            public void setShow_status(String show_status) {
                this.show_status = show_status;
            }

            public String getIs_selected() {
                return is_selected;
            }

            public void setIs_selected(String is_selected) {
                this.is_selected = is_selected;
            }
        }
    }

    public static class InvalidBean {
        /**
         * goods_name : 英语-资料
         * goods_type : global
         * goods_id : 121
         * sku_id : 230
         * goods_img : http://img.wobianmall.com/uploads/2019/08/25/c46b68870ac4c20e7d906fb425feadfc.jpg
         * price_vip : 3.00
         * price_normal : 4.00
         * limit_num : 0
         * cart_num : 4
         * qty : 8
         * cart_id : 2471
         * tip :
         * service_price : 0
         * specs_txt : 套
         * step_long : 2
         * is_selected : 0
         */

        private String goods_name;
        private String goods_type;
        private String goods_id;
        private String sku_id;
        private String goods_img;
        private String price_vip;
        private String price_normal;
        private int limit_num;
        private String cart_num;
        private String qty;
        private String cart_id;
        private String tip;
        private int service_price;
        private String specs_txt;
        private String step_long;
        private int is_selected;

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
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

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
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

        public int getLimit_num() {
            return limit_num;
        }

        public void setLimit_num(int limit_num) {
            this.limit_num = limit_num;
        }

        public String getCart_num() {
            return cart_num;
        }

        public void setCart_num(String cart_num) {
            this.cart_num = cart_num;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getCart_id() {
            return cart_id;
        }

        public void setCart_id(String cart_id) {
            this.cart_id = cart_id;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public int getService_price() {
            return service_price;
        }

        public void setService_price(int service_price) {
            this.service_price = service_price;
        }

        public String getSpecs_txt() {
            return specs_txt;
        }

        public void setSpecs_txt(String specs_txt) {
            this.specs_txt = specs_txt;
        }

        public String getStep_long() {
            return step_long;
        }

        public void setStep_long(String step_long) {
            this.step_long = step_long;
        }

        public int getIs_selected() {
            return is_selected;
        }

        public void setIs_selected(int is_selected) {
            this.is_selected = is_selected;
        }
    }
}
