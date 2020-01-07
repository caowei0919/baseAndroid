package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * FileName: BuildBean
 * Author: 曹伟
 * Date: 2019/10/17 10:20
 * Description:
 */

@Keep
public class BuildBean implements Serializable{

    /**
     * building_id : 695
     * name : 测试1
     * school_id : 96
     */

    private String building_id;
    private String name;
    private String school_id;

    public String getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(String building_id) {
        this.building_id = building_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }
}
