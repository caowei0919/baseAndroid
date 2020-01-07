package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

/**
 * FileName: InviteCheckBean
 * Author: 曹伟
 * Date: 2019/10/6 15:41
 * Description:邀请人查询
 */

@Keep
public class InviteCheckBean {

    /**
     * cellphone : 17349774478
     * avatar : http://img.wobianmall.com/static/mall/20190326/513b84ca4bebaa3dfabd24e1c0bde6d8.jpg
     * nickname : 就行
     */

    private String cellphone;
    private String avatar;
    private String nickname;

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
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
}
