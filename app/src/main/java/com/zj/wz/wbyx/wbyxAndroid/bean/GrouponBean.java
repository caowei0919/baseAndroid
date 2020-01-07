package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: GrouponBean
 * Author: 曹伟
 * Date: 2019/10/29 17:59
 * Description:拼团
 */
@Keep
public class GrouponBean {

    /**
     * title : 团购
     * goods : [{"title":"窝边超市","req_num":"1","img":"http://img.wobianmall.com/uploads/2019/06/21/28bbacf3196fffe77eb35e2409c6d837.jpg","normal_price":6,"activity_price":"0.11"},{"title":"龙肉超市商品","req_num":"2","img":"http://img.wobianmall.com/uploads/2019/08/28/5c0910de8e1771afef598b0cdf3089ba.jpg","normal_price":6,"activity_price":"77.00"},{"title":"西瓜","req_num":"2","img":"http://img.wobianmall.com/uploads/2019/06/10/f1bdc0ae3b892204616c969dd9c79234.jpg","normal_price":1,"activity_price":"0.50"}]
     */

    private String title;
    private List<GoodsBean> goods;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * title : 窝边超市
         * req_num : 1
         * img : http://img.wobianmall.com/uploads/2019/06/21/28bbacf3196fffe77eb35e2409c6d837.jpg
         * normal_price : 6
         * activity_price : 0.11
         */

        private String title;
        private String req_num;
        private String img;
        private int normal_price;
        private String activity_price;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getReq_num() {
            return req_num;
        }

        public void setReq_num(String req_num) {
            this.req_num = req_num;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getNormal_price() {
            return normal_price;
        }

        public void setNormal_price(int normal_price) {
            this.normal_price = normal_price;
        }

        public String getActivity_price() {
            return activity_price;
        }

        public void setActivity_price(String activity_price) {
            this.activity_price = activity_price;
        }
    }
}
