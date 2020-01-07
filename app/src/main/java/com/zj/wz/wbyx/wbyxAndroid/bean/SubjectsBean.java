package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: SubjectsBean
 * Author: 曹伟
 * Date: 2019/10/29 19:01
 * Description:专题详情
 */
@Keep
public class SubjectsBean {

    /**
     * banner : {"name":"专题列表22","img":"http://img.wobianmall.com/uploads/2019/10/29/5d291c25ed3b82eb5de16fe5ab5d14f1.jpg","params":{"type":1,"param":{"activity_id":48}}}
     * params : {"type":1,"param":{"activity_id":48}}
     * goods : [{"goods_id":"110","goods_type":1,"warehouse_id":"0","title":"飘 柔","img":"http://img.wobianmall.com/uploads/2019/08/07/16df0e3b979312e42eba2de1b8b4adef.jpg","activity_price":"2.00","normal_price":"3.00"}]
     */

    private BannerBean banner;
    private ParamsBeanX params;
    private List<GoodsBean> goods;

    public BannerBean getBanner() {
        return banner;
    }

    public void setBanner(BannerBean banner) {
        this.banner = banner;
    }

    public ParamsBeanX getParams() {
        return params;
    }

    public void setParams(ParamsBeanX params) {
        this.params = params;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class BannerBean {
        /**
         * name : 专题列表22
         * img : http://img.wobianmall.com/uploads/2019/10/29/5d291c25ed3b82eb5de16fe5ab5d14f1.jpg
         * params : {"type":1,"param":{"activity_id":48}}
         */

        private String name;
        private String img;
        private ParamsBean params;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public static class ParamsBean {
            /**
             * type : 1
             * param : {"activity_id":48}
             */

            private int type;
            private ParamBean param;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public ParamBean getParam() {
                return param;
            }

            public void setParam(ParamBean param) {
                this.param = param;
            }

            public static class ParamBean {
                /**
                 * activity_id : 48
                 */

                private int activity_id;

                public int getActivity_id() {
                    return activity_id;
                }

                public void setActivity_id(int activity_id) {
                    this.activity_id = activity_id;
                }
            }
        }
    }

    public static class ParamsBeanX {
        /**
         * type : 1
         * param : {"activity_id":48}
         */

        private int type;
        private ParamBeanX param;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public ParamBeanX getParam() {
            return param;
        }

        public void setParam(ParamBeanX param) {
            this.param = param;
        }

        public static class ParamBeanX {
            /**
             * activity_id : 48
             */

            private String activity_id;

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }
        }
    }

    public static class GoodsBean {
        /**
         * goods_id : 110
         * goods_type : 1
         * warehouse_id : 0
         * title : 飘 柔
         * img : http://img.wobianmall.com/uploads/2019/08/07/16df0e3b979312e42eba2de1b8b4adef.jpg
         * activity_price : 2.00
         * normal_price : 3.00
         */

        private String goods_id;
        private String goods_type;
        private String warehouse_id;
        private String title;
        private String img;
        private String activity_price;
        private String normal_price;

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

        public String getActivity_price() {
            return activity_price;
        }

        public void setActivity_price(String activity_price) {
            this.activity_price = activity_price;
        }

        public String getNormal_price() {
            return normal_price;
        }

        public void setNormal_price(String normal_price) {
            this.normal_price = normal_price;
        }
    }
}
