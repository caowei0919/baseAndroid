package com.zj.wz.wbyx.wbyxAndroid.presenter;


import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ListVersionBean;
import com.zj.wz.wbyx.wbyxAndroid.view.SettingView;

import javax.inject.Inject;

/**
 * FileName: SettingPresenter
 * Author: 曹伟
 * Date: 2019/10/10 18:37
 * Description:设置P层
 */

public class SettingPresenter extends BasePresenter<SettingView> {
    MyApi api ;

    @Inject
    public SettingPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 检查版本
     */
    public void checkVersion() {
        api.checkVersion()
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<ListVersionBean>>(){
                    @Override
                    public void onNext(BaseBean<ListVersionBean> baseBean) {
                        if(baseBean.getResponse() != null && baseBean.getResponse().size() > 0)
                            getView().checkVersionSuccess(baseBean.getResponse().get(0));
                    }
                });
    }
}
