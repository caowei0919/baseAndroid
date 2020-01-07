package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.baseandroid.view.SwitchButton;
import com.zj.wz.wbyx.wbyxAndroid.presenter.NewsSettingPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.NewsSettingView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: NewsSettingActivity
 * Author: 曹伟
 * Date: 2019/10/11 13:48
 * Description:消息设置
 */

public class NewsSettingActivity extends BaseMvpActivity<NewsSettingView, NewsSettingPresenter>
        implements NewsSettingView {

    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.linear_back)
    LinearLayout linearBack ;       //返回
    @BindView(R.id.switch_news)
    SwitchButton switchNews ;   //消息设置

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_setting;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.setting));
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
