package com.zj.wz.wbyx.wbyxAndroid.view;


import android.view.View;

import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllListBean;

/**
 * FileName: HomeOfAllView
 * Author: 曹伟
 * Date: 2019/11/8 10:22
 * Description:首页底部全部V层
 */

public interface HomeOfAllView extends MvpView {
    /**
     * 获取首页全部商品成功
     * @param response
     */
    void getOnGoodsSuccess(HomeGoodsOfAllListBean response);

    /**
     * 获取详情成功
     * @param baseBean
     * @param addCar
     */
    void getGoodDetailSucccess(GoodDetailBean baseBean, View addCar);

    /**
     * 推荐加入购物车
     * @param addCar
     */
    void recommendAddCarSuccess(View addCar);

    /**
     * 商城加入购物车
     * @param addCar
     */
    void marketAddCarSuccess(View addCar);
}
