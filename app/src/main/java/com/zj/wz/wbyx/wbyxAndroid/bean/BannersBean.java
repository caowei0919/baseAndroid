package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;

/**
 * FileName: BannersBean
 * Author: 曹伟
 * Date: 2019/10/28 19:04
 * Description:轮播图
 */
@Keep
public class BannersBean {
    /**
     * name : 窝边超市
     * img : http://img.wobianmall.com/image/3c06b84f0eafeabb9d7e08baa91ae1a7.png
     * params : {"type":2,"url":"http://testh5.wobianmall.com/aroundSupermarket?goods_type=2&area_id=320102&from=singlemessage&isappinstalled=0"}
     * close_time : 1597398833
     */

    private String name;
    private String img;
    private ParamsBean params;
    private String close_time;

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

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public static class ParamsBean {
        /**
         * type : 2
         * url : http://testh5.wobianmall.com/aroundSupermarket?goods_type=2&area_id=320102&from=singlemessage&isappinstalled=0
         */

        private int type;
        private String url;

        public ParamBean getParam() {
            return param;
        }

        public void setParam(ParamBean param) {
            this.param = param;
        }

        private ParamBean param ;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public static class ParamBean {

            /**
             * goods_type : 1
             * warehouse_id : 0
             * goods_id : 40
             * inlet : common
             */

            private String goods_type;
            private String warehouse_id;
            private String goods_id;
            private String inlet;

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

            public String getInlet() {
                return inlet;
            }

            public void setInlet(String inlet) {
                this.inlet = inlet;
            }
        }
    }

}
