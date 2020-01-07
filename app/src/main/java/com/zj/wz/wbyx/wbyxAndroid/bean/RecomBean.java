package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: RecomBean
 * Author: 曹伟
 * Date: 2019/10/29 14:13
 * Description:模块区数据
 */
@Keep
public class RecomBean {

    /**
     * recom_shop : {"title":"窝边推荐","shop_id":1,"goods":[{"goods_id":"123","title":"换季推荐水果","img":"http://img.wobianmall.com/uploads/2019/08/15/a92b298e97894b793393be8ef350c8c0.jpg","position":"1^1","normal_price":"2.00","vip_price":"1.00"},{"goods_id":"94","title":"铁观音","img":"http://img.wobianmall.com/uploads/2019/06/12/4875ca9f3e69511afd197ce628dcab90.png","position":"1^2","normal_price":"0.01","vip_price":"0.01"}]}
     * city_shop : {"title":"窝边超市","shop_id":1,"goods":[{"goods_id":"63","title":"手工皂畅销装-茶树","img":"http://img.wobianmall.com/uploads/2019/06/05/b266972170c9a8c7b3ab2d42c536d07e.jpg","position":"1^2","normal_price":"75.00","vip_price":"32.80"},{"goods_id":"99","title":"手机","img":"http://img.wobianmall.com/uploads/2019/06/25/8011c3814f5c8ccb8061c3e87a88bfca.jpg","position":"1^1","normal_price":"180.00","vip_price":"200.00"}]}
     * dorm_shop : {"title":"宿舍小店","shop_id":1,"goods":[{"goods_id":"310","title":"猫村长芒果干70g","img":"http://img.wobianmall.com//static/mall/20190604/51fef03e7c28907495ba3fb9ed791c1a.jpg","position":"1^1","normal_price":"8.00","vip_price":"5.20"},{"goods_id":"283","title":"防点水","img":"http://img.wobianmall.com//static/mall/20190525/f0c06585c86d50cf0468fb7df2990d4a.jpg","position":"3^3","normal_price":"0.02","vip_price":"0.01"},{"goods_id":"134","title":"红牛250ml","img":"http://img.wobianmall.com//static/mall/20190412/92c01314d13308ff1aa35a7a130a264e.jpg","position":"3^1","normal_price":"6.80","vip_price":"4.72"},{"goods_id":"322","title":"口水鱼（香辣）13g","img":"http://img.wobianmall.com//static/mall/20190606/8863e4a69a000e030bb89ef19d0064bd.jpg","position":"2^1","normal_price":"1.00","vip_price":"0.70"},{"goods_id":"309","title":"维奕蛋黄酥330g","img":"http://img.wobianmall.com//static/mall/20190604/05276b26a4f6e00491167a96507a3e50.jpg","position":"2^2","normal_price":"39.90","vip_price":"16.90"},{"goods_id":"320","title":"口水鱼（酱汁牛肉味）13g","img":"http://img.wobianmall.com//static/mall/20190606/bdabe4fc7c3df018d3808e3d44ead84b.jpg","position":"2^3","normal_price":"1.00","vip_price":"0.70"},{"goods_id":"321","title":"口水鱼（麻辣味）13g","img":"http://img.wobianmall.com//static/mall/20190606/67addd25a1d4d7caa371559df638415d.jpg","position":"1^2","normal_price":"0.01","vip_price":"0.01"},{"goods_id":"302","title":"乳子牛牛蹄筋140g","img":"http://img.wobianmall.com//static/mall/20190601/68934477f99096cac69c4280b8ea8c1a.jpg","position":"1^3","normal_price":"0.20","vip_price":"0.13"},{"goods_id":"299","title":"统一来一桶藤椒牛肉面105g","img":"http://img.wobianmall.com//static/mall/20190531/e5ac2fe99765f723e85ae24f586b808b.jpg","position":"3^2","normal_price":"0.04","vip_price":"0.03"}]}
     */

    private RecomShopBean recom_shop;   //窝边推荐
    private CityShopBean city_shop;     //窝边超市
    private DormShopBean dorm_shop;     //宿舍小店

    public RecomShopBean getRecom_shop() {
        return recom_shop;
    }

    public void setRecom_shop(RecomShopBean recom_shop) {
        this.recom_shop = recom_shop;
    }

    public CityShopBean getCity_shop() {
        return city_shop;
    }

    public void setCity_shop(CityShopBean city_shop) {
        this.city_shop = city_shop;
    }

    public DormShopBean getDorm_shop() {
        return dorm_shop;
    }

    public void setDorm_shop(DormShopBean dorm_shop) {
        this.dorm_shop = dorm_shop;
    }

    public static class RecomShopBean {
        /**
         * title : 窝边推荐
         * shop_id : 1
         * goods : [{"goods_id":"123","title":"换季推荐水果","img":"http://img.wobianmall.com/uploads/2019/08/15/a92b298e97894b793393be8ef350c8c0.jpg","position":"1^1","normal_price":"2.00","vip_price":"1.00"},{"goods_id":"94","title":"铁观音","img":"http://img.wobianmall.com/uploads/2019/06/12/4875ca9f3e69511afd197ce628dcab90.png","position":"1^2","normal_price":"0.01","vip_price":"0.01"}]
         */

