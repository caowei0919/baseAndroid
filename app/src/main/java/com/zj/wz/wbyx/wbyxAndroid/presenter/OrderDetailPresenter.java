package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.view.OrderDetailView;

import javax.inject.Inject;


/**
 * FileName: OrderDetailPresenter
 * Author: 曹伟
 * Date: 2019/11/20 14:28
 * Description:订单详情P层
 */

public class OrderDetailPresenter extends BasePresenter<OrderDetailView> {
    MyApi api ;

    @Inject
    public OrderDetailPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取订单详情
     * @param order_id
     */
    public void getOrderDetail(String order_id) {
        api.getOrderDetail(order_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<OrderDetailBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<OrderDetailBean> baseBean) {
                        getView().getOrderDetailSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
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
}
