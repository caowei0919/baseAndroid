package com.zj.wz.wbyx.wbyxAndroid.event;

import android.support.annotation.Keep;

/**
 * FileName: TagChangeEvent
 * Author: 曹伟
 * Date: 2019/11/28 17:09
 * Description:超市推荐商品标签点击事件
 */
@Keep
public class TagChangeEvent {
    private int index ;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TagChangeEvent(int index) {
        this.index = index;
    }
}
