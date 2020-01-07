package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;

/**
 * FileName: CategoriesBean
 * Author: 曹伟
 * Date: 2019/10/28 19:08
 * Description:分类
 */
@Keep
public class CategoriesBean {

    /**
     * name : 清仓特卖分类
     * icon : http://img.wobianmall.com/uploads/2019/12/09/d40a93eb692d6d8569e03282f48953b4.jpg
     * url :
     * jump : 0
     * type : 2
     * cate_id : 0
     * region : 0
     * province_id : 0
     * city_id : 0
     * area_id : 0
     */

    private String name;
    private String icon;
    private String url;
    private String jump;
    private String type;
    private String cate_id;
    private String region;
    private String province_id;
    private String city_id;
    private String area_id;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
}
