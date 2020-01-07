package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.LoginBean;
import com.zj.wz.wbyx.wbyxAndroid.view.BindPhoneView;

import javax.inject.Inject;


/**
 * FileName: WXEntryPresenter
 * Author: 曹伟
 * Date: 2019/11/25 13:36
 * Description:
 */

public class BindPhonePresenter extends BasePresenter<BindPhoneView> {
    MyApi api ;

    @Inject
    public BindPhonePresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 通过手机号获取验证码
     * @param mPhoneNum
     */
    public void sendMessage(String mPhoneNum) {
        api.sendMsg(mPhoneNum)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean bean) {
                        getView().sendMessageSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        getView().sendMessageFailed();
                    }
                });
    }

    /**
     * 微信绑定手机号
     * @param key
     * @param phone
     * @param code
     */
    public void WxLogin(String key, String phone, String code) {
        api.WxLogin(key,phone,code)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<LoginBean>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<LoginBean> bean) {
                        PLog.e(bean.getResponse().getToken());
                        Constant.savaToken(bean.getResponse().getToken());
                        Constant.setVipUser(bean.getResponse().getIs_vip());
                        Constant.setNewUser(bean.getResponse().getIs_new());
                        getView().LoginSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}