package com.zj.wz.wbyx.wbyxAndroid.bean;

/**
 * FileName: SchoolBean
 * Author: 曹伟
 * Date: 2019/10/10 15:20
 * Description:抽离出的学校实体
 */

public class SchoolBean {

    public SchoolBean(String school_id, String name) {
        this.school_id = school_id;
        this.name = name;
    }

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
