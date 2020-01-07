package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ServiceBean;
import com.zj.wz.wbyx.wbyxAndroid.view.ServiceView;

import javax.inject.Inject;


/**
 * FileName: ServicePresenter
 * Author: 曹伟
 * Date: 2019/10/11 14:24
 * Description:服务条款P层
 */

public class ServicePresenter extends BasePresenter<ServiceView> {
    MyApi api ;

    @Inject
    public ServicePresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取服务条款内容
     * @param typeService
     */
    public void getServerWrite(int typeService) {
        api.getServerWrite(typeService)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<ServiceBean>>(){
                    @Override
                    public void onNext(BaseBean<ServiceBean> baseBean) {
                        getView().getServiceWriteSuccess(baseBean.getResponse());
                    }
                });
    }
}
