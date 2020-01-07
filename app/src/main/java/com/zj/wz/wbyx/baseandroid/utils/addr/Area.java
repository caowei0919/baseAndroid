package com.zj.wz.wbyx.baseandroid.utils.addr;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * FileName: Area
 * Author: 曹伟
 * Date: 2019/10/8 17:44
 * Description:区
 */

public class Area implements IPickerViewData {
    private String name ; //名称
    private String city_id  ;   //对应城市id
    private String area_id ;    //区域id

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

    @Override
    public String getPickerViewText() {
        return null;
    }
}
