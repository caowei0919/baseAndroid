package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.bean.ServiceBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.ServicePresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.ServiceView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: ServiceActivity
 * Author: 曹伟
 * Date: 2019/10/11 14:22
 * Description:服务条款
 */

public class ServiceActivity extends BaseMvpActivity<ServiceView, ServicePresenter>
        implements ServiceView{
    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.linear_back)
    LinearLayout linearBack ;       //返回
    @BindView(R.id.tv_serviceTitle)
    TextView tvServiceTitle ;   //服务协议标题
    @BindView(R.id.tv_content)
    TextView tvContent ;    //服务协议文本

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.the_service_agreement));
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().getServerWrite(Constant.TYPE_SERVICE);
    }

    @OnClick(R.id.linear_back)
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                    finish();
                break;
        }
    }

    /**
     * 获取服务条款信息成功
     * @param baseBean
     */
    @Override
    public void getServiceWriteSuccess(ServiceBean baseBean) {
        if(baseBean.getList().size() > 0){
            tvServiceTitle.setText(baseBean.getList().get(0).getTitle());
            tvContent.setText(Html.fromHtml(baseBean.getList().get(0).getContent()));
        }
    }
}
