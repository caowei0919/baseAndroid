package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.LoginBean;
import com.zj.wz.wbyx.wbyxAndroid.view.LoginView;

import javax.inject.Inject;


/**
 * FileName: LoginPresenter
 * Author: 曹伟
 * Date: 2019/9/17 19:21
 * Description:
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    MyApi myApi ;

    @Inject
    public LoginPresenter(MyApi myApi) {
        this.myApi = myApi;
    }

    /**
     * 通过手机号获取验证码
     * @param mPhoneNum
     */
    public void sendMessage(String mPhoneNum) {
        myApi.sendMsg(mPhoneNum)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean bean) {
                        if(RxObserver.checkJsonCode(bean)){
                            getView().sendMessageSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        getView().sendMessageFailed();
                    }
                });
    }

    /**
     * 登录or注册
     * @param mPhoneNum 手机号
     * @param mMessageCode  验证码
     */
    public void loginOrRegister(String mPhoneNum, String mMessageCode) {
        myApi.loginOrRegister(mPhoneNum,mMessageCode)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<LoginBean>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<LoginBean> bean) {
                        if(RxObserver.checkJsonCode(bean)){
                            PLog.e(bean.getResponse().getToken());
                            Constant.savaToken(bean.getResponse().getToken());
                            Constant.setVipUser(bean.getResponse().getIs_vip());
                            Constant.setNewUser(bean.getResponse().getIs_new());
                            getView().LoginSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 根据微信code检查手机号是否绑定过微信
     * @param code
     */
    public void checkWxBind(String code) {
        myApi.checkWxBind(code)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<LoginBean>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<LoginBean> bean) {
                        if(RxObserver.checkJsonCode(bean)){
                            switch (bean.getCode()){
                                case Api.CODE_SUCCESS :
                                    PLog.e(bean.getResponse().getToken());
                                    Constant.savaToken(bean.getResponse().getToken());
                                    Constant.setVipUser(bean.getResponse().getIs_vip());
                                    Constant.setNewUser(bean.getResponse().getIs_new());
                                    getView().LoginSuccess();
                                    break;

                                case Api.CODE_NEED_BIND :
                                    getView().needBindPhone(bean.getResponse().getKey());
                                    PLog.e(bean.getResponse().getKey()+"");
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
