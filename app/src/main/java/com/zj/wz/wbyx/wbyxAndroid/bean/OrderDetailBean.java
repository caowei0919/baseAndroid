package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * FileName: OrderDetailBean
 * Author: 曹伟
 * Date: 2019/11/20 15:29
 * Description:
 */

@Keep
public class OrderDetailBean {

    /**
     * order : {"type":"global","shop_name":"窝边商城","building_name":"","freight_price":0,"service_price":0,"goods_price":"1.00","pick_tip":"","refund_status":"","refund_status_code":0,"is_commented":"0","price_total":"2.00","id":"313810","order_id":"1485","order_sn":"20191117044253b4b931629d2485ab","pay_type":"支付宝","creat_t":"2019-11-17 04:42:53","pay_t":"2019-11-17 04:43:01","send_t":"","deliv_t":"","status":"待发货","status_code":"2","delivery":{},"confirm_exp":0,"pay_exp":0,"remark":"这种族和睦共处中","goods":[{"id":"129","sku_id":"241","title":"龙肉*","img":"http://img.wobianmall.com/uploads/2019/08/28/ff9440312ea66fd6591d396c2a07a714.jpg","goods_type":1,"warehouse_id":0,"price":"1.00","num":"2","specs":["龙肉"]}]}
     * address : {"id":"2049","name":"是啊","tel":"17621628088","addr":"江苏省南京市玄武区南京大学1刂中心-起来室101室"}
     * current_time : 1574234845
     * refund_reason : {"5":"非营业时间下单，送货时间太晚了","6":"营业时间下单，要等很久才送","7":"买错了，重新买/不想要了","8":"其它"}
     */

    private OrderBean order;
    private AddressBean address;
    private String current_time;
    private RefundReasonBean refund_reason;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    public RefundReasonBean getRefund_reason() {
        return refund_reason;
    }

    public void setRefund_reason(RefundReasonBean refund_reason) {
        this.refund_reason = refund_reason;
    }

    private class RefundReasonBean {

        /**
         * 5 : 非营业时间下单，送货时间太晚了
         * 6 : 营业时间下单，要等很久才送
         * 7 : 买错了，重新买/不想要了
         * 8 : 其它
         */

        @SerializedName("5")
        private String _$5;
        @SerializedName("6")
        private String _$6;
        @SerializedName("7")
        private String _$7;
        @SerializedName("8")
        private String _$8;

        public String get_$5() {
            return _$5;
        }

        public void set_$5(String _$5) {
            this._$5 = _$5;
        }

        public String get_$6() {
            return _$6;
        }

        public void set_$6(String _$6) {
            this._$6 = _$6;
        }

        public String get_$7() {
            return _$7;
        }

        public void set_$7(String _$7) {
            this._$7 = _$7;
        }

        public String get_$8() {
            return _$8;
        }

        public void set_$8(String _$8) {
            this._$8 = _$8;
        }
    }

    public class AddressBean{

        /**
         * id : 2049
         * name : 是啊
         * tel : 17621628088
         * addr : 江苏省南京市玄武区南京大学1刂中心-起来室101室
         */

