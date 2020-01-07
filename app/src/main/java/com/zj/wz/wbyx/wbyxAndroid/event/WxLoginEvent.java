package com.zj.wz.wbyx.wbyxAndroid.event;

import android.support.annotation.Keep;

import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * FileName: WxLoginEvent
 * Author: 曹伟
 * Date: 2019/11/25 17:16
 * Description:
 */

@Keep
public class WxLoginEvent {
    private SendAuth.Resp baseResp ;

    public SendAuth.Resp getBaseResp() {
        return baseResp;
    }

    public void setBaseResp(SendAuth.Resp baseResp) {
        this.baseResp = baseResp;
    }

    public WxLoginEvent(SendAuth.Resp baseResp) {
        this.baseResp = baseResp ;
    }
}
