package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: newRecommendBean
 * Author: 曹伟
 * Date: 2019/10/29 16:44
 * Description:最新上线
 */
@Keep
public class NewRecommendBean {

    private List<GoodsBean> goods;

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * goods_id : 106
         * goods_type : 1
         * warehouse_id : 0
         * title : mia洗衣液
         * img : http://img.wobianmall.com/uploads/2019/08/07/f776716d039a7c1c899f68473adde52c.jpg
         * normal_price : 400
         * vip_price : 600
         */

        private String goods_id;
        private String goods_type;
        private String warehouse_id;
        private String title;
        private String img;
        private String normal_price;
        private String vip_price;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
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

        public String getNormal_price() {
            return normal_price;
        }

        public void setNormal_price(String normal_price) {
            this.normal_price = normal_price;
        }

        public String getVip_price() {
            return vip_price;
        }

        public void setVip_price(String vip_price) {
            this.vip_price = vip_price;
        }
    }
}
