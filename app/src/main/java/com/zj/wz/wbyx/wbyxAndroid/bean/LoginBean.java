package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

/**
 * FileName: LoginBean
 * Author: 曹伟
 * Date: 2019/9/16 18:01
 * Description:
 */
@Keep
public class LoginBean {

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * phone : 1300000
     * token : sefgwerge5h
     * dorm : 宿舍地址
     * is_vip : 1是0否
     * is_new : 1是0否
     * is_guest : 1是0否
     */

    private String phone;   //手机号
    private String token;   //token
    private String key ;    //微信授权绑定的key
    private String dorm;    //宿舍地址
    private String is_vip;  //是否为vip，1是，0或者2为否
    private String is_new;  //是否为新用户，1是
    private String is_guest;    //是否为游客 （Android段用不了，ios审核用）

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public boolean getIs_vip() {
        return is_vip.equals("1");
    }

    public void setIs_vip(String is_vip) {
        this.is_vip = is_vip;
    }

    public boolean getIs_new() {
        return is_new.equals("1");
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getIs_guest() {
        return is_guest;
    }

    public void setIs_guest(String is_guest) {
        this.is_guest = is_guest;
    }
}
