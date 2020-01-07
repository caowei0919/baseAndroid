package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.CarFootBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.UserBean;
import com.zj.wz.wbyx.wbyxAndroid.view.MainView;

import javax.inject.Inject;


/**
 * FileName: MainPresenter
 * Author: 曹伟
 * Date: 2019/9/12 13:47
 * Description:
 */

public class MainPresenter extends BasePresenter<MainView>{
    MyApi api ;

    @Inject
    public MainPresenter(MyApi myApi) {
        this.api = myApi;
    }

    /**
     * 获取用户信息
     */
    public void getUser() {
        api.getUser()
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<UserBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<UserBean> baseBean) {
                        Constant.saveUser(baseBean.getResponse());
                        getView().getUserSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取购物车角标
     * @param city_id
     */
    public void getCarFoot(String city_id) {
        api.getCarFoot(city_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<CarFootBean>>(){
                    @Override
                    public void onNext(BaseBean<CarFootBean> baseBean) {
                        getView().getCarFootSuccess(baseBean.getResponse());
                    }
                });
    }
}
