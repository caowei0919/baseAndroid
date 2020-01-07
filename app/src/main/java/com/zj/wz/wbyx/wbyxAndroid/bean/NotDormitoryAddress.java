package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * FileName: NotDormitoryAddress
 * Author: 曹伟
 * Date: 2019/10/18 14:49
 * Description:非宿舍地址
 */
@Keep
public class NotDormitoryAddress implements Serializable{

    /**
     * type : 2
     * id : 15282
     * name : 广告费
     * cellphone : 17349774478
     * address_detail : 555445
     * is_default : 1
     * pname : 内蒙古自治区
     * province_id : 150000
     * cname : 呼和浩特市
     * city_id : 150100
     * aname : 市辖区
     * area_id : 150101
     */

    private String type;
    private String id;
    private String name;
    private String cellphone;
    private String address_detail;
    private String is_default;
    private String pname;
    private String province_id;
    private String cname;
    private String city_id;
    private String aname;
    private String area_id;

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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }
}
