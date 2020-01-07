package com.zj.wz.wbyx.wbyxAndroid.view;


import android.view.View;

import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MarketGoodsListBean;

/**
 * FileName: MarketFragmentView
 * Author: 曹伟
 * Date: 2019/11/12 9:52
 * Description:窝边超市底部fragment的V层
 */

public interface MarketFragmentView extends MvpView {
    /**
     * 获取商品列表成功
     * @param response
     */
    void getGoodsListSuccess(MarketGoodsListBean response);

    void getGoodDetailSucccess(GoodDetailBean baseBean, View addCar);

    void recommendAddCarSuccess(View addCar);

    void marketAddCarSuccess(View addCar);
}
