package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderListBean;
import com.zj.wz.wbyx.wbyxAndroid.view.OrderView;

import javax.inject.Inject;


/**
 * FileName: OrderPresenter
 * Author: 曹伟
 * Date: 2019/10/22 11:05
 * Description:订单p层
 */

public class OrderPresenter extends BasePresenter<OrderView> {
    MyApi api ;

    @Inject
    public OrderPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 查询订单
     * @param status  订单状态
     * @param page  页码
     * @param num   单页数量
     */
    public void getOrder(int status , int page, int num) {
        api.getOrder(status,page,num)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<OrderListBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<OrderListBean> baseBean) {
                        if(baseBean.getResponse() != null)
                            getView().getOrderListSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
