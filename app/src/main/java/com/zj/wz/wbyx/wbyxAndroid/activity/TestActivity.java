package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.TagTextView;
import com.zj.wz.wbyx.wbyxAndroid.presenter.TestPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.TestView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * FileName: TestActivity
 * Author: 曹伟
 * Date: 2019/11/5 19:50
 * Description:
 */

public class TestActivity extends BaseMvpActivity<TestView, TestPresenter> implements TestView {
    @BindView(R.id.text)
    TagTextView text ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @SuppressLint("NewApi")
    @Override
    protected void setupView() {
        PLog.e(android.os.Build.VERSION.SDK_INT + "=============");
        text.setContentAndTag("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试",
                "100人团");
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

    }
}
