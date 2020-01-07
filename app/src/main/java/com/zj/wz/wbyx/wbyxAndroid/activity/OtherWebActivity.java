package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.wbyxAndroid.presenter.OtherWebPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.OtherWebView;

/**
 * FileName: OtherWebActivity
 * Author: 曹伟
 * Date: 2019/11/9 10:51
 * Description: 其他外部链接
 */

public class OtherWebActivity extends BaseMvpActivity<OtherWebView, OtherWebPresenter>
        implements OtherWebView{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_web;
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
