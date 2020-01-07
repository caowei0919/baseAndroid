package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: VVipProducBean
 * Author: 曹伟
 * Date: 2019/11/20 9:35
 * Description:会员套餐和支付方式
 */
@Keep
public class VipProducBean {

    private List<ListBean> list;
    private List<PayListBean> pay_list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<PayListBean> getPay_list() {
        return pay_list;
    }

    public void setPay_list(List<PayListBean> pay_list) {
        this.pay_list = pay_list;
    }

    public static class ListBean {
        /**
         * vid : 6
         * vip_name : 三天卡
         * old_price : 2.99
         * price : 1.99
         */

        private String vid;
        private String vip_name;
        private String old_price;
        private String price;

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getVip_name() {
            return vip_name;
        }

        public void setVip_name(String vip_name) {
            this.vip_name = vip_name;
        }

        public String getOld_price() {
            return old_price;
        }

        public void setOld_price(String old_price) {
            this.old_price = old_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    public static class PayListBean {
        /**
         * pay_code : wx
         * title : 微信支付
         * picture : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1960797960,2775936779&fm=26&gp=0.jpg
         */

        private String pay_code;
        private String title;
        private String picture;

        public String getPay_code() {
            return pay_code;
        }

        public void setPay_code(String pay_code) {
            this.pay_code = pay_code;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
