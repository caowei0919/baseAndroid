package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BuyVipBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.VipProducBean;
import com.zj.wz.wbyx.wbyxAndroid.view.BuyMembershipView;

import javax.inject.Inject;


/**
 * FileName: BuyMembershipPresenter
 * Author: 曹伟
 * Date: 2019/11/19 20:47
 * Description:购买会员P层
 */

public class BuyMembershipPresenter extends BasePresenter<BuyMembershipView> {
    MyApi api ;

    @Inject
    public BuyMembershipPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取当前用户可购买Vip套餐
     */
    public void getVipProduct() {
        api.getVipProduct()
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<VipProducBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<VipProducBean> baseBean) {
                        getView().getVipProductSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 创建支付订单
     * @param vipId 套餐id
     * @param payType   支付方式
     * @param appIp ip地址
     */
    public void setBuyVip(String vipId, String payType, String appIp) {
        api.setBuyVip(vipId,payType,appIp)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<BuyVipBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<BuyVipBean> baseBean) {
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
