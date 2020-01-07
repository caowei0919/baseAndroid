package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.wbyxAndroid.view.TestView;

import javax.inject.Inject;


/**
 * FileName: TestPresenter
 * Author: 曹伟
 * Date: 2019/11/5 19:51
 * Description:测试
 */

public class TestPresenter extends BasePresenter<TestView> {
    MyApi api ;

    @Inject
    public TestPresenter(MyApi api) {
        this.api = api;
    }
}
