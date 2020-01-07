package com.zj.wz.wbyx.wbyxAndroid.presenter;


import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.view.StartView;

import javax.inject.Inject;


/**
 * FileName: StartPresenter
 * Author: 曹伟
 * Date: 2019/9/17 17:51
 * Description:
 */

public class StartPresenter extends BasePresenter<StartView> {
    MyApi myApi ;

    @Inject
    public StartPresenter(MyApi myApi) {
        this.myApi = myApi;
    }

    /**
     * 获取启动页网络图片
     */
    public void getSplashImage() {
        myApi.getSplashImage()
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<String>>() {
                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
