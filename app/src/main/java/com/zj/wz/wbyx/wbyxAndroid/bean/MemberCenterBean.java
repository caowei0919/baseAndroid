package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * FileName: MemberCenterBean
 * Author: 曹伟
 * Date: 2019/9/23 19:20
 * Description: 会员中心bean
 */

@Keep
public class MemberCenterBean {

    /**
     * user : {"is_vip":"1","nickname":"优秀","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/u45xlG4UyialNIyf2FT0ejlFJW0ljWVLicJhkMrDMYgo5tAnOY4DmGT13tNq7aPbKl4Auibvw57o3Xd51NoLBx6iaQ/132","limit_vip":"1638435998","economize_price":"1190.84","add_time":"1556437067","cellphone":"17621628088","monthCard_price":"9.90","is_landlord":"1"}
     * img : [{"img_id":"33","title":"会员权益","image":"http://img.wobianmall.com/uploads/2019/08/29/831ba7cdc67c816f45eef9067a78aa61.png","link":""},{"img_id":"34","title":"分享有礼","image":"http://img.wobianmall.com/uploads/2019/09/12/e56cf93f0c33dc04d209664a5f7fc370.png","link":"testapp.wobianmall.com?share=2039"}]
     * vipbylist : [{"add_time":"2019-09-17 20:28:39","vip_name":"三天卡","total_price":"1.99"},{"add_time":"2019-09-05 18:22:45","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-09-05 11:37:34","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-09-05 11:34:45","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-08-30 16:27:27","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-08-27 10:22:35","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-08-27 10:16:29","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-08-27 09:47:54","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-06-03 09:30:46","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-05-24 13:45:45","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-05-16 18:58:04","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-05-16 18:54:18","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-05-14 17:06:31","vip_name":"月卡","total_price":"9.90"},{"add_time":"2019-05-13 10:30:16","vip_name":"月卡","total_price":"9.90"}]
     * ord_num : {"1":"76","2":"29","3":"2","4":"19","5":"367","6":"0","7":"3","8":"37"}
     */

    private UserBean user;
    private OrdNumBean ord_num;
    private List<ImgBean> img;
    private List<VipbylistBean> vipbylist;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public OrdNumBean getOrd_num() {
        return ord_num;
    }

    public void setOrd_num(OrdNumBean ord_num) {
        this.ord_num = ord_num;
    }

    public List<ImgBean> getImg() {
        return img;
    }

    public void setImg(List<ImgBean> img) {
        this.img = img;
    }

    public List<VipbylistBean> getVipbylist() {
        return vipbylist;
    }

    public void setVipbylist(List<VipbylistBean> vipbylist) {
        this.vipbylist = vipbylist;
    }

    public static class UserBean {
        /**
         * is_vip : 1
         * nickname : 优秀
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/u45xlG4UyialNIyf2FT0ejlFJW0ljWVLicJhkMrDMYgo5tAnOY4DmGT13tNq7aPbKl4Auibvw57o3Xd51NoLBx6iaQ/132
         * limit_vip : 1638435998
         * economize_price : 1190.84
         * add_time : 1556437067
         * cellphone : 17621628088
         * monthCard_price : 9.90
         * is_landlord : 1
         */

        private String is_vip;
        private String nickname;
        private String avatar;
        private String limit_vip;
        private String economize_price;
        private String add_time;
        private String cellphone;
        private String monthCard_price;
        private String is_landlord;

        public String getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(String is_vip) {
            this.is_vip = is_vip;
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

        public String getLimit_vip() {
            return limit_vip;
        }

        public void setLimit_vip(String limit_vip) {
            this.limit_vip = limit_vip;
        }

        public String getEconomize_price() {
            return economize_price;
        }

        public void setEconomize_price(String economize_price) {
            this.economize_price = economize_price;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getCellphone() {
            return cellphone;
        }

        public void setCellphone(String cellphone) {
            this.cellphone = cellphone;
        }

        public String getMonthCard_price() {
            return monthCard_price;
        }

        public void setMonthCard_price(String monthCard_price) {
            this.monthCard_price = monthCard_price;
        }

        public String getIs_landlord() {
            return is_landlord;
        }

        public void setIs_landlord(String is_landlord) {
            this.is_landlord = is_landlord;
        }
    }

    public static class OrdNumBean {
        /**
         * 1 : 76
         * 2 : 29
         * 3 : 2
         * 4 : 19
         * 5 : 367
         * 6 : 0
         * 7 : 3
         * 8 : 37
         */

        @SerializedName("1")
        private String _$1;
        @SerializedName("2")
        private String _$2;
        @SerializedName("3")
        private String _$3;
        @SerializedName("4")
        private String _$4;
        @SerializedName("5")
        private String _$5;
        @SerializedName("6")
        private String _$6;
        @SerializedName("7")
        private String _$7;
        @SerializedName("8")
        private String _$8;

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }

        public String get_$3() {
            return _$3;
        }

        public void set_$3(String _$3) {
            this._$3 = _$3;
        }

        public String get_$4() {
            return _$4;
        }

        public void set_$4(String _$4) {
            this._$4 = _$4;
        }

        public String get_$5() {
            return _$5;
        }

        public void set_$5(String _$5) {
            this._$5 = _$5;
        }

        public String get_$6() {
            return _$6;
        }

        public void set_$6(String _$6) {
            this._$6 = _$6;
        }

        public String get_$7() {
            return _$7;
        }

        public void set_$7(String _$7) {
            this._$7 = _$7;
        }

        public String get_$8() {
            return _$8;
        }

        public void set_$8(String _$8) {
            this._$8 = _$8;
        }
    }

    public static class ImgBean {
        /**
         * img_id : 33
         * title : 会员权益
         * image : http://img.wobianmall.com/uploads/2019/08/29/831ba7cdc67c816f45eef9067a78aa61.png
         * link :
         */

        private String img_id;
        private String title;
        private String image;
        private String link;

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

    public static class VipbylistBean {
        /**
         * add_time : 2019-09-17 20:28:39
         * vip_name : 三天卡
         * total_price : 1.99
         */

        private String add_time;
        private String vip_name;
        private String total_price;

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getVip_name() {
            return vip_name;
        }

        public void setVip_name(String vip_name) {
            this.vip_name = vip_name;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }
    }
}
