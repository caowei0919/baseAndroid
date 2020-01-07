package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;

/**
 * FileName: BaseBean
 * Author: 曹伟
 * Date: 2019/9/16 17:20
 * Description:
 */

@Keep
public class BaseBean<T> {
    private T response;
    private String code;
    private String msg;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
