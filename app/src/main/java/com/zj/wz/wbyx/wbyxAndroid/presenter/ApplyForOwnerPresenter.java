package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.ApplyBuildBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.view.ApplyForOwnerView;

import javax.inject.Inject;


/**
 * FileName: ApplyForOwnerPresenter
 * Author: 曹伟
 * Date: 2019/9/25 15:50
 * Description: 申请店长P层
 */

public class ApplyForOwnerPresenter extends BasePresenter<ApplyForOwnerView>{
    private MyApi api ;

    @Inject
    public ApplyForOwnerPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 查询申请状态
     */
    public void applyBuild(String s_id, String phone ,String name
            , String address_detail, String cellphone) {
        api.applyBuild(s_id,phone,name,address_detail,cellphone)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        getView().applyBuildSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取申请状态
     * @param s_id
     * @param name
     * @param phone
     * @param address_detail
     */
    public void applyBuildStatus(String s_id, String name, String phone, String address_detail) {
        api.applyBuildStatus(s_id,name,phone,address_detail)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<ApplyBuildBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<ApplyBuildBean> baseBean) {
                        getView().applyBuildStateSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
