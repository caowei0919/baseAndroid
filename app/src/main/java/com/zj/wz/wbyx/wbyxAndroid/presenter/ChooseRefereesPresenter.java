package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefereesBean;
import com.zj.wz.wbyx.wbyxAndroid.view.ChooseRefereesView;

import javax.inject.Inject;


/**
 * FileName: ChooseRefereesPresenter
 * Author: 曹伟
 * Date: 2019/10/8 14:13
 * Description:选择联系人P层
 */

public class ChooseRefereesPresenter extends BasePresenter<ChooseRefereesView> {
    MyApi api ;

    @Inject
    public ChooseRefereesPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取推荐人
     */
    public void applyBuild() {
        api.applyBuild()
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<RefereesBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<RefereesBean> baseBean) {
                        getView().applyBuildSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
