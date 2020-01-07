package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: ChooseSchool
 * Author: 曹伟
 * Date: 2019/10/10 14:43
 * Description:当前城市附近学校和省市区
 */
@Keep
public class ChooseSchool {

    private List<RegionBean> region;
    private SchoolBean school;

    public SchoolBean getSchool() {
        return school;
    }

    public void setSchool(SchoolBean school) {
        this.school = school;
    }

    public List<RegionBean> getRegion() {
        return region;
    }

    public void setRegion(List<RegionBean> region) {
        this.region = region;
    }

    public static class SchoolBean {
        /**
         * current_page : 1
         * data : [{"school_id":"96","name":"测试学校一","alphabet":null,"area_id":"310113","address_detail":"泰德科技园"}]
         * first_page_url : http://testapp.wobianmall.com/user/chooseSchool?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://testapp.wobianmall.com/user/chooseSchool?page=1
         * next_page_url : null
         * path : http://testapp.wobianmall.com/user/chooseSchool
         * per_page : 100
         * prev_page_url : null
         * to : 1
         * total : 1
         */

        private String current_page;
        private String first_page_url;
        private String from;
        private String last_page;
        private String last_page_url;
        private String next_page_url;
        private String path;
        private String per_page;
        private String prev_page_url;
        private String to;
        private String total;
        private List<DataBean> data;

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public String getFirst_page_url() {
            return first_page_url;
        }

        public void setFirst_page_url(String first_page_url) {
            this.first_page_url = first_page_url;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getLast_page() {
            return last_page;
        }

        public void setLast_page(String last_page) {
            this.last_page = last_page;
        }

        public String getLast_page_url() {
            return last_page_url;
        }

        public void setLast_page_url(String last_page_url) {
            this.last_page_url = last_page_url;
        }

        public String getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(String next_page_url) {
            this.next_page_url = next_page_url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public String getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(String prev_page_url) {
            this.prev_page_url = prev_page_url;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * school_id : 96
             * name : 测试学校一
             * alphabet : null
             * area_id : 310113
             * address_detail : 泰德科技园
             */

            private String school_id;
            private String name;
            private String alphabet;
            private String area_id;
            private String address_detail;

            public String getSchool_id() {
                return school_id;
            }

            public void setSchool_id(String school_id) {
                this.school_id = school_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlphabet() {
                return alphabet;
            }

            public void setAlphabet(String alphabet) {
                this.alphabet = alphabet;
            }

            public String getArea_id() {
                return area_id;
            }

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }

            public String getAddress_detail() {
                return address_detail;
            }

            public void setAddress_detail(String address_detail) {
                this.address_detail = address_detail;
            }
        }
    }

    public static class RegionBean {
        /**
         * name : 北京市
         * province_id : 110000
         * city : [{"name":"市辖区","province_id":"110000","city_id":"110100","area":[{"name":"东城区","city_id":"110100","area_id":"110101"},{"name":"西城区","city_id":"110100","area_id":"110102"},{"name":"朝阳区","city_id":"110100","area_id":"110105"},{"name":"丰台区","city_id":"110100","area_id":"110106"},{"name":"石景山区","city_id":"110100","area_id":"110107"},{"name":"海淀区","city_id":"110100","area_id":"110108"},{"name":"门头沟区","city_id":"110100","area_id":"110109"},{"name":"房山区","city_id":"110100","area_id":"110111"},{"name":"通州区","city_id":"110100","area_id":"110112"},{"name":"顺义区","city_id":"110100","area_id":"110113"},{"name":"昌平区","city_id":"110100","area_id":"110114"},{"name":"大兴区","city_id":"110100","area_id":"110115"},{"name":"怀柔区","city_id":"110100","area_id":"110116"},{"name":"平谷区","city_id":"110100","area_id":"110117"},{"name":"密云区","city_id":"110100","area_id":"110118"},{"name":"延庆区","city_id":"110100","area_id":"110119"}]}]
         */

        private String name;
        private String province_id;
        private List<CityBean> city;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * name : 市辖区
             * province_id : 110000
             * city_id : 110100
             * area : [{"name":"东城区","city_id":"110100","area_id":"110101"},{"name":"西城区","city_id":"110100","area_id":"110102"},{"name":"朝阳区","city_id":"110100","area_id":"110105"},{"name":"丰台区","city_id":"110100","area_id":"110106"},{"name":"石景山区","city_id":"110100","area_id":"110107"},{"name":"海淀区","city_id":"110100","area_id":"110108"},{"name":"门头沟区","city_id":"110100","area_id":"110109"},{"name":"房山区","city_id":"110100","area_id":"110111"},{"name":"通州区","city_id":"110100","area_id":"110112"},{"name":"顺义区","city_id":"110100","area_id":"110113"},{"name":"昌平区","city_id":"110100","area_id":"110114"},{"name":"大兴区","city_id":"110100","area_id":"110115"},{"name":"怀柔区","city_id":"110100","area_id":"110116"},{"name":"平谷区","city_id":"110100","area_id":"110117"},{"name":"密云区","city_id":"110100","area_id":"110118"},{"name":"延庆区","city_id":"110100","area_id":"110119"}]
             */

            private String name;
            private String province_id;
            private String city_id;
            private List<AreaBean> area;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvince_id() {
                return province_id;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public List<AreaBean> getArea() {
                return area;
            }

            public void setArea(List<AreaBean> area) {
                this.area = area;
            }

            public static class AreaBean {
                /**
                 * name : 东城区
                 * city_id : 110100
                 * area_id : 110101
                 */

                private String name;
                private String city_id;
                private String area_id;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCity_id() {
                    return city_id;
                }

                public void setCity_id(String city_id) {
                    this.city_id = city_id;
                }

                public String getArea_id() {
                    return area_id;
                }

                public void setArea_id(String area_id) {
                    this.area_id = area_id;
                }
            }
        }
    }
}
