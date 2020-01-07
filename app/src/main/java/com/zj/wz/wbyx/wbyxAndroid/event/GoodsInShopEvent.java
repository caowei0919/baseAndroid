package com.zj.wz.wbyx.wbyxAndroid.event;

import android.support.annotation.Keep;

import com.zj.wz.wbyx.wbyxAndroid.bean.ShopCarBean;


/**
 * FileName: GoodsInShopEvent
 * Author: 曹伟
 * Date: 2019/10/28 9:30
 * Description:购物车商品相关事件
 */
@Keep
public class GoodsInShopEvent {
    public enum GoodsInShopEventTag{
        DELETE,
        ITEM ,
        ADD ,
        SUBTRACTION
    }

    private ShopCarBean.ShopBean.GoodsBean bean ;
    private GoodsInShopEventTag eventTag ;

    public GoodsInShopEvent(ShopCarBean.ShopBean.GoodsBean bean, GoodsInShopEventTag eventTag) {
        this.bean = bean;
        this.eventTag = eventTag;
    }

    public ShopCarBean.ShopBean.GoodsBean getBean() {
        return bean;
    }

    public void setBean(ShopCarBean.ShopBean.GoodsBean bean) {
        this.bean = bean;
    }

    public GoodsInShopEventTag getEventTag() {
        return eventTag;
    }

    public void setEventTag(GoodsInShopEventTag eventTag) {
        this.eventTag = eventTag;
    }
}
