package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.wbyxAndroid.view.CustomerView;

import javax.inject.Inject;


/**
 * FileName: CustomerPresenter
 * Author: 曹伟
 * Date: 2019/11/9 13:51
 * Description:客服P层
 */

public class CustomerPresenter extends BasePresenter<CustomerView> {
    MyApi api ;

    @Inject
    public CustomerPresenter(MyApi api) {
        this.api = api;
    }
}
