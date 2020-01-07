package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.wbyxAndroid.view.NewsSettingView;

import javax.inject.Inject;


/**
 * FileName: NewsSettingPresenter
 * Author: 曹伟
 * Date: 2019/10/11 13:50
 * Description:消息设置P层
 */

public class NewsSettingPresenter extends BasePresenter<NewsSettingView> {
    MyApi api ;

    @Inject
    public NewsSettingPresenter(MyApi api) {
        this.api = api;
    }
}
