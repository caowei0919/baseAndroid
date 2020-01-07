package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.NotDormitoryAddress;
import com.zj.wz.wbyx.wbyxAndroid.view.AddNotDormitoryView;

import javax.inject.Inject;


/**
 * FileName: AddNotDormitoryPresenter
 * Author: 曹伟
 * Date: 2019/10/18 9:31
 * Description:新增修改非宿舍地址P层
 */

public class AddNotDormitoryPresenter extends BasePresenter<AddNotDormitoryView> {
    MyApi api ;

    @Inject
    public AddNotDormitoryPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 添加非宿舍地址
     * @param type
     * @param address_info
     */
    public void addAddress(String type, String address_info) {
        api.addNotDormitoryAddress(type,address_info)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<NotDormitoryAddress>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<NotDormitoryAddress> baseBean) {
                        getView().addNotDormitoryAddress(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 修改非宿舍地址
     * @param type
     * @param address_info
     * @param address_id
     */
    public void upDateAddress(String type, String address_info, String address_id) {
        api.upDateNotDormitoryAddress(type,address_info,address_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        getView().upDateSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
