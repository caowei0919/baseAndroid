package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.wbyxAndroid.view.GroupBookingView;

import javax.inject.Inject;


/**
 * FileName: GroupBookingPresenter
 * Author: 曹伟
 * Date: 2019/11/9 10:58
 * Description:拼团列表P层
 */

public class GroupBookingPresenter extends BasePresenter<GroupBookingView> {
    MyApi api ;

    @Inject
    public GroupBookingPresenter(MyApi api) {
        this.api = api;
    }
}
