package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;

import java.util.List;

/**
 * FileName: ApplyBuildBean
 * Author: 曹伟
 * Date: 2019/10/6 18:01
 * Description: 申请楼长
 */
@Keep
public class ApplyBuildBean {

    /**
     * list : [{"school_name":"测试学校一","school_id":"96","name":"韩国反反复复发发发","phone":"17349774478","address_detail":"hgffg","status":"3","share_phone":"15651775527"}]
     * phone : 025-52350225
     */

    private String phone;
    private List<ListBean> list;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * school_name : 测试学校一
         * school_id : 96
         * name : 韩国反反复复发发发
         * phone : 17349774478
         * address_detail : hgffg
         * status : 3   1 是 2被拒 3审核中 4否
         * share_phone : 15651775527
         */

        private String school_name;
        private String school_id;
        private String name;
        private String phone;
        private String address_detail;
        private String status;
        private String share_phone;

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShare_phone() {
            return share_phone;
        }

        public void setShare_phone(String share_phone) {
            this.share_phone = share_phone;
        }
    }
}
