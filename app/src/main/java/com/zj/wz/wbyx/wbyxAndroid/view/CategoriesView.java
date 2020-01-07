package com.zj.wz.wbyx.wbyxAndroid.view;


import android.view.View;

import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodSortListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemListBean;

/**
 * FileName: CategoriesView
 * Author: 曹伟
 * Date: 2019/11/7 20:40
 * Description:商品分类V层
 */

public interface CategoriesView extends MvpView {
    /**
     * 获取顶级分类菜单成功
     * @param response
     */
    void getMenuLeftSuccess(MenuItemListBean response);

    /**
     * 获取菜单项下商品成功
     * @param response
     */
    void getGoodSortSuccess(GoodSortListBean response);

    /**
     * 获取商品详情成功
     * @param baseBean
     * @param addCar
     */
    void getGoodDetailSucccess(GoodDetailBean baseBean, View addCar);

    /**
     * 推荐商品加入购物车
     * @param addCar
     */
    void recommendAddCarSuccess(View addCar);

    /**
     * 超市商品加入购物车
     * @param addCar
     */
    void marketAddCarSuccess(View addCar);
}
