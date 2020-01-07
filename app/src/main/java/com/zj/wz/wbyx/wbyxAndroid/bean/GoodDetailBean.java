package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: GoodDetailBean
 * Author: 曹伟
 * Date: 2019/11/19 15:17
 * Description:商品详情信息
 */
@Keep
public class GoodDetailBean {

    /**
     * code : 200
     * msg : 请求成功
     * is_collect : 0
     * activity_type : common
     * img : [{"url":"http://img.wobianmall.com/uploads/2019/08/19/fd566a8c7ae5a00f11af56c59305ceed.jpg"}]
     * details : {"goods_id":"124","goods_type":"1","name":"女包","sell_price":"5.00","vip_price":"3.00","store_nums":"1000","sale":"0","comments":"0","details":"<span style=\"color:#666666;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">只能上传1张图片，建议尺寸：702 x 276 像素，最大支持 12 MB 的图片( jpg / gif / png ））<\/span>","status":"1"}
     * comment : {}
     * sku_group : [{"sku_id":"234","store_nums":"1000","sell_price":"5.00","vip_price":"3.00","standard_id":"820","spec_value":"红色","sku_img":"http://img.wobianmall.com/uploads/2019/08/19/833843f777a01d49f05f37dc4f29dd33.jpg","image":"http://img.wobianmall.com/uploads/2019/08/19/833843f777a01d49f05f37dc4f29dd33.jpg","minimum":2,"limit_num":"0","step_size":"1"}]
     * group : {}
     * share_url : http://testh5.wobianmall.com/detail?proObj={"goods_type":"1","goods_id":"124","warehouse_id":"0","group_id":"0","inlet":"common"}&warehouse_id=0
     * share_content : 新用户注册即送3天超级会员，下单立享会员价，7折封顶！
     */

    private String code;
    private String msg;
    private String is_collect;
    private String activity_type;
    private DetailsBean details;
    private CommentBean comment;
    private GroupBean group;
    private String share_url;
    private String share_content;
    private List<ImgBean> img;
    private List<SkuGroupBean> sku_group;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(String is_collect) {
        this.is_collect = is_collect;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public DetailsBean getDetails() {
        return details;
    }

    public void setDetails(DetailsBean details) {
        this.details = details;
    }

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }

    public List<ImgBean> getImg() {
        return img;
    }

    public void setImg(List<ImgBean> img) {
        this.img = img;
    }

    public List<SkuGroupBean> getSku_group() {
        return sku_group;
    }

    public void setSku_group(List<SkuGroupBean> sku_group) {
        this.sku_group = sku_group;
    }

    public static class DetailsBean {
        /**
         * goods_id : 124
         * goods_type : 1
         * name : 女包
         * sell_price : 5.00
         * vip_price : 3.00
         * store_nums : 1000
         * sale : 0
         * comments : 0
         * details : <span style="color:#666666;font-family:&quot;font-size:14px;background-color:#FFFFFF;">只能上传1张图片，建议尺寸：702 x 276 像素，最大支持 12 MB 的图片( jpg / gif / png ））</span>
         * status : 1
         */

        private String warehouse_id ;
        private String goods_id;
        private String goods_type;
        private String name;
        private String sell_price;

        public String getWarehouse_id() {
            return warehouse_id;
        }

        public void setWarehouse_id(String warehouse_id) {
            this.warehouse_id = warehouse_id;
        }

        private String vip_price;
        private String store_nums;
        private String sale;
        private String comments;
        private String details;
        private String status;

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

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class CommentBean {
        private String sku_value ;
        private String level ;
        private String evaluate ;
        private String addtime ;
        private String nickname ;
        private String avatar ;

        public String getSku_value() {
            return sku_value;
        }

        public void setSku_value(String sku_value) {
            this.sku_value = sku_value;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public static class GroupBean {
        private String share_title ;
        private String share_content ;
        private String share_img ;
        private String status ;
        private String number ;
        private String group_id ;
        private String start_time ;
        private String end_time ;
        private String now_time ;
        private String buy_number ;
        private String group_price ;

        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        public String getShare_content() {
            return share_content;
        }

        public void setShare_content(String share_content) {
            this.share_content = share_content;
        }

        public String getShare_img() {
            return share_img;
        }

        public void setShare_img(String share_img) {
            this.share_img = share_img;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getNow_time() {
            return now_time;
        }

        public void setNow_time(String now_time) {
            this.now_time = now_time;
        }

        public String getBuy_number() {
            return buy_number;
        }

        public void setBuy_number(String buy_number) {
            this.buy_number = buy_number;
        }

        public String getGroup_price() {
            return group_price;
        }

        public void setGroup_price(String group_price) {
            this.group_price = group_price;
        }
    }

    public static class ImgBean {
        /**
         * url : http://img.wobianmall.com/uploads/2019/08/19/fd566a8c7ae5a00f11af56c59305ceed.jpg
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class SkuGroupBean {
        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        /**
         * sku_id : 234
         * store_nums : 1000
         * sell_price : 5.00
         * vip_price : 3.00
         * standard_id : 820
         * spec_value : 红色
         * sku_img : http://img.wobianmall.com/uploads/2019/08/19/833843f777a01d49f05f37dc4f29dd33.jpg
         * image : http://img.wobianmall.com/uploads/2019/08/19/833843f777a01d49f05f37dc4f29dd33.jpg
         * minimum : 2
         * limit_num : 0
         * step_size : 1
         */
        private String product_id ;
        private String sku_id;
        private String store_nums;
        private String sell_price;
        private String vip_price;
        private String standard_id;
        private String spec_value;
        private String sku_img;
        private String image;
        private String minimum;
        private String limit_num;
        private String step_size;

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
