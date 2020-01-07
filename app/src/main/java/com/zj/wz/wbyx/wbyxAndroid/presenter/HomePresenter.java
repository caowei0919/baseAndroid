package com.zj.wz.wbyx.wbyxAndroid.presenter;

import android.view.View;

import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.AddShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeDateBean;
import com.zj.wz.wbyx.wbyxAndroid.view.HomeView;

import java.util.Map;

import javax.inject.Inject;


/**
 * FileName: HomePresenter
 * Author: 曹伟
 * Date: 2019/9/20 21:17
 * Description: 首页P层
 */

public class HomePresenter extends BasePresenter<HomeView>{
    MyApi myApi ;

    @Inject
    public HomePresenter(MyApi myApi) {
        this.myApi = myApi;
    }

    /**
     * 获取首页banner
     * @param area_id  区域id
     * @param modules  模块
     */
    public void getHomeDate(String area_id , String modules) {
        myApi.getHomeDate(area_id,modules)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<HomeDateBean>>() {
                    @Override
                    public void onComplete() {
                        getView().onComplete();
                    }

                    @Override
                    public void onNext(BaseBean<HomeDateBean> bean) {
                        if(checkJsonCode(bean)){
                            getView().getBannerSuccess(bean.getResponse().getBanners());
                            getView().getCategoriesSuccess(bean.getResponse().getCategories());
                            getView().getRecomSuccess(bean.getResponse().getRecom());
                            getView().getNewRecommendSuccess(bean.getResponse().getNewX());
                            getView().getGrouponSuccess(bean.getResponse().getGroupon());
                            getView().getSubjectsSuccess(bean.getResponse().getSubjects());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取商品详情信息
     * @param map
     */
    public void getGoodsDetail(View addCar,Map<String, String> map) {
        myApi.getGoodsDetail(map)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<GoodDetailBean>(){
                    @Override
                    public void onNext(GoodDetailBean baseBean) {
                        if(baseBean.getCode().equals(Api.CODE_SUCCESS)){
                            getView().getGoodDetailSucccess(baseBean,addCar);
                        }
                    }
                });
    }

    /**
     * 推荐商品加入购物车
     * @param params
     * @param addCar
     */
    public void recommendAddCar(Map<String, Object> params, View addCar) {
        myApi.recommendAddCar(params)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<AddShopCarBean>>(){
                    @Override
                    public void onNext(BaseBean<AddShopCarBean> baseBean) {
                        if(checkJsonCode(baseBean)){
                            getView().recommendAddCarSuccess(addCar);
                        }
                    }
                });
    }

    /**
     * 商城加入购物车
     * @param marketparams
     * @param addCar
     */
    public void marketAddCar(Map<String, Object> marketparams, View addCar) {
        myApi.marketAddCar(marketparams)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<AddShopCarBean>>(){
                    @Override
                    public void onNext(BaseBean<AddShopCarBean> baseBean) {
                        if(checkJsonCode(baseBean)){
                            getView().marketAddCarSuccess(addCar);
                        }
                    }
                });
    }
}
