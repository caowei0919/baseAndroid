package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.AdvListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MarketGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemListBean;

/**
 * FileName: RecommendView
 * Author: 曹伟
 * Date: 2019/11/9 10:00
 * Description:推荐商城V层
 */

public interface RecommendView extends MvpView {
    /**
     * 获取分类菜单成功
     * @param response
     */
    void getMenuSuccess(MenuItemListBean response);

    /**
     * 获取广告图成功
     * @param response
     */
    void getAdvSuccess(AdvListBean response);

    /**
     * 获取商品成功
     * @param response
     */
    void getGoodsListSuccess(MarketGoodsListBean response);
}
