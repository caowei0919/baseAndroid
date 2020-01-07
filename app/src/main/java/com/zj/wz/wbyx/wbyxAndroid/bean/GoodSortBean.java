package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import com.mcxtzhang.indexlib.suspension.ISuspensionInterface;

import java.util.List;

/**
 * FileName: GoodSortBean
 * Author: 曹伟
 * Date: 2019/11/9 18:16
 * Description:
 */
@Keep
public class GoodSortBean {

    /**
     * id : 37
     * name : 同季商品
     * reid : 26
     * sortnum : 20
     * image :
     * flag : 0
     * is_active : 1
     * goods_list : [{"warehouse_id":0,"goods_id":128,"goods_type":"1","store_nums":"99","image":"http://img.wobianmall.com/uploads/2019/08/28/6b179c70fa4fabd72fa64cb106675e92.jpg","title":null,"name":"舒肤佳100g","sale":"0","sell_price":"3.00","vip_price":"1.00","sku_group":[{"sku_id":240,"store_nums":"99","limit_num":"0","sell_price":"3.00","vip_price":"1.00","standard_id":"861","spec_value":"规格","sku_img":"http://img.wobianmall.com/uploads/2019/08/28/67d4fc2e407e0e6a0245d729bc02b7c7.jpg","image":"http://img.wobianmall.com/uploads/2019/08/28/67d4fc2e407e0e6a0245d729bc02b7c7.jpg","step_size":"1","minimum":1}]},{"warehouse_id":"6","goods_id":54,"goods_type":"2","store_nums":"681","image":"http://img.wobianmall.com/uploads/2019/05/28/4cab13d7ce62da2c9917fac447618376.jpg","title":null,"name":"进口火龙果-水果","sale":"0","vip_price":"1.00","sell_price":"1.00","sku_group":[{"product_id":47,"sku_id":49,"image":"http://img.wobianmall.com/uploads/2019/06/10/5c8770dd795f131b13d678bcafc4abf3.jpg","sku_no":"1558596032","spec_id":"844","spec_value":"64G","store_nums":"187","vip_price":"1.00","sell_price":"1.00","minimum":1,"limit_num":"999","step_size":"1"},{"product_id":47,"sku_id":87,"image":"http://img.wobianmall.com/uploads/2019/08/09/207923acac99940eda47a290b17680a2.png","sku_no":"1565317140-1","spec_id":"844","spec_value":"64G","store_nums":"192","vip_price":"3.00","sell_price":"4.00","minimum":4,"limit_num":"0","step_size":"2"},{"product_id":47,"sku_id":88,"image":"http://img.wobianmall.com/uploads/2019/06/10/5c8770dd795f131b13d678bcafc4abf3.jpg","sku_no":"1565317633-2","spec_id":"844","spec_value":"100kg","store_nums":"300","vip_price":"8.00","sell_price":"10.00","minimum":6,"limit_num":"0","step_size":"3"}],"tips":"窝边超市"},{"warehouse_id":"6","goods_id":58,"goods_type":"2","store_nums":"201","image":"http://img.wobianmall.com/uploads/2019/05/28/07ecbede7251769dfcef29d18be9f88a.png","title":null,"name":"越南红心火龙果1个 约650g","sale":"0","vip_price":"13.50","sell_price":"19.50","sku_group":[{"product_id":39,"sku_id":40,"image":"http://img.wobianmall.com/uploads/2019/06/10/6bb5d0061f6b6e7f8870b26afca826d3.jpg","sku_no":"1559015542","spec_id":"805","spec_value":"650g","store_nums":"193","vip_price":"13.50","sell_price":"19.50","minimum":4,"limit_num":"99","step_size":"2"},{"product_id":39,"sku_id":41,"image":"http://img.wobianmall.com/uploads/2019/06/10/3422703733a65830ab27d613e9859dee.jpg","sku_no":"1559015542-1","spec_id":"805","spec_value":"600g","store_nums":"8","vip_price":"14.00","sell_price":"19.00","minimum":4,"limit_num":"99","step_size":"4"}],"tips":"窝边超市"},{"warehouse_id":"6","goods_id":64,"goods_type":"2","store_nums":"60","image":"http://img.wobianmall.com/uploads/2019/06/05/40fdd0015106179a61fc1026f2e2cb33.jpg","title":null,"name":"海南火龙果-水果","sale":"0","vip_price":"3.00","sell_price":"5.00","sku_group":[{"product_id":42,"sku_id":44,"image":"http://img.wobianmall.com/uploads/2019/06/10/7876c6d9394257e88116ed98f9c99461.jpeg","sku_no":"1559737907","spec_id":"836","spec_value":"海南火龙果","store_nums":"60","vip_price":"3.00","sell_price":"5.00","minimum":4,"limit_num":"2","step_size":"2"}],"tips":"窝边超市"},{"warehouse_id":"6","goods_id":68,"goods_type":"2","store_nums":"212","image":"http://img.wobianmall.com/uploads/2019/06/06/a472d55d35c96c6dd6759294365a71ac.jpg","title":null,"name":"广东火龙果-水果","sale":"0","vip_price":"5.00","sell_price":"9.00","sku_group":[{"product_id":44,"sku_id":46,"image":"http://img.wobianmall.com/uploads/2019/06/06/14c1af0ac1ff6f40ca6ca133177d3289.png","sku_no":"1559803248","spec_id":"837","spec_value":"半熟","store_nums":"211","vip_price":"5.00","sell_price":"9.00","minimum":1,"limit_num":"6","step_size":"1"},{"product_id":44,"sku_id":93,"image":"http://img.wobianmall.com/uploads/2019/06/06/14c1af0ac1ff6f40ca6ca133177d3289.png","sku_no":"1561015538-1","spec_id":"837","spec_value":"全熟","store_nums":"1","vip_price":"4.00","sell_price":"8.00","minimum":1,"limit_num":"3","step_size":"1"}],"tips":"窝边超市"}]
     */

