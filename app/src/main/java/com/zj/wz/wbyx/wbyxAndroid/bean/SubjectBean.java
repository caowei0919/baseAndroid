package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: SubjectBean
 * Author: 曹伟
 * Date: 2019/11/13 13:24
 * Description:专题详情实体
 */
@Keep
public class SubjectBean {

    /**
     * poster : http://img.wobianmall.com/uploads/2019/08/15/96115dc4ab358e58e21d1e427d4302fd.jpg
     * good_list : [{"sale":"16","status":"1","sortnum":"1000","goods_type":1,"warehouse_id":"0","goods_id":"95","image":"http://img.wobianmall.com/uploads/2019/07/31/507f2b2d778c324418cc190f81b9f07b.jpg","name":"西瓜水果","up_time":"1565093995","title":"推荐商品","sales":16,"store_nums":7,"vip_price":"0.01","sell_price":"0.01","sku_group":[{"sku_id":"201","product_id":"","limit_num":null,"vip_price":"0.01","sell_price":"0.01","image":"http://img.wobianmall.com/uploads/2019/07/31/507f2b2d778c324418cc190f81b9f07b.jpg","spec_value":"半熟","step_size":"1","minimum":"1","store_nums":"7","sale":"16"}]},{"sale":"0","status":"1","sortnum":"99","goods_type":1,"warehouse_id":"0","goods_id":"131","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","name":"CHAMPION冠军男女款连帽卫衣","up_time":"1572871178","title":null,"sales":0,"store_nums":11887,"vip_price":"439.00","sell_price":"690.00","sku_group":[{"sku_id":"262","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白20","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"252","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白10","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"253","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白11","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"243","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白","step_size":"3","minimum":"2","store_nums":"9991","sale":"0"},{"sku_id":"254","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白12","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"244","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白2","step_size":"1","minimum":"1","store_nums":"98","sale":"0"},{"sku_id":"255","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白13","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"245","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白3","step_size":"1","minimum":"1","store_nums":"99","sale":"0"},{"sku_id":"256","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白14","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"246","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白4","step_size":"1","minimum":"1","store_nums":"99","sale":"0"},{"sku_id":"257","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白15","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"247","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白5","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"258","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白16","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"248","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白6","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"259","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白17","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"249","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白7","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"260","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白18","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"250","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白8","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"261","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白19","step_size":"1","minimum":"1","store_nums":"100","sale":"0"},{"sku_id":"251","product_id":"","limit_num":null,"vip_price":"439.00","sell_price":"690.00","image":"http://img.wobianmall.com/uploads/2019/10/12/83fd1c240bd697a9022619de2827d41f.jpg","spec_value":"S白9","step_size":"1","minimum":"1","store_nums":"100","sale":"0"}]},{"sale":"0","status":"1","sortnum":"99","goods_type":1,"warehouse_id":"0","goods_id":"96","image":"http://img.wobianmall.com/uploads/2019/05/29/4002238cd0b28121b145841c2e4ba526.png","name":"窝边精选--水果","up_time":"1565094694","title":"大使馆","sales":0,"store_nums":246,"vip_price":"7.00","sell_price":"9.00","sku_group":[{"sku_id":"202","product_id":"","limit_num":null,"vip_price":"6.00","sell_price":"8.00","image":"http://img.wobianmall.com/uploads/2019/05/29/4002238cd0b28121b145841c2e4ba526.png","spec_value":"盒","step_size":"1","minimum":"1","store_nums":"106","sale":"0"},{"sku_id":"203","product_id":"","limit_num":null,"vip_price":"7.00","sell_price":"9.00","image":"http://img.wobianmall.com/uploads/2019/05/29/4002238cd0b28121b145841c2e4ba526.png","spec_value":"盒","step_size":"1","minimum":"1","store_nums":"17","sale":"0"}]},{"sale":"2","status":"1","sortnum":"99","goods_type":1,"warehouse_id":"0","goods_id":"65","image":"http://img.wobianmall.com/uploads/2019/06/06/9ef3c07d17b9df1dc28a9bf9cb39d651.jpg","name":"水果-西瓜","up_time":"1565762011","title":null,"sales":2,"store_nums":102,"vip_price":"4.00","sell_price":"5.00","sku_group":[{"sku_id":"158","product_id":"","limit_num":null,"vip_price":"4.00","sell_price":"5.00","image":"http://img.wobianmall.com/uploads/2019/06/06/9ef3c07d17b9df1dc28a9bf9cb39d651.jpg","spec_value":"半熟","step_size":"2","minimum":"1","store_nums":"102","sale":"2"}]},{"sale":"0","status":"1","sortnum":"33","goods_type":1,"warehouse_id":"0","goods_id":"119","image":"http://img.wobianmall.com/uploads/2019/08/13/35d0aa78d40a85cb716807a1b3b6df94.jpg","name":"棒棒糖","up_time":"1569287658","title":"棒棒糖--包装","sales":0,"store_nums":996,"vip_price":"3.00","sell_price":"4.00","sku_group":[{"sku_id":"228","product_id":"","limit_num":null,"vip_price":"3.00","sell_price":"4.00","image":"http://img.wobianmall.com/uploads/2019/08/13/35d0aa78d40a85cb716807a1b3b6df94.jpg","spec_value":"10份","step_size":"2","minimum":"3","store_nums":"996","sale":"0"}]},{"sale":"0","status":"1","sortnum":"11","goods_type":1,"warehouse_id":"0","goods_id":"109","image":"http://img.wobianmall.com/uploads/2019/08/07/d04fa6a5f3c2935f4332faabcd955def.jpg","name":"海飞丝","up_time":"1565839762","title":null,"sales":0,"store_nums":99,"vip_price":"9.00","sell_price":"11.00","sku_group":[{"sku_id":"216","product_id":"","limit_num":null,"vip_price":"9.00","sell_price":"11.00","image":"http://img.wobianmall.com/uploads/2019/08/07/d04fa6a5f3c2935f4332faabcd955def.jpg","spec_value":"100g","step_size":"1","minimum":"1","store_nums":"99","sale":"0"}]},{"sale":"8","status":"1","sortnum":"10","goods_type":1,"warehouse_id":"0","goods_id":"110","image":"http://img.wobianmall.com/uploads/2019/08/07/16df0e3b979312e42eba2de1b8b4adef.jpg","name":"飘 柔","up_time":"1572517667","title":null,"sales":8,"store_nums":110,"vip_price":"2.00","sell_price":"3.00","sku_group":[{"sku_id":"217","product_id":"","limit_num":null,"vip_price":"2.00","sell_price":"3.00","image":"http://img.wobianmall.com/uploads/2019/08/07/16df0e3b979312e42eba2de1b8b4adef.jpg","spec_value":"10g","step_size":"1","minimum":"1","store_nums":"110","sale":"8"}]},{"sale":"0","status":"1","sortnum":"2","goods_type":1,"warehouse_id":"0","goods_id":"45","image":"http://img.wobianmall.com/uploads/2019/07/31/370a38df6defe2c3a3e0e177b4e20c3d.jpg","name":"妃琳卡彩妆哑光口红套装","up_time":"1565864145","title":"333","sales":0,"store_nums":22,"vip_price":"100.00","sell_price":"199.00","sku_group":[{"sku_id":"133","product_id":"","limit_num":null,"vip_price":"100.00","sell_price":"199.00","image":"http://img.wobianmall.com/uploads/2019/07/31/370a38df6defe2c3a3e0e177b4e20c3d.jpg","spec_value":"光彩亮泽娇嫩唇膏（魅力版）","step_size":"1","minimum":"1","store_nums":"11","sale":"0"}]},{"sale":"0","status":"1","sortnum":"1","goods_type":1,"warehouse_id":"0","goods_id":"106","image":"http://img.wobianmall.com/uploads/2019/08/07/f776716d039a7c1c899f68473adde52c.jpg","name":"mia洗衣液","up_time":"1572334510","title":null,"sales":0,"store_nums":103,"vip_price":"6.00","sell_price":"4.00","sku_group":[{"sku_id":"213","product_id":"","limit_num":null,"vip_price":"6.00","sell_price":"4.00","image":"http://img.wobianmall.com/uploads/2019/08/07/f776716d039a7c1c899f68473adde52c.jpg","spec_value":"520g","step_size":"1","minimum":"1","store_nums":"103","sale":"0"}]},{"sale":"0","status":"1","sortnum":"1","goods_type":1,"warehouse_id":"0","goods_id":"108","image":"http://img.wobianmall.com/uploads/2019/08/07/504c5801fbaa58029f9794ec6fa7851c.jpg","name":"拉芳","up_time":"1571104269","title":null,"sales":0,"store_nums":102,"vip_price":"8.00","sell_price":"10.00","sku_group":[{"sku_id":"215","product_id":"","limit_num":null,"vip_price":"8.00","sell_price":"10.00","image":"http://img.wobianmall.com/uploads/2019/08/07/504c5801fbaa58029f9794ec6fa7851c.jpg","spec_value":"500g","step_size":"1","minimum":"1","store_nums":"102","sale":"0"}]}]
     */

