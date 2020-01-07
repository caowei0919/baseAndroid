package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefundDetailsBean;
import com.zj.wz.wbyx.wbyxAndroid.view.ConfirmRefundDetailsView;

import javax.inject.Inject;


/**
 * FileName: ConfirmRefundDetailsPresenter
 * Author: 曹伟
 * Date: 2019/11/20 17:21
 * Description:退款详情P层
 */

public class ConfirmRefundDetailsPresenter extends BasePresenter<ConfirmRefundDetailsView> {
    MyApi api ;

    @Inject
    public ConfirmRefundDetailsPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取退款详情
     * @param order_id
     */
    public void getRefundDetail(String order_id) {
        api.getRefundDetail(order_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<RefundDetailsBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<RefundDetailsBean> baseBean) {
                        getView().getRefundDetailsSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 取消退款
     * @param order_id
     */
    public void cancelRefund(String order_id) {
        api.cancelRefund(order_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        getView().cancelRefundSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
