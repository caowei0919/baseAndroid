package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: SearchGoodsBean
 * Author: 曹伟
 * Date: 2019/11/29 11:45
 * Description:搜索页面搜索商品实体
 */
@Keep
public class SearchGoodsBean {

    /**
     * goods_tips :
     * inlet : 1
     * group_id :
     * goods_id : 123
     * goods_type : 1
     * name : 换季推荐水果
     * image : http://img.wobianmall.com/uploads/2019/08/15/a92b298e97894b793393be8ef350c8c0.jpg
     * vip_price : 1.00
     * sell_price : 2.00
     * sale : 0
     * addtime : 1565834189
     * store_nums : 99
     * sku_group : [{"sku_id":232,"image":"http://img.wobianmall.com/uploads/2019/08/15/3e9fd156de3e8e67514fda3b6825779e.jpg","sku_no":"1565834093","spec_id":"843","spec_value":"广东特色","store_nums":"99","vip_price":"1.00","sell_price":"2.00","minimum":1,"limit_num":"0","step_size":"1"}]
     */

    private String goods_tips;
    private String inlet;
    private String group_id;
    private String goods_id;
    private String goods_type;
    private String name;
    private String image;
    private String vip_price;
    private String sell_price;
    private String sale;
    private String addtime;
    private String store_nums;
    private List<SkuGroupBean> sku_group;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVip_price() {
        return vip_price;
    }

    public void setVip_price(String vip_price) {
        this.vip_price = vip_price;
    }

    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getStore_nums() {
        return store_nums;
    }

    public void setStore_nums(String store_nums) {
        this.store_nums = store_nums;
    }

    public List<SkuGroupBean> getSku_group() {
        return sku_group;
    }

    public void setSku_group(List<SkuGroupBean> sku_group) {
        this.sku_group = sku_group;
    }

    public static class SkuGroupBean {
        /**
         * sku_id : 232
         * image : http://img.wobianmall.com/uploads/2019/08/15/3e9fd156de3e8e67514fda3b6825779e.jpg
         * sku_no : 1565834093
         * spec_id : 843
         * spec_value : 广东特色
         * store_nums : 99
         * vip_price : 1.00
         * sell_price : 2.00
         * minimum : 1
         * limit_num : 0
         * step_size : 1
         */

        private String sku_id;
        private String image;
        private String sku_no;
        private String spec_id;
        private String spec_value;
        private String store_nums;
        private String vip_price;
        private String sell_price;
        private String minimum;
        private String limit_num;
        private String step_size;

        public String getSku_id() {
            return sku_id;
        }

        public void setSku_id(String sku_id) {
            this.sku_id = sku_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSku_no() {
            return sku_no;
        }

        public void setSku_no(String sku_no) {
            this.sku_no = sku_no;
        }

        public String getSpec_id() {
            return spec_id;
        }

        public void setSpec_id(String spec_id) {
            this.spec_id = spec_id;
        }

        public String getSpec_value() {
            return spec_value;
        }

        public void setSpec_value(String spec_value) {
            this.spec_value = spec_value;
        }

        public String getStore_nums() {
            return store_nums;
        }

        public void setStore_nums(String store_nums) {
            this.store_nums = store_nums;
        }

        public String getVip_price() {
            return vip_price;
        }

        public void setVip_price(String vip_price) {
            this.vip_price = vip_price;
        }

        public String getSell_price() {
            return sell_price;
        }

        public void setSell_price(String sell_price) {
            this.sell_price = sell_price;
        }

        public String getMinimum() {
            return minimum;
        }

        public void setMinimum(String minimum) {
            this.minimum = minimum;
        }

        public String getLimit_num() {
            return limit_num;
        }

        public void setLimit_num(String limit_num) {
            this.limit_num = limit_num;
        }

        public String getStep_size() {
            return step_size;
        }

        public void setStep_size(String step_size) {
            this.step_size = step_size;
        }
    }
}
