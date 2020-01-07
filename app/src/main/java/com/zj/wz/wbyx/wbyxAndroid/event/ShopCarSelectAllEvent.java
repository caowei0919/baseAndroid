package com.zj.wz.wbyx.wbyxAndroid.event;

import android.support.annotation.Keep;

/**
 * FileName: ShopCarSelectAllEvent
 * Author: 曹伟
 * Date: 2019/10/25 16:39
 * Description: 购物车全选事件
 */
@Keep
public class ShopCarSelectAllEvent {
    private boolean isSelectAll ;

    public boolean isSelectAll() {
        return isSelectAll;
    }

    public void setSelectAll(boolean selectAll) {
        isSelectAll = selectAll;
    }

    public ShopCarSelectAllEvent(boolean b) {
        isSelectAll = b ;
    }
}
