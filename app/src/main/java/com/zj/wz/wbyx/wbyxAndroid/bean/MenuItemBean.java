package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * FileName: MenuBean
 * Author: 曹伟
 * Date: 2019/11/9 15:53
 * Description:顶级-分类菜单
 */
@Keep
public class MenuItemBean implements Serializable{

    /**
     * id : 89
     * name : 包
     * reid : 0
     * sortnum : 2000
     * image : http://img.wobianmall.com/uploads/2019/08/22/f90b2c0d77eccf6df83f40bdea3cb4d2.png
     * flag : 0
     * is_active : 1
     */

    private String id;
    private String name;
    private String reid;
    private String sortnum;
    private String image;
    private String flag;
    private String is_active;

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

    public String getReid() {
        return reid;
    }

    public void setReid(String reid) {
        this.reid = reid;
    }

    public String getSortnum() {
        return sortnum;
    }

    public void setSortnum(String sortnum) {
        this.sortnum = sortnum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
}
