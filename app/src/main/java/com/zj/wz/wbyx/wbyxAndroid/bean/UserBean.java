package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

/**
 * FileName: UserBean
 * Author: 曹伟
 * Date: 2019/9/22 17:03
 * Description: 用户基础信息
 */

@Keep
public class UserBean {

    /**
     * phone : 13776054690
     * dorm : 测试学校一测试3
     * is_vip : 0
     * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/78vDM2vOOAjppX0I1ZOQxrrXeBgLqV6LaYojv3L4vSMG9Q2vHs6fsdEn8wQrvXgEulJjJNbnvBfbxzakv3ajBw/132
     * nickname : 哈哈
     * exp_time :
     * reg_time : 2019-08-30
     * sc : 1
     * is_landlord : 4
     * economize_price : 0.00
     * reg_day : 0
     * is_guest : 0
     */

    private String phone;
    private String dorm;
    private int is_vip;
    private String avatar;
    private String nickname;
    private String exp_time;
    private String reg_time;
    private int sc;
    private int is_landlord;
    private String economize_price;
    private int reg_day;
    private String is_guest;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public int getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(int is_vip) {
        this.is_vip = is_vip;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getExp_time() {
        return exp_time;
    }

    public void setExp_time(String exp_time) {
        this.exp_time = exp_time;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public int getSc() {
        return sc;
    }

    public void setSc(int sc) {
        this.sc = sc;
    }

    public int getIs_landlord() {
        return is_landlord;
    }

    public void setIs_landlord(int is_landlord) {
        this.is_landlord = is_landlord;
    }

    public String getEconomize_price() {
        return economize_price;
    }

    public void setEconomize_price(String economize_price) {
        this.economize_price = economize_price;
    }

    public int getReg_day() {
        return reg_day;
    }

    public void setReg_day(int reg_day) {
        this.reg_day = reg_day;
    }

    public String getIs_guest() {
        return is_guest;
    }

    public void setIs_guest(String is_guest) {
        this.is_guest = is_guest;
    }
}
