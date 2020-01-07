package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: SelectSchoolBean
 * Author: 曹伟
 * Date: 2019/10/10 14:29
 * Description:选择学校
 */
@Keep
public class SelectSchoolBean {

    /**
     * current_page : 1
     * data : [{"school_id":"96","province_id":"310000","city_id":"310100","area_id":"310113","address_detail":"泰德科技园","name":"测试学校一","already_develop":"25","building_count":"30","section_name":"","add_time":"1555571681","update_time":"1567478307","status":"1","alphabet":null},{"school_id":"104","province_id":"340000","city_id":"340100","area_id":"340104","address_detail":"测试区测试路测试弄A座","name":"测试学校二","already_develop":"3","building_count":"20","section_name":"","add_time":"1555643985","update_time":"1560242547","status":"1","alphabet":null}]
     * first_page_url : http://testapp.wobianmall.com/user/selectSchool?page=1
     * from : 1
     * last_page : 1
     * last_page_url : http://testapp.wobianmall.com/user/selectSchool?page=1
     * next_page_url : null
     * path : http://testapp.wobianmall.com/user/selectSchool
     * per_page : 20
     * prev_page_url : null
     * to : 2
     * total : 2
     */

    private int current_page;
    private String first_page_url;
    private int from;
    private int last_page;
    private String last_page_url;
    private String next_page_url;
    private String path;
    private int per_page;
    private String prev_page_url;
    private int to;
    private int total;
    private List<DataBean> data;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public String getFirst_page_url() {
        return first_page_url;
    }

    public void setFirst_page_url(String first_page_url) {
        this.first_page_url = first_page_url;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
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

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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
         * province_id : 310000
         * city_id : 310100
         * area_id : 310113
         * address_detail : 泰德科技园
         * name : 测试学校一
         * already_develop : 25
         * building_count : 30
         * section_name : 
         * add_time : 1555571681
         * update_time : 1567478307
         * status : 1
         * alphabet : null
         */

        private String school_id;
        private String province_id;
        private String city_id;
        private String area_id;
        private String address_detail;
        private String name;
        private String already_develop;
        private String building_count;
        private String section_name;
        private String add_time;
        private String update_time;
        private String status;
        private String alphabet;

        public String getSchool_id() {
            return school_id;
        }

        public void setSchool_id(String school_id) {
            this.school_id = school_id;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlready_develop() {
            return already_develop;
        }

        public void setAlready_develop(String already_develop) {
            this.already_develop = already_develop;
        }

        public String getBuilding_count() {
            return building_count;
        }

        public void setBuilding_count(String building_count) {
            this.building_count = building_count;
        }

        public String getSection_name() {
            return section_name;
        }

        public void setSection_name(String section_name) {
            this.section_name = section_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAlphabet() {
            return alphabet;
        }

        public void setAlphabet(String alphabet) {
            this.alphabet = alphabet;
        }
    }
}
