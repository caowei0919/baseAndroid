package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.view.AppraiseShopView;

import javax.inject.Inject;


/**
 * FileName: AppraiseShopPresenter
 * Author: 曹伟
 * Date: 2019/11/20 19:23
 * Description:店铺评价P层
 */

public class AppraiseShopPresenter extends BasePresenter<AppraiseShopView> {
    MyApi api ;

    @Inject
    public AppraiseShopPresenter(MyApi api) {
        this.api = api;
    }


    public void submitAppraise(String id, int score, int anonymous) {
        api.submitAppraise(id,score,anonymous)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        getView().submitAppraiseSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
