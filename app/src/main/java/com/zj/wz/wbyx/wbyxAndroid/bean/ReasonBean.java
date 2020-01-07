package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

/**
 * FileName: ReasonBean
 * Author: 曹伟
 * Date: 2019/9/19 18:05
 * Description:
 */
@Keep
public class ReasonBean {
    private String id ;
    private String reason ;
    private boolean isCheck = false ;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ReasonBean(String id, String reason) {

        this.id = id;
        this.reason = reason;
    }
}