    private String poster;
    private List<GoodListBean> good_list;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<GoodListBean> getGood_list() {
        return good_list;
    }

    public void setGood_list(List<GoodListBean> good_list) {
        this.good_list = good_list;
    }

    public static class GoodListBean {
        /**
         * sale : 16
         * status : 1
         * sortnum : 1000
         * goods_type : 1
         * warehouse_id : 0
         * goods_id : 95
         * image : http://img.wobianmall.com/uploads/2019/07/31/507f2b2d778c324418cc190f81b9f07b.jpg
         * name : 西瓜水果
         * up_time : 1565093995
         * title : 推荐商品
         * sales : 16
         * store_nums : 7
         * vip_price : 0.01
         * sell_price : 0.01
         * sku_group : [{"sku_id":"201","product_id":"","limit_num":null,"vip_price":"0.01","sell_price":"0.01","image":"http://img.wobianmall.com/uploads/2019/07/31/507f2b2d778c324418cc190f81b9f07b.jpg","spec_value":"半熟","step_size":"1","minimum":"1","store_nums":"7","sale":"16"}]
         */

        private String sale;
        private String status;
        private String sortnum;
        private String goods_type;
        private String warehouse_id;
        private String goods_id;
        private String image;
        private String name;
        private String up_time;
        private String title;
        private String sales;
        private int store_nums;
        private String vip_price;
        private String sell_price;
        private List<SkuGroupBean> sku_group;

