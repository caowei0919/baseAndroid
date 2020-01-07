package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;

import com.mcxtzhang.indexlib.suspension.ISuspensionInterface;

import java.util.List;

/**
 * FileName: DormitoryGoodsBean
 * Author: 曹伟
 * Date: 2019/11/18 16:06
 * Description:宿舍小店商品
 */
@Keep
public class DormitoryGoodsBean {

    /**
     * cid : 0
     * title : 热销排行
     * cate_num : 0
     * goods : [{"id":"5489","title":"默认商品","img":"http://img.wobianmall.com//static/mall/20190926/2182d107bc317295154620eca23d7e9a.jpg","buy_price":"0.10","vip_price":"0.10","show_price":"1.00","spec":"大使馆大使馆 南洋股份 扔隔热一扔一遥一遥瑶一遥一摇滚一不表年按照言交友聊天；螺旋影片国通话遥遥  ","cate_id":"0","sales":"70","qty":"2","shop_sku_id":"5489","steplong":1,"cart_num":0,"status":"1","tag":"0","tag_txt":"","is_can_reservation":"1"},{"id":"5492","title":"测试牛奶！！","img":"http://img.wobianmall.com//static/mall/20190412/de9ed9c8b1732ce1f4e3a0d1755c4a30.jpg","buy_price":"0.02","vip_price":"0.02","show_price":"0.03","spec":"280ml","cate_id":"0","sales":"59","qty":"0","shop_sku_id":"5492","steplong":1,"cart_num":0,"status":"1","tag":"1","tag_txt":"热销","is_can_reservation":"1"},{"id":"5493","title":"测试商品","img":"http://img.wobianmall.com//static/mall/20190429/738f85352f35cfce88ce293dc7c85868.jpg","buy_price":"0.02","vip_price":"0.02","show_price":"0.03","spec":"2斤","cate_id":"0","sales":"70","qty":"0","shop_sku_id":"5493","steplong":1,"cart_num":0,"status":"1","tag":"1","tag_txt":"热销","is_can_reservation":"1"},{"id":"5494","title":"测试水果","img":"http://img.wobianmall.com//static/mall/20190515/f22100bebea24f7ecf0f94fadab132f8.jpg","buy_price":"1.00","vip_price":"1.00","show_price":"2.00","spec":"1斤","cate_id":"0","sales":"10","qty":"0","shop_sku_id":"5494","steplong":1,"cart_num":0,"status":"1","tag":"0","tag_txt":"","is_can_reservation":"1"},{"id":"5498","title":"测试商品一号","img":"http://img.wobianmall.com//static/mall/20190515/fe3df156f51a1246402d7f44a5766492.jpg","buy_price":"0.50","vip_price":"0.50","show_price":"1.00","spec":"500ml","cate_id":"0","sales":"14","qty":"5","shop_sku_id":"5498","steplong":1,"cart_num":0,"status":"1","tag":"0","tag_txt":"","is_can_reservation":"1"},{"id":"5499","title":"测试杯子1号","img":"http://img.wobianmall.com//static/mall/20190508/0b33586d60be674dfa54dbbbe43a4a34.jpg","buy_price":"0.02","vip_price":"0.02","show_price":"0.03","spec":"白色","cate_id":"0","sales":"-112","qty":"3","shop_sku_id":"5499","steplong":1,"cart_num":0,"status":"1","tag":"3","tag_txt":"招牌","is_can_reservation":"1"}]
     */

    private String cid;
    private String title;
    private String cate_num;
    private List<GoodsBean> goods;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCate_num() {
        return cate_num;
    }

    public void setCate_num(String cate_num) {
        this.cate_num = cate_num;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean implements ISuspensionInterface {
        /**
         * id : 5489
         * title : 默认商品
         * img : http://img.wobianmall.com//static/mall/20190926/2182d107bc317295154620eca23d7e9a.jpg
         * buy_price : 0.10
         * vip_price : 0.10
         * show_price : 1.00
         * spec : 大使馆大使馆 南洋股份 扔隔热一扔一遥一遥瑶一遥一摇滚一不表年按照言交友聊天；螺旋影片国通话遥遥
         * cate_id : 0
         * sales : 70
         * qty : 2
         * shop_sku_id : 5489
         * steplong : 1
         * cart_num : 0
         * status : 1
         * tag : 0
         * tag_txt :
         * is_can_reservation : 1
         */
        private String good_type ;  //手动添加的商品类别标识

        public String getGood_type() {
            return good_type;
        }

        public void setGood_type(String good_type) {
            this.good_type = good_type;
        }

        private String id;
        private String title;
        private String img;
        private String buy_price;
        private String vip_price;
        private String show_price;
        private String spec;
        private String cate_id;
        private String sales;
        private String qty;
        private String shop_sku_id;
        private String steplong;
        private String cart_num;
        private String status;
        private String tag;
        private String tag_txt;
        private String is_can_reservation;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(String buy_price) {
            this.buy_price = buy_price;
        }

        public String getVip_price() {
            return vip_price;
        }

        public void setVip_price(String vip_price) {
            this.vip_price = vip_price;
        }

        public String getShow_price() {
            return show_price;
        }

        public void setShow_price(String show_price) {
            this.show_price = show_price;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getShop_sku_id() {
            return shop_sku_id;
        }

        public void setShop_sku_id(String shop_sku_id) {
            this.shop_sku_id = shop_sku_id;
        }

        public String getSteplong() {
            return steplong;
        }

        public void setSteplong(String steplong) {
            this.steplong = steplong;
        }

        public String getCart_num() {
            return cart_num;
        }

        public void setCart_num(String cart_num) {
            this.cart_num = cart_num;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTag_txt() {
            return tag_txt;
        }

        public void setTag_txt(String tag_txt) {
            this.tag_txt = tag_txt;
        }

        public String getIs_can_reservation() {
            return is_can_reservation;
        }

        public void setIs_can_reservation(String is_can_reservation) {
            this.is_can_reservation = is_can_reservation;
        }

        @Override
        public boolean isShowSuspension() {
            return true;
        }

        @Override
        public String getSuspensionTag() {
            return good_type;
        }
    }
}
