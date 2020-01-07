package com.zj.wz.wbyx.wbyxAndroid.view;


import android.view.View;

import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.BannersBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.CategoriesBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GrouponBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.NewRecommendBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.RecomBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SubjectsBean;

import java.util.List;

/**
 * FileName: HomeView
 * Author: 曹伟
 * Date: 2019/9/20 21:16
 * Description:首页View层
 */

public interface HomeView extends MvpView {
    /**
     * 获取首页banners成功
     * @param banners
     */
    void getBannerSuccess(List<BannersBean> banners);

    /**
     * 获取分类成功
     * @param categories
     */
    void getCategoriesSuccess(List<CategoriesBean> categories);

    /**
     * 获取模块成功
     * @param recom
     */
    void getRecomSuccess(RecomBean recom);

    /**
     * 获取最新推荐成功
     * @param newX
     */
    void getNewRecommendSuccess(NewRecommendBean newX);

    /**
     * 获取拼团成功
     * @param groupon
     */
    void getGrouponSuccess(GrouponBean groupon);

    /**
     * 获取专题详情成功
     * @param subjects
     */
    void getSubjectsSuccess(List<SubjectsBean> subjects);

    /**
     * 加载数据完成
     */
    void onComplete();

    /**
     * 获取商品详情成功
     * @param baseBean
     * @param addCar
     */
    void getGoodDetailSucccess(GoodDetailBean baseBean, View addCar);

    /**
     * 推荐商品添加购物车成功
     * @param addCar
     */
    void recommendAddCarSuccess(View addCar);

    /**
     * 商城加入购物车成功
     * @param addCar
     */
    void marketAddCarSuccess(View addCar);
}
