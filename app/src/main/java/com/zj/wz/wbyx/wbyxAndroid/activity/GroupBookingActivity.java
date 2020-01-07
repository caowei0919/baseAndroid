package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;

import com.zj.wz.wbyx.wbyxAndroid.presenter.GroupBookingPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.GroupBookingView;

/**
 * FileName: GroupBookingActivity
 * Author: 曹伟
 * Date: 2019/11/9 10:31
 * Description:拼团列表
 */

public class GroupBookingActivity extends BaseMvpActivity<GroupBookingView,GroupBookingPresenter>
        implements GroupBookingView {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupbooking;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {

    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

    }
}
