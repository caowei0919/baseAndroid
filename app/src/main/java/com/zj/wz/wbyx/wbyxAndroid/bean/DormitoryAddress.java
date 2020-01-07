package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * FileName: DormitoryAddress
 * Author: 曹伟
 * Date: 2019/10/17 18:54
 * Description:宿舍地址
 */
@Keep
public class DormitoryAddress implements Serializable{

    /**
     * type : 1
     * id : 15281
     * name : hgfg
     * cellphone : 13776054690
     * address_detail : 测试学校一测试24测试学校一测试24
     * is_default : 1
     * pname : null
     * province_id : null
     * cname : null
     * city_id : null
     * aname : null
     * area_id : null
     * school_id : 96
     * school_name : 测试学校一
     * building_id : 1121
     * building_name : 测试24
     * room_num : 测试学校一测试24
     */

    private String type;
    private String id;
    private String name;
    private String cellphone;
    private String address_detail;
    private String is_default;
    private Object pname;
    private Object province_id;
    private Object cname;
    private Object city_id;
    private Object aname;
    private String area_id;
    private String school_id;
    private String school_name;
    private String building_id;
    private String building_name;
    private String room_num;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public Object getPname() {
        return pname;
    }

    public void setPname(Object pname) {
        this.pname = pname;
    }

    public Object getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Object province_id) {
        this.province_id = province_id;
    }

    public Object getCname() {
        return cname;
    }

    public void setCname(Object cname) {
        this.cname = cname;
    }

    public Object getCity_id() {
        return city_id;
    }

    public void setCity_id(Object city_id) {
        this.city_id = city_id;
    }

    public Object getAname() {
        return aname;
    }

    public void setAname(Object aname) {
        this.aname = aname;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(String building_id) {
        this.building_id = building_id;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }
}
