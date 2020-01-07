package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: DormitoryBean
 * Author: 曹伟
 * Date: 2019/11/13 16:05
 * Description: 宿舍小店实体
 */
@Keep
public class DormitoryBean implements Serializable {

    /**
     * dorm : 测试学校一测试7
     * shoper : [{"name":"哈哈","title":"好物业管理店长","notice":"欢迎光临","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/fpsBBtD9nT7jXTjWFxhYzBcyYZ6SVAIRwlj99hX6G9HyAt1dqdFswrFRvQj5OBHBy1V44FRGLLGL7nv4rsOQOQ/132","status":"1","sid":"848","shop_info":"","shop_address":"测试7","phone":"15358183304","grade":0,"grade_total":5,"open_time":[{"week":"全天","hour":"00:00:00-23:59:59"}],"is_open":"1","category":[{"name":"热销榜","icon":"","id":0,"cart_num":"0","level":0},{"name":"测试三级","icon":"","id":"106","level":"3","cate_num":0},{"name":"衣物护理","icon":"","id":"131","level":"3","cate_num":0}]},{"name":"优秀","title":"33店长","notice":"欢迎光临","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/u45xlG4UyialNIyf2FT0ejlFJW0ljWVLicJhkMrDMYgo5tAnOY4DmGT13tNq7aPbKl4Auibvw57o3Xd51NoLBx6iaQ/132","status":"1","sid":"285","shop_info":"我想你了","shop_address":"测试7","phone":"15358183304","grade":0,"grade_total":5,"open_time":[{"hour":"05:00-07:00"},{"hour":"08:00-09:00"},{"hour":"09:01-10:00"},{"hour":"10:01-11:00"}],"is_open":"1","category":[{"name":"热销榜","icon":"","id":0,"cart_num":"0","level":0},{"name":"测试三级","icon":"","id":"106","level":"3","cate_num":0},{"name":"测试三级无","icon":"","id":"133","level":"3","cate_num":0}]}]
     * banners : [{"name":"宿舍小店V2.0.0","img":"http://img.wobianmall.com/uploads/2019/08/22/ce7d0ef3641208744edd20544931e01c.png","start_time":"1564588800","end_time":"1756569600","params":{"type":2,"url":"http://testh5.wobianmall.com/brand?activity_id=30&from=singlemessage&isappinstalled=0"}},{"name":"宿舍小店","img":"http://img.wobianmall.com/uploads/2019/09/04/39c26f6fd894bc8605b3a9860163def4.png","start_time":"1567526400","end_time":"1601740800","params":{"type":1,"param":{"goods_type":1,"warehouse_id":0,"goods_id":"129","inlet":"common"}}},{"name":"指定商品详情","img":"http://img.wobianmall.com/uploads/2019/10/14/7a0f4e5e86ae2228c54e98bedac7ac5b.jpg","start_time":"1570982400","end_time":"1604073600","params":{"type":2,"url":"http://testh5.wobianmall.com/detail?proObj={\"goods_type\":\"2\",\"goods_id\":51,\"warehouse_id\":\"25\"}&warehouse_id=25"}},{"name":"分享入口链接","img":"http://img.wobianmall.com/uploads/2019/10/14/ecb62b983f3ab78a79275795d7440611.jpg","start_time":"1570982400","end_time":"1604073600","params":{"type":2,"url":"http://testh5.wobianmall.com/share_money/share_vip"}},{"name":"分类轮播","img":"http://img.wobianmall.com/uploads/2019/10/14/4d9ce98332c4a4c51c4898d6b67a7c53.jpg","start_time":"1570982400","end_time":"1609344000","params":{"type":2,"url":"http://testh5.wobianmall.com/classify?param=31"}},{"name":"推荐商城","img":"http://img.wobianmall.com/uploads/2019/10/14/c65d3070a8425f2a52a8a23fa35bc8ca.jpg","start_time":"1570982400","end_time":"1604073600","params":{"type":2,"url":"http://testh5.wobianmall.com/aroundSupermarket?goods_type=1&area_id=320102&from=singlemessage&isappinstalled=0"}},{"name":"跳转web","img":"http://img.wobianmall.com/uploads/2019/10/14/dc01cae6404cc8ed46ccfdb4f3503f94.jpg","start_time":"1570982400","end_time":"1604073600","params":{"type":2,"url":"https://www.baidu.com/"}},{"name":"专题活动","img":"http://img.wobianmall.com/uploads/2019/10/14/4aa3d85074f52a2cb0a6e3ee048e918d.jpg","start_time":"1570982400","end_time":"1604073600","params":{"type":2,"url":"http://testh5.wobianmall.com/brand?activity_id=30&from=singlemessage&isappinstalled=0"}},{"name":"http://tes","img":"http://img.wobianmall.com/uploads/2019/10/14/aab8b8093d1bdc9e23fd242d9aa59dba.jpg","start_time":"1570982400","end_time":"1604073600","params":{"type":2,"url":"http://testh5.wobianmall.com/appshophome?from=singlemessage&isappinstalled=0"}},{"name":"窝边超市","img":"http://img.wobianmall.com/uploads/2019/10/14/7e90c1fadcbe923021348af39cccf7a6.jpg","start_time":"1570982400","end_time":"1604073600","params":{"type":2,"url":"http://testh5.wobianmall.com/aroundSupermarket?goods_type=2&area_id=320102&from=singlemessage&isappinstalled=0"}},{"name":"商品详情","img":"http://img.wobianmall.com/uploads/2019/10/14/1e372c7a41772615b2683183a14d4ca3.jpg","start_time":"1570982400","end_time":"1604073600","params":{"type":1,"param":{"goods_type":1,"warehouse_id":0,"goods_id":"110","inlet":"common"}}},{"name":"上海宿舍轮播图","img":"http://img.wobianmall.com/uploads/2019/10/29/85c8ee528bd45ba905db7e559c09e570.jpeg","start_time":"1569859200","end_time":"1576771200","params":{"type":1,"param":{"goods_type":1,"warehouse_id":0,"goods_id":"40","inlet":"common"}}}]
     * addr_id : 15369
     */

