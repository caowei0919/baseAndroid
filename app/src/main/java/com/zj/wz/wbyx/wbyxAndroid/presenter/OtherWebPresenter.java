package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.wbyxAndroid.view.OtherWebView;

import javax.inject.Inject;


/**
 * FileName: OtherWebPresenter
 * Author: 曹伟
 * Date: 2019/11/9 11:00
 * Description:其他外部链接P层
 */

public class OtherWebPresenter extends BasePresenter<OtherWebView> {
    MyApi api ;

    @Inject
    public OtherWebPresenter(MyApi api) {
        this.api = api;
    }
}
