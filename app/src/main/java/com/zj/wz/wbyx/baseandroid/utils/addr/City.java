package com.zj.wz.wbyx.baseandroid.utils.addr;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * FileName: City
 * Author: 曹伟
 * Date: 2019/10/8 17:43
 * Description:市
 */

public class City implements IPickerViewData {
    private String name ;   //名称
    private String province_id ;    //对应省id
    private String city_id ;    //市id
    private List<Area> areas ;

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

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }
}
