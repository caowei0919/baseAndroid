package com.zj.wz.wbyx.wbyxAndroid.view;


import android.view.View;

import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllListBean;

/**
 * FileName: HomeOfMarketView
 * Author: 曹伟
 * Date: 2019/11/8 10:26
 * Description:首页底部窝边超市V层
 */

public interface HomeOfMarketView extends MvpView {

    void getOnGoodsSuccess(HomeGoodsOfAllListBean response);

    void getGoodDetailSucccess(GoodDetailBean baseBean, View addCar);

    void recommendAddCarSuccess(View addCar);

    void marketAddCarSuccess(View addCar);
}
