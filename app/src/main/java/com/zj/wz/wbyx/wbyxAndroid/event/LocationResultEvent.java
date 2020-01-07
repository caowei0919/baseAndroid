package com.zj.wz.wbyx.wbyxAndroid.event;

import android.support.annotation.Keep;

/**
 * FileName: LocationResultEvent
 * Author: 曹伟
 * Date: 2019/10/8 11:26
 * Description:
 */

@Keep
public class LocationResultEvent {
    boolean mbResult;

    public LocationResultEvent(boolean bresult) {
        this.mbResult = bresult;
    }

    public boolean isMbResult() {
        return mbResult;
    }

    public void setMbResult(boolean mbResult) {
        this.mbResult = mbResult;
    }
}
