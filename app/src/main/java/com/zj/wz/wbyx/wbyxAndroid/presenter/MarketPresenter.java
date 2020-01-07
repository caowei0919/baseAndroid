package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.AdvListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MarketGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemListBean;
import com.zj.wz.wbyx.wbyxAndroid.view.MarketView;

import javax.inject.Inject;


/**
 * FileName: MarketPresenter
 * Author: 曹伟
 * Date: 2019/11/9 10:08
 * Description:窝边超市P层
 */

public class MarketPresenter extends BasePresenter<MarketView> {
    MyApi api ;

    @Inject
    public MarketPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取窝边超市广告图
     * @param typeMarket
     */
    public void getAdv(int typeMarket) {
        api.getAdv(typeMarket)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<AdvListBean>>(){
                    @Override
                    public void onNext(BaseBean<AdvListBean> baseBean) {
                        if(baseBean.getResponse() != null && baseBean.getResponse().size() > 0)
                            getView().getAdvSuccess(baseBean.getResponse());
                    }
                });
    }

    /**
     * 获取分类菜单
     * @param hot 非必填，传1为没有热销的分类列表。
     * @param type 1:推荐 2：超市
     * @param area_id   区域id
     */
    public void getMenu(String hot, String type, String area_id) {
        api.getMenu(hot,type,area_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<MenuItemListBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<MenuItemListBean> baseBean) {
                        getView().getMenuSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
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
}
