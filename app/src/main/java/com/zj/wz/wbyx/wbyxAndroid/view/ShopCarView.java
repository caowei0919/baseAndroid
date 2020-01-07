package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.ShopCarBean;

/**
 * FileName: ShopCarView
 * Author: 曹伟
 * Date: 2019/9/20 21:23
 * Description:购物车View层
 */

public interface ShopCarView extends MvpView {
    /**
     * 获取购物车商品成功
     * @param response
     */
    void getShopCartShopSuccess(ShopCarBean response);

    /**
     * 删除购物车选中成功
     */
    void deleteSelectedSuccess();

}
