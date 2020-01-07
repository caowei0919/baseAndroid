package com.zj.wz.wbyx.wbyxAndroid.presenter;

import android.view.View;

import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.AddShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllListBean;
import com.zj.wz.wbyx.wbyxAndroid.view.HomeOfAllView;

import java.util.Map;

import javax.inject.Inject;


/**
 * FileName: HomeOfAllPresenter
 * Author: 曹伟
 * Date: 2019/11/8 10:22
 * Description:首页底部P层
 */

public class HomeOfAllPresenter extends BasePresenter<HomeOfAllView> {
    MyApi api ;

    @Inject
    public HomeOfAllPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取首页底部展示商品
     * @param adCode    区域编码
     * @param goodsType 商品类型
     * @param page  页码
     */
    public void getHomeGoods(String adCode, String goodsType, int page,int pageNum) {
        api.getHomeGoods(adCode,goodsType,page,pageNum)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<HomeGoodsOfAllListBean>>(){
                    @Override
                    public void onNext(BaseBean<HomeGoodsOfAllListBean> baseBean) {
                        getView().getOnGoodsSuccess(baseBean.getResponse());
                    }
                });
    }

    /**
     * 获取商品详情信息
     * @param map
     */
    public void getGoodsDetail(View addCar, Map<String, String> map) {
        api.getGoodsDetail(map)
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
        api.recommendAddCar(params)
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
        api.marketAddCar(marketparams)
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
