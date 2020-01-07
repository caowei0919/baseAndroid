package com.zj.wz.wbyx.wbyxAndroid.presenter;

import android.view.View;

import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.AddShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MarketGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.view.MarketFragmentView;

import java.util.Map;

import javax.inject.Inject;


/**
 * FileName: MarketFragmentPresenter
 * Author: 曹伟
 * Date: 2019/11/12 9:53
 * Description:
 */

public class MarketFragmentPresenter extends BasePresenter<MarketFragmentView> {
    MyApi api ;

    @Inject
    public MarketFragmentPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取商品列表
     * @param areaId    区域id
     * @param id        种类id
     * @param goodsType 商品类型
     */
    public void getGoodList(String areaId, String id, String goodsType) {
        api.getGoodList(areaId,id,goodsType)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<MarketGoodsListBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<MarketGoodsListBean> baseBean) {
                        getView().getGoodsListSuccess(baseBean.getResponse());
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
