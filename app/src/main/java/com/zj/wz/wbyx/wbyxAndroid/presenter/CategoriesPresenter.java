package com.zj.wz.wbyx.wbyxAndroid.presenter;

import android.view.View;

import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.AddShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodSortListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemListBean;
import com.zj.wz.wbyx.wbyxAndroid.view.CategoriesView;

import java.util.Map;

import javax.inject.Inject;


/**
 * FileName: CategoriesPresenter
 * Author: 曹伟
 * Date: 2019/11/7 20:40
 * Description:商品分类P层
 */

public class CategoriesPresenter extends BasePresenter<CategoriesView> {
    MyApi api ;

    @Inject
    public CategoriesPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取分类菜单
     * @param hot 非必填，传1为没有热销的分类列表。
     * @param type 1:推荐 2：超市
     * @param area_id   区域id
     */
    public void getMenu(int hot, String type, String area_id) {
        api.getMenu(hot+"",type,area_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<MenuItemListBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<MenuItemListBean> baseBean) {
                        getView().getMenuLeftSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取菜单项下商品列表
     * @param cateId
     * @param area_id
     */
    public void getGoodsSort(String cateId, String area_id) {
        api.getGoodsSort(cateId,area_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<GoodSortListBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<GoodSortListBean> baseBean) {
                        getView().getGoodSortSuccess(baseBean.getResponse());
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