    private String dorm;
    private String addr_id;
    private List<ShoperBean> shoper;
    private List<BannersBean> banners;

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public String getAddr_id() {
        return addr_id;
    }

    public void setAddr_id(String addr_id) {
        this.addr_id = addr_id;
    }

    public List<ShoperBean> getShoper() {
        return shoper;
    }

    public void setShoper(List<ShoperBean> shoper) {
        this.shoper = shoper;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class ShoperBean implements Serializable {
        /**
         * name : 哈哈
         * title : 好物业管理店长
         * notice : 欢迎光临
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/fpsBBtD9nT7jXTjWFxhYzBcyYZ6SVAIRwlj99hX6G9HyAt1dqdFswrFRvQj5OBHBy1V44FRGLLGL7nv4rsOQOQ/132
         * status : 1
         * sid : 848
         * shop_info :
         * shop_address : 测试7
         * phone : 15358183304
         * grade : 0
         * grade_total : 5
         * open_time : [{"week":"全天","hour":"00:00:00-23:59:59"}]
         * is_open : 1
         * category : [{"name":"热销榜","icon":"","id":0,"cart_num":"0","level":0},{"name":"测试三级","icon":"","id":"106","level":"3","cate_num":0},{"name":"衣物护理","icon":"","id":"131","level":"3","cate_num":0}]
         */

        private String name;
        private String title;
        private String notice;
        private String avatar;
        private String status;
        private String sid;
        private String shop_info;
        private String shop_address;
        private String phone;
        private String grade;
        private String grade_total;
        private String is_open;
        private List<OpenTimeBean> open_time;
        private List<CategoryBean> category;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getShop_info() {
            return shop_info;
        }

        public void setShop_info(String shop_info) {
            this.shop_info = shop_info;
        }

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getGrade_total() {
            return grade_total;
        }

        public void setGrade_total(String grade_total) {
            this.grade_total = grade_total;
        }

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public List<OpenTimeBean> getOpen_time() {
            return open_time;
        }

        public void setOpen_time(List<OpenTimeBean> open_time) {
            this.open_time = open_time;
        }

        public List<CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryBean> category) {
            this.category = category;
        }

        public static class OpenTimeBean {
            /**
             * week : 全天
             * hour : 00:00:00-23:59:59
             */

            private String week;
            private String hour;

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getHour() {
                return hour;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }
        }

        public static class CategoryBean {
            /**
             * name : 热销榜
             * icon :
             * id : 0
             * cart_num : 0
             * level : 0
             * cate_num : 0
             */

            private String name;
            private String icon;
            private String id;
            private String cart_num;
            private String level;
            private String cate_num;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCart_num() {
                return cart_num;
            }

            public void setCart_num(String cart_num) {
                this.cart_num = cart_num;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getCate_num() {
                return cate_num;
            }

            public void setCate_num(String cate_num) {
                this.cate_num = cate_num;
            }
        }
    }

    public static class BannersBean {
        /**
         * name : 宿舍小店V2.0.0
         * img : http://img.wobianmall.com/uploads/2019/08/22/ce7d0ef3641208744edd20544931e01c.png
         * start_time : 1564588800
         * end_time : 1756569600
         * params : {"type":2,"url":"http://testh5.wobianmall.com/brand?activity_id=30&from=singlemessage&isappinstalled=0"}
         */

        private String name;
        private String img;
        private String start_time;
        private String end_time;
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

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public static class ParamsBean {
            /**
             * type : 2
             * url : http://testh5.wobianmall.com/brand?activity_id=30&from=singlemessage&isappinstalled=0
             */

            private String type;
            private String url;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
