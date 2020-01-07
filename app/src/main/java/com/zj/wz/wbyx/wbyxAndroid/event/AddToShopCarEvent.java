package com.zj.wz.wbyx.wbyxAndroid.event;

import android.support.annotation.Keep;
import android.view.View;

/**
 * FileName: AddToShopCarEvent
 * Author: 曹伟
 * Date: 2019/11/27 14:27
 * Description: 加入购物车成功事件
 */
@Keep
public class AddToShopCarEvent {
    private boolean hasAnim = false ;
    private View addShopCar ;

    public boolean isHasAnim() {
        return hasAnim;
    }

    public void setHasAnim(boolean hasAnim) {
        this.hasAnim = hasAnim;
    }

    public View getAddShopCar() {
        return addShopCar;
    }

    public void setAddShopCar(View addShopCar) {
        this.addShopCar = addShopCar;
    }

    public AddToShopCarEvent(boolean hasAnim, View addShopCar) {
        this.hasAnim = hasAnim;
        this.addShopCar = addShopCar;
    }
}
