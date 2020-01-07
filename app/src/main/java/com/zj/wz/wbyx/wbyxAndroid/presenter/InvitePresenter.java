package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.InviteCheckBean;
import com.zj.wz.wbyx.wbyxAndroid.view.InviteView;

import javax.inject.Inject;


/**
 * FileName: InvitePresenter
 * Author: 曹伟
 * Date: 2019/9/30 17:13
 * Description:
 */

public class InvitePresenter extends BasePresenter<InviteView> {
    MyApi myApi ;

    @Inject
    public InvitePresenter(MyApi myApi) {
        this.myApi = myApi;
    }

    /**
     * 查询邀请人手机号
     * @param invitePhone
     */
    public void checkInvitePhone(String invitePhone) {
        myApi.checkInvitePhone(invitePhone)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<InviteCheckBean>>(){
                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }

                    @Override
                    public void onNext(BaseBean<InviteCheckBean> inviteCheckBeanBaseBean) {
                        getView().checkInviteSuccess(inviteCheckBeanBaseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    public void BindInvite(String cellphone) {
        myApi.BindInvite(cellphone,"1")
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }

                    @Override
                    public void onNext(BaseBean inviteCheckBeanBaseBean) {
                        getView().BindSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
