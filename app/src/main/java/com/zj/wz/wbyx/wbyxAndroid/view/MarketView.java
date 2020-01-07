package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.AdvListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MarketGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemListBean;

/**
 * FileName: MarketView
 * Author: 曹伟
 * Date: 2019/11/9 10:08
 * Description:窝边超市V层
 */

public interface MarketView extends MvpView {
    /**
     * 获取窝边超市广告图成功
     * @param response
     */
    void getAdvSuccess(AdvListBean response);

    /**
     * 获取顶级菜单成功
     * @param response
     */
    void getMenuSuccess(MenuItemListBean response);

    /**
     * 获取商品成功
     * @param response
     */
    void getGoodsListSuccess(MarketGoodsListBean response);
}
