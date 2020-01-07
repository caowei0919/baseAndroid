package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: RefereesBean
 * Author: 曹伟
 * Date: 2019/10/8 14:55
 * Description:推荐人实体
 */
@Keep
public class RefereesBean {

    /**
     * phone : 13776054690
     * marketer : [{"head_image":"http://img.wobianmall.com//static/mall/20190605/fa583d035ca586598942ad50faaecce1.png","truename":"丁潇","describe":"","city":"镇江","cellphone":"13033596208"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/7ede761c13f96998a021a33da28587ce.png","truename":"郭齐宾","describe":"","city":"南京","cellphone":"15651775527"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/77a24ee02f0ec673f74fc75aadfafd99.png","truename":"黄俭强","describe":"","city":"南京","cellphone":"18851091001"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/f2f93f4e8eea74631b98cca74eee3870.png","truename":"李智伟","describe":"","city":"泰州","cellphone":"18360715520"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/959269561a4e47ef07ca06ea8305a96e.png","truename":"梁梦凯","describe":"","city":"南京","cellphone":"15651775526"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/8df3d024879adc5e64bf8c8d6853222d.png","truename":"刘超远","describe":"","city":"南京","cellphone":"15380768615"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/5d86845c9c872ff326c2724345ad11b9.png","truename":"孙吉锐","describe":"","city":"南京","cellphone":"15252474178"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/a69f2570eac9c4987b679007dd21f051.png","truename":"佟瑶","describe":"","city":"南京","cellphone":"18242091817"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/46b7f4e5ff9ba72902f8aaf82b8b0ae9.png","truename":"代亚威","describe":"人生如画，每一笔都是自己的勾划!","city":"合肥","cellphone":"18168035865"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/d3e83770112d02274aea589f2b4f7a8b.png","truename":"杨豪","describe":"","city":"南京","cellphone":"18851728279"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/505e86a11401bae2385dd491fdc274c9.png","truename":"李永随","describe":"","city":"南京","cellphone":"15720610960"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/f0cdf1a0ce09e6a7fb1aae1a451d181a.png","truename":"张涛","describe":"","city":"南京","cellphone":"18852051720"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/070ebcedc69fb5567d08380db7bddc4f.png","truename":"储玉坤","describe":"丰富带店经验，努力才能成就拥有！","city":"合肥","cellphone":"18055152331"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/f38d1fda1faa5862ce0866a5c8ec47a8.png","truename":"杜锦程","describe":"带出过8大优秀店长，团队日销破万","city":"合肥","cellphone":"18555680500"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/e6d84b159ab9cd76583dafc624d50fa7.png","truename":"黄郑兴","describe":"经济基础决定上层建筑，赚到钱了再谈别的","city":"合肥","cellphone":"17356604067"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/e95b54060046d086b56ef94478154954.png","truename":"余聃华","describe":"要当老板找我，团队日销破万了解一下啊","city":"合肥","cellphone":"13645513114"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/8250344343c2858837a9929affe655e4.png","truename":"张水清","describe":"你荒废的今日，正是昨日殒身之人祈求的明日。","city":"合肥","cellphone":"17318514702"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/c1509f65001c5a93cbfc898a9313e78b.png","truename":"魏郑旺","describe":"热爱，永不止步","city":"合肥","cellphone":"18756909892"},{"head_image":"http://img.wobianmall.com//static/mall/20190605/7ede761c13f96998a021a33da28587ce.png","truename":"邵金龙","describe":"","city":"南京","cellphone":"15380314598"},{"head_image":"http://img.wobianmall.com//static/mall/20190925/b43e721cd5cbd38ba7bde5db9533cdc7.jpg","truename":"马畅畅","describe":"","city":"南京","cellphone":"18052034385"}]
     */

    private String phone;
    private List<MarketerBean> marketer;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<MarketerBean> getMarketer() {
        return marketer;
    }

    public void setMarketer(List<MarketerBean> marketer) {
        this.marketer = marketer;
    }

    public static class MarketerBean {
        /**
         * head_image : http://img.wobianmall.com//static/mall/20190605/fa583d035ca586598942ad50faaecce1.png
         * truename : 丁潇
         * describe :
         * city : 镇江
         * cellphone : 13033596208
         */

        private String head_image;
        private String truename;
        private String describe;
        private String city;
        private String cellphone;

        public String getHead_image() {
            return head_image;
        }

        public void setHead_image(String head_image) {
            this.head_image = head_image;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCellphone() {
            return cellphone;
        }

        public void setCellphone(String cellphone) {
            this.cellphone = cellphone;
        }
    }
}
