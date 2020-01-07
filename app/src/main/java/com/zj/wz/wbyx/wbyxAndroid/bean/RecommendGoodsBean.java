package com.zj.wz.wbyx.wbyxAndroid.bean;

import java.util.List;

/**
 * FileName: RecommendGoodsBean
 * Author: 曹伟
 * Date: 2019/11/28 20:17
 * Description:推荐商城商品
 */

public class RecommendGoodsBean {

    /**
     * id : 91
     * name : 女包
     * reid : 90
     * sortnum : 99
     * image :
     * flag : 0
     * is_active : 1
     * goods_list : [{"goods_tips":"","inlet":"0","group_id":"","warehouse_id":0,"goods_id":124,"goods_type":"1","store_nums":"996","image":"http://img.wobianmall.com/uploads/2019/08/19/fd566a8c7ae5a00f11af56c59305ceed.jpg","title":"女包--时尚","name":"女包","sale":"0","sell_price":"5.00","vip_price":"3.00","sku_group":[{"sku_id":234,"store_nums":"996","limit_num":"0","sell_price":"5.00","vip_price":"3.00","standard_id":"820","spec_value":"红色","sku_img":"http://img.wobianmall.com/uploads/2019/08/19/833843f777a01d49f05f37dc4f29dd33.jpg","image":"http://img.wobianmall.com/uploads/2019/08/19/833843f777a01d49f05f37dc4f29dd33.jpg","step_size":"1","minimum":2}]}]
     */

    private String id;
    private String name;
    private String reid;
    private String sortnum;
    private String image;
    private String flag;
    private String is_active;
    private List<GoodsListBean> goods_list;

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

    public String getReid() {
        return reid;
    }

    public void setReid(String reid) {
        this.reid = reid;
    }

    public String getSortnum() {
        return sortnum;
    }

    public void setSortnum(String sortnum) {
        this.sortnum = sortnum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean {
        /**
         * goods_tips :
         * inlet : 0
         * group_id :
         * warehouse_id : 0
         * goods_id : 124
         * goods_type : 1
         * store_nums : 996
         * image : http://img.wobianmall.com/uploads/2019/08/19/fd566a8c7ae5a00f11af56c59305ceed.jpg
         * title : 女包--时尚
         * name : 女包
         * sale : 0
         * sell_price : 5.00
         * vip_price : 3.00
         * sku_group : [{"sku_id":234,"store_nums":"996","limit_num":"0","sell_price":"5.00","vip_price":"3.00","standard_id":"820","spec_value":"红色","sku_img":"http://img.wobianmall.com/uploads/2019/08/19/833843f777a01d49f05f37dc4f29dd33.jpg","image":"http://img.wobianmall.com/uploads/2019/08/19/833843f777a01d49f05f37dc4f29dd33.jpg","step_size":"1","minimum":2}]
         */

        private String goods_tips;
        private String inlet;
        private String group_id;
        private String warehouse_id;
        private String goods_id;
        private String goods_type;
        private String store_nums;
        private String image;
        private String title;
        private String name;
        private String sale;
        private String sell_price;
        private String vip_price;
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

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getStore_nums() {
            return store_nums;
        }

        public void setStore_nums(String store_nums) {
            this.store_nums = store_nums;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSale() {
            return sale;
        }

        public void setSale(String sale) {
            this.sale = sale;
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

        public List<SkuGroupBean> getSku_group() {
            return sku_group;
        }

        public void setSku_group(List<SkuGroupBean> sku_group) {
            this.sku_group = sku_group;
        }

        public static class SkuGroupBean {
            /**
             * sku_id : 234
             * store_nums : 996
             * limit_num : 0
             * sell_price : 5.00
             * vip_price : 3.00
             * standard_id : 820
             * spec_value : 红色
             * sku_img : http://img.wobianmall.com/uploads/2019/08/19/833843f777a01d49f05f37dc4f29dd33.jpg
             * image : http://img.wobianmall.com/uploads/2019/08/19/833843f777a01d49f05f37dc4f29dd33.jpg
             * step_size : 1
             * minimum : 2
             */

            private String sku_id;
            private String store_nums;
            private String limit_num;
            private String sell_price;
            private String vip_price;
            private String standard_id;
            private String spec_value;
            private String sku_img;
            private String image;
            private String step_size;
            private String minimum;

            public String getSku_id() {
                return sku_id;
            }

            public void setSku_id(String sku_id) {
                this.sku_id = sku_id;
            }

            public String getStore_nums() {
                return store_nums;
            }

            public void setStore_nums(String store_nums) {
                this.store_nums = store_nums;
            }

            public String getLimit_num() {
                return limit_num;
            }

            public void setLimit_num(String limit_num) {
                this.limit_num = limit_num;
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

            public String getStandard_id() {
                return standard_id;
            }

            public void setStandard_id(String standard_id) {
                this.standard_id = standard_id;
            }

            public String getSpec_value() {
                return spec_value;
            }

            public void setSpec_value(String spec_value) {
                this.spec_value = spec_value;
            }

            public String getSku_img() {
                return sku_img;
            }

            public void setSku_img(String sku_img) {
                this.sku_img = sku_img;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getStep_size() {
                return step_size;
            }

            public void setStep_size(String step_size) {
                this.step_size = step_size;
            }

            public String getMinimum() {
                return minimum;
            }

            public void setMinimum(String minimum) {
                this.minimum = minimum;
            }
        }
    }
}
