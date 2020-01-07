package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.AddShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SearchGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SearchHistoryListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.WareHouseListBean;
import com.zj.wz.wbyx.wbyxAndroid.event.UpDateCarFootEvent;
import com.zj.wz.wbyx.wbyxAndroid.view.SearchView;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import javax.inject.Inject;


/**
 * FileName: SearchPresenter
 * Author: 曹伟
 * Date: 2019/11/13 10:31
 * Description:搜索P层
 */

public class SearchPresenter extends BasePresenter<SearchView> {
    MyApi api ;

    @Inject
    public SearchPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取搜索历史
     */
    public void getSearchHistory() {
        api.getSearchHistory()
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<SearchHistoryListBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<SearchHistoryListBean> baseBean) {
                        if(checkJsonCode(baseBean)){
                            if(baseBean.getResponse() != null && baseBean.getResponse().size() > 0){
                                getView().getSearchHistorySuccess(baseBean.getResponse()) ;
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 情况搜索记录
     */
    public void delSearchHistory() {
        api.delSearchHistory("1")
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if(checkJsonCode(baseBean)){
                            getView().deleteHistorySuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 搜索商品
     * @param content
     * @param sort_type
     * @param warehouse_id
     * @param direstion
     * @param page
     * @param per_page
     */
    public void searchGoods(String content, String sort_type
            , String warehouse_id, String direstion, int page, int per_page) {
        api.searchGoods(content,sort_type,warehouse_id,direstion,page,per_page)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<SearchGoodsListBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<SearchGoodsListBean> baseBean) {
                        if(checkJsonCode(baseBean)){
                            getView().getSearchGoodsSuccess(baseBean.getResponse());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取附近城市仓id
     * @param longitude
     * @param latitude
     * @param adCode
     */
    public void getDayLocation(double longitude, double latitude, String adCode) {
        api.getDayLocation(longitude+"",latitude+"",adCode)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<WareHouseListBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<WareHouseListBean> baseBean) {
                        if(checkJsonCode(baseBean)){
                            if(baseBean.getResponse().size() > 0)
                                getView().getDayLocationSuccess(baseBean.getResponse().get(0));
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
    public void getGoodsDetail(Map<String, String> map) {
        api.getGoodsDetail(map)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<GoodDetailBean>(){
                    @Override
                    public void onNext(GoodDetailBean baseBean) {
                        if(baseBean.getCode().equals(Api.CODE_SUCCESS)){
                            getView().getGoodDetailSucccess(baseBean);
                        }
                    }
                });
    }

    /**
     * 推荐商品加入购物车
     * @param params
     */
    public void recommendAddCar(Map<String, Object> params) {
        api.recommendAddCar(params)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<AddShopCarBean>>(){
                    @Override
                    public void onNext(BaseBean<AddShopCarBean> baseBean) {
                        if(checkJsonCode(baseBean)){
                            ToastUtils.showLongToast(baseBean.getMsg());
                            EventBus.getDefault().post(new UpDateCarFootEvent());
                        }
                    }
                });
    }

    /**
     * 商城加入购物车
     * @param marketparams
     */
    public void marketAddCar(Map<String, Object> marketparams) {
        api.marketAddCar(marketparams)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<AddShopCarBean>>(){
                    @Override
                    public void onNext(BaseBean<AddShopCarBean> baseBean) {
                        if(checkJsonCode(baseBean)){
                            ToastUtils.showLongToast(baseBean.getMsg());
                            EventBus.getDefault().post(new UpDateCarFootEvent());
                        }
                    }
                });
    }
}