        private String id;
        private String name;
        private String tel;
        private String addr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }
    }

    public class OrderBean {

        /**
         * type : global
         * shop_name : 窝边商城
         * building_name :
         * freight_price : 0
         * service_price : 0
         * goods_price : 1.00
         * pick_tip :
         * refund_status :
         * refund_status_code : 0
         * is_commented : 0
         * price_total : 2.00
         * id : 313810
         * order_id : 1485
         * order_sn : 20191117044253b4b931629d2485ab
         * pay_type : 支付宝
         * creat_t : 2019-11-17 04:42:53
         * pay_t : 2019-11-17 04:43:01
         * send_t :
         * deliv_t :
         * status : 待发货
         * status_code : 2
         * delivery : {}
         * confirm_exp : 0
         * pay_exp : 0
         * remark : 这种族和睦共处中
         * goods : [{"id":"129","sku_id":"241","title":"龙肉*","img":"http://img.wobianmall.com/uploads/2019/08/28/ff9440312ea66fd6591d396c2a07a714.jpg","goods_type":1,"warehouse_id":0,"price":"1.00","num":"2","specs":["龙肉"]}]
         */

        private String type;
        private String shop_name;
        private String building_name;
        private String freight_price;
        private String service_price;
        private String goods_price;
        private String pick_tip;
        private String refund_status;
        private String refund_status_code;
        private String is_commented;
        private String price_total;
        private String id;
        private String order_id;
        private String order_sn;
        private String pay_type;
        private String creat_t;
        private String pay_t;
        private String send_t;
        private String deliv_t;
        private String status;
        private String status_code;
        private DeliveryBean delivery;
        private String confirm_exp;
        private String pay_exp;
        private String remark;
        private List<GoodsBean> goods;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

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

        public String getFreight_price() {
            return freight_price;
        }

        public void setFreight_price(String freight_price) {
            this.freight_price = freight_price;
        }

        public String getService_price() {
            return service_price;
        }

        public void setService_price(String service_price) {
            this.service_price = service_price;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getPick_tip() {
            return pick_tip;
        }

        public void setPick_tip(String pick_tip) {
            this.pick_tip = pick_tip;
        }

        public String getRefund_status() {
            return refund_status;
        }

        public void setRefund_status(String refund_status) {
            this.refund_status = refund_status;
        }

        public String getRefund_status_code() {
            return refund_status_code;
        }

        public void setRefund_status_code(String refund_status_code) {
            this.refund_status_code = refund_status_code;
        }

        public String getIs_commented() {
            return is_commented;
        }

        public void setIs_commented(String is_commented) {
            this.is_commented = is_commented;
        }

        public String getPrice_total() {
            return price_total;
        }

        public void setPrice_total(String price_total) {
            this.price_total = price_total;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getCreat_t() {
            return creat_t;
        }

        public void setCreat_t(String creat_t) {
            this.creat_t = creat_t;
        }

        public String getPay_t() {
            return pay_t;
        }

        public void setPay_t(String pay_t) {
            this.pay_t = pay_t;
        }

        public String getSend_t() {
            return send_t;
        }

        public void setSend_t(String send_t) {
            this.send_t = send_t;
        }

        public String getDeliv_t() {
            return deliv_t;
        }

        public void setDeliv_t(String deliv_t) {
            this.deliv_t = deliv_t;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus_code() {
            return status_code;
        }

        public void setStatus_code(String status_code) {
            this.status_code = status_code;
        }

        public DeliveryBean getDelivery() {
            return delivery;
        }

        public void setDelivery(DeliveryBean delivery) {
            this.delivery = delivery;
        }

        public String getConfirm_exp() {
            return confirm_exp;
        }

        public void setConfirm_exp(String confirm_exp) {
            this.confirm_exp = confirm_exp;
        }

        public String getPay_exp() {
            return pay_exp;
        }

        public void setPay_exp(String pay_exp) {
            this.pay_exp = pay_exp;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public class DeliveryBean {
        }

        public  class GoodsBean {
            /**
             * id : 129
             * sku_id : 241
             * title : 龙肉*
             * img : http://img.wobianmall.com/uploads/2019/08/28/ff9440312ea66fd6591d396c2a07a714.jpg
             * goods_type : 1
             * warehouse_id : 0
             * price : 1.00
             * num : 2
             * specs : ["龙肉"]
             */

            private String id;
            private String sku_id;
            private String refund_txt ;

            public String getRefund_txt() {
                return refund_txt;
            }

            public void setRefund_txt(String refund_txt) {
                this.refund_txt = refund_txt;
            }

            private String title;
            private String img;
            private String goods_type;
            private String warehouse_id;
            private String price;
            private String num;
            private List<String> specs;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public String getWarehouse_id() {
                return warehouse_id;
            }

            public void setWarehouse_id(String warehouse_id) {
                this.warehouse_id = warehouse_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public List<String> getSpecs() {
                return specs;
            }

            public void setSpecs(List<String> specs) {
                this.specs = specs;
            }
        }
    }
}