    private int id;
    private String name;
    private String reid;
    private String sortnum;
    private String image;
    private String flag;
    private String is_active;
    private List<GoodsListBean> goods_list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public static class GoodsListBean implements ISuspensionInterface {
        /**
         * warehouse_id : 0
         * goods_id : 128
         * goods_type : 1
         * store_nums : 99
         * image : http://img.wobianmall.com/uploads/2019/08/28/6b179c70fa4fabd72fa64cb106675e92.jpg
         * title : null
         * name : 舒肤佳100g
         * sale : 0
         * sell_price : 3.00
         * vip_price : 1.00
         * sku_group : [{"sku_id":240,"store_nums":"99","limit_num":"0","sell_price":"3.00","vip_price":"1.00","standard_id":"861","spec_value":"规格","sku_img":"http://img.wobianmall.com/uploads/2019/08/28/67d4fc2e407e0e6a0245d729bc02b7c7.jpg","image":"http://img.wobianmall.com/uploads/2019/08/28/67d4fc2e407e0e6a0245d729bc02b7c7.jpg","step_size":"1","minimum":1}]
         * tips : 窝边超市
         */
        private String tag ;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

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
        private String tips;
        private List<SkuGroupBean> sku_group;

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

        public Object getTitle() {
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

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public List<SkuGroupBean> getSku_group() {
            return sku_group;
        }

        public void setSku_group(List<SkuGroupBean> sku_group) {
            this.sku_group = sku_group;
        }

        @Override
        public boolean isShowSuspension() {
            return true;
        }

        @Override
        public String getSuspensionTag() {
            return tag;
        }

        public static class SkuGroupBean {
            /**
             * sku_id : 240
             * store_nums : 99
             * limit_num : 0
             * sell_price : 3.00
             * vip_price : 1.00
             * standard_id : 861
             * spec_value : 规格
             * sku_img : http://img.wobianmall.com/uploads/2019/08/28/67d4fc2e407e0e6a0245d729bc02b7c7.jpg
             * image : http://img.wobianmall.com/uploads/2019/08/28/67d4fc2e407e0e6a0245d729bc02b7c7.jpg
             * step_size : 1
             * minimum : 1
             */

            private int sku_id;
            private String store_nums;
            private String limit_num;
            private String sell_price;
            private String vip_price;
            private String standard_id;
            private String spec_value;
            private String sku_img;
            private String image;
            private String step_size;
            private int minimum;

            public int getSku_id() {
                return sku_id;
            }

            public void setSku_id(int sku_id) {
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

            public int getMinimum() {
                return minimum;
            }

            public void setMinimum(int minimum) {
                this.minimum = minimum;
            }
        }
    }
}
