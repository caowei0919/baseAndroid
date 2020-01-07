package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.AddressBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.view.MyAddrView;

import javax.inject.Inject;


/**
 * FileName: MyAddrPresenter
 * Author: 曹伟
 * Date: 2019/10/15 11:47
 * Description:我的地址P层
 */

public class MyAddrPresenter extends BasePresenter<MyAddrView> {
    MyApi myApi ;

    @Inject
    public MyAddrPresenter(MyApi myApi) {
        this.myApi = myApi;
    }

    /**
     * 获取我的所有地址
     */
    public void getAllAddress() {
        myApi.getAllAddress()
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<AddressBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<AddressBean> baseBean) {
                        if(baseBean.getResponse()!=null){
                            getView().getAllAddressSuccess(baseBean.getResponse());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 修改宿舍地址
     * @param type
     * @param address_info
     * @param address_id
     */
    public void upDateDormitory(String type, String address_info, String address_id) {
        myApi.upDateDormitory(type,address_info,address_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        getAllAddress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
