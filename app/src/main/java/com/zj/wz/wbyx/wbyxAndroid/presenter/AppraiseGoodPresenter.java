package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.view.AppraiseGoodView;

import javax.inject.Inject;


/**
 * FileName: AppraiseGoodPresenter
 * Author: 曹伟
 * Date: 2019/11/20 19:27
 * Description:商品评价P层
 */

public class AppraiseGoodPresenter extends BasePresenter<AppraiseGoodView> {
    MyApi api ;


    @Inject
    public AppraiseGoodPresenter(MyApi api) {
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
                        getView().getOrderGoodSuccess(baseBean.getResponse().getOrder().getGoods());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
