package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.presenter.CustomerPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.CustomerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: CustomerActivity
 * Author: 曹伟
 * Date: 2019/11/9 13:50
 * Description:客服
 */

public class CustomerActivity extends BaseMvpActivity<CustomerView, CustomerPresenter>
        implements CustomerView {

    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.customer));
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.linear_back})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                    finish();
                break;
        }
    }
}
