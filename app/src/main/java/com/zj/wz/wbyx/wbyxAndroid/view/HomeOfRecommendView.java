package com.zj.wz.wbyx.wbyxAndroid.view;


import android.view.View;

import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllListBean;

/**
 * FileName: HomeOfRecommendView
 * Author: 曹伟
 * Date: 2019/11/8 10:30
 * Description:首页底部推荐V层
 */

public interface HomeOfRecommendView extends MvpView {
    void getOnGoodsSuccess(HomeGoodsOfAllListBean response);

    void marketAddCarSuccess(View addCar);

    void recommendAddCarSuccess(View addCar);

    void getGoodDetailSucccess(GoodDetailBean baseBean, View addCar);
}