        public String getSale() {
            return sale;
        }

        public void setSale(String sale) {
            this.sale = sale;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSortnum() {
            return sortnum;
        }

        public void setSortnum(String sortnum) {
            this.sortnum = sortnum;
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

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUp_time() {
            return up_time;
        }

        public void setUp_time(String up_time) {
            this.up_time = up_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public int getStore_nums() {
            return store_nums;
        }

        public void setStore_nums(int store_nums) {
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

        public List<SkuGroupBean> getSku_group() {
            return sku_group;
        }

        public void setSku_group(List<SkuGroupBean> sku_group) {
            this.sku_group = sku_group;
        }

        public static class SkuGroupBean {
            /**
             * sku_id : 201
             * product_id :
             * limit_num : null
             * vip_price : 0.01
             * sell_price : 0.01
             * image : http://img.wobianmall.com/uploads/2019/07/31/507f2b2d778c324418cc190f81b9f07b.jpg
             * spec_value : 半熟
             * step_size : 1
             * minimum : 1
             * store_nums : 7
             * sale : 16
             */

            private String sku_id;
            private String product_id;
            private Object limit_num;
            private String vip_price;
            private String sell_price;
            private String image;
            private String spec_value;
            private String step_size;
            private String minimum;
            private String store_nums;
            private String sale;

            public String getSku_id() {
                return sku_id;
            }

            public void setSku_id(String sku_id) {
                this.sku_id = sku_id;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public Object getLimit_num() {
                return limit_num;
            }

            public void setLimit_num(Object limit_num) {
                this.limit_num = limit_num;
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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getSpec_value() {
                return spec_value;
            }

            public void setSpec_value(String spec_value) {
                this.spec_value = spec_value;
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

            public String getStore_nums() {
                return store_nums;
            }

            public void setStore_nums(String store_nums) {
                this.store_nums = store_nums;
            }

            public String getSale() {
                return sale;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }
        }
    }
}