        private String title;
        private String shop_id;
        private List<GoodsBean> goods;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goods_id : 123
             * title : 换季推荐水果
             * img : http://img.wobianmall.com/uploads/2019/08/15/a92b298e97894b793393be8ef350c8c0.jpg
             * position : 1^1
             * normal_price : 2.00
             * vip_price : 1.00
             */

            private String goods_id;
            private String title;
            private String img;
            private String position;
            private String normal_price;
            private String vip_price;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
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

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
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

    public static class CityShopBean {
        /**
         * title : 窝边超市
         * shop_id : 1
         * goods : [{"goods_id":"63","title":"手工皂畅销装-茶树","img":"http://img.wobianmall.com/uploads/2019/06/05/b266972170c9a8c7b3ab2d42c536d07e.jpg","position":"1^2","normal_price":"75.00","vip_price":"32.80"},{"goods_id":"99","title":"手机","img":"http://img.wobianmall.com/uploads/2019/06/25/8011c3814f5c8ccb8061c3e87a88bfca.jpg","position":"1^1","normal_price":"180.00","vip_price":"200.00"}]
         */

        private String title;
        private String shop_id;
        private List<GoodsBeanX> goods;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public List<GoodsBeanX> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBeanX> goods) {
            this.goods = goods;
        }

        public static class GoodsBeanX {
            /**
             * goods_id : 63
             * title : 手工皂畅销装-茶树
             * img : http://img.wobianmall.com/uploads/2019/06/05/b266972170c9a8c7b3ab2d42c536d07e.jpg
             * position : 1^2
             * normal_price : 75.00
             * vip_price : 32.80
             */

            private String goods_id;
            private String title;
            private String img;
            private String position;
            private String normal_price;
            private String vip_price;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
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

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
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

    public static class DormShopBean {
        /**
         * title : 宿舍小店
         * shop_id : 1
         * goods : [{"goods_id":"310","title":"猫村长芒果干70g","img":"http://img.wobianmall.com//static/mall/20190604/51fef03e7c28907495ba3fb9ed791c1a.jpg","position":"1^1","normal_price":"8.00","vip_price":"5.20"},{"goods_id":"283","title":"防点水","img":"http://img.wobianmall.com//static/mall/20190525/f0c06585c86d50cf0468fb7df2990d4a.jpg","position":"3^3","normal_price":"0.02","vip_price":"0.01"},{"goods_id":"134","title":"红牛250ml","img":"http://img.wobianmall.com//static/mall/20190412/92c01314d13308ff1aa35a7a130a264e.jpg","position":"3^1","normal_price":"6.80","vip_price":"4.72"},{"goods_id":"322","title":"口水鱼（香辣）13g","img":"http://img.wobianmall.com//static/mall/20190606/8863e4a69a000e030bb89ef19d0064bd.jpg","position":"2^1","normal_price":"1.00","vip_price":"0.70"},{"goods_id":"309","title":"维奕蛋黄酥330g","img":"http://img.wobianmall.com//static/mall/20190604/05276b26a4f6e00491167a96507a3e50.jpg","position":"2^2","normal_price":"39.90","vip_price":"16.90"},{"goods_id":"320","title":"口水鱼（酱汁牛肉味）13g","img":"http://img.wobianmall.com//static/mall/20190606/bdabe4fc7c3df018d3808e3d44ead84b.jpg","position":"2^3","normal_price":"1.00","vip_price":"0.70"},{"goods_id":"321","title":"口水鱼（麻辣味）13g","img":"http://img.wobianmall.com//static/mall/20190606/67addd25a1d4d7caa371559df638415d.jpg","position":"1^2","normal_price":"0.01","vip_price":"0.01"},{"goods_id":"302","title":"乳子牛牛蹄筋140g","img":"http://img.wobianmall.com//static/mall/20190601/68934477f99096cac69c4280b8ea8c1a.jpg","position":"1^3","normal_price":"0.20","vip_price":"0.13"},{"goods_id":"299","title":"统一来一桶藤椒牛肉面105g","img":"http://img.wobianmall.com//static/mall/20190531/e5ac2fe99765f723e85ae24f586b808b.jpg","position":"3^2","normal_price":"0.04","vip_price":"0.03"}]
         */

        private String title;
        private String shop_id;
        private List<GoodsBeanXX> goods;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public List<GoodsBeanXX> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBeanXX> goods) {
            this.goods = goods;
        }

        public static class GoodsBeanXX {
            /**
             * goods_id : 310
             * title : 猫村长芒果干70g
             * img : http://img.wobianmall.com//static/mall/20190604/51fef03e7c28907495ba3fb9ed791c1a.jpg
             * position : 1^1
             * normal_price : 8.00
             * vip_price : 5.20
             */

            private String goods_id;
            private String title;
            private String img;
            private String position;
            private String normal_price;
            private String vip_price;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
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

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
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
}
