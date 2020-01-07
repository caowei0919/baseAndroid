package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

/**
 * FileName: UpdataUserBean
 * Author: 曹伟
 * Date: 2019/9/30 15:08
 * Description:修改用户信息
 */
@Keep
public class UpdataUserBean {


    /**
     * avatar : http://img.wobianmall.com/static/mall/20190326/513b84ca4bebaa3dfabd24e1c0bde6d8.jpg
     * nickname : 就行
     * sex : 2
     * birthday : 2019-06-01
     * cellphone : 17349774478
     * invite_phone : 13776054690
     * app_openid :
     * binding_wx : 0
     * invite_name : 啦啦啦嗯啦
     */

    private String avatar;
    private String nickname;
    private String sex;
    private String birthday;
    private String cellphone;
    private String invite_phone;
    private String app_openid;
    private int binding_wx;
    private String invite_name;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getInvite_phone() {
        return invite_phone;
    }

    public void setInvite_phone(String invite_phone) {
        this.invite_phone = invite_phone;
    }

    public String getApp_openid() {
        return app_openid;
    }

    public void setApp_openid(String app_openid) {
        this.app_openid = app_openid;
    }

    public int getBinding_wx() {
        return binding_wx;
    }

    public void setBinding_wx(int binding_wx) {
        this.binding_wx = binding_wx;
    }

    public String getInvite_name() {
        return invite_name;
    }

    public void setInvite_name(String invite_name) {
        this.invite_name = invite_name;
    }
}
