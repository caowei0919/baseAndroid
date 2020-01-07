package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.RatingBar;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.event.AppraiseEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.AppraiseShopPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.AppraiseShopView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: AppraiseShopActivity
 * Author: 曹伟
 * Date: 2019/11/20 19:21
 * Description: 店铺评价
 */

public class AppraiseShopActivity extends BaseMvpActivity<AppraiseShopView, AppraiseShopPresenter>
        implements AppraiseShopView, RatingBar.OnStarChangeListener {
    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.ratingBar_appraise)
    RatingBar ratingBarAppraise ;
    @BindView(R.id.tv_shopName)
    TextView tvShopName ;
    @BindView(R.id.checkbox_showName)
    CheckBox checkboxShowName ;

    private int star = 0 ;  //评分
    private String order_id = "" ;  //店铺id
    private String shop_name = "" ; //店铺名称

    @Override
    protected int getLayoutId() {
        return R.layout.activity_appraise_shop;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.evaluation_for_shop));
        ratingBarAppraise.setmOnStarChangeListener(this);
        checkboxShowName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxShowName.setChecked(isChecked);
            }
        });
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        order_id = TextUtils.isEmpty(getIntent().getStringExtra("order_id"))
                ? "" : getIntent().getStringExtra("order_id") ;
        shop_name = TextUtils.isEmpty(getIntent().getStringExtra("shop_name"))
                ? "" : getIntent().getStringExtra("shop_name") ;
        tvShopName.setText(shop_name);
    }

    @Override
    public void OnStarChanged(float selectedNumber, int position) {
        PLog.e("selectedNumber" + selectedNumber + ",position " + position);
        star = position + 1 ;
    }

    @OnClick({R.id.linear_back,R.id.btn_submit})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                    finish();
                break;

            case R.id.btn_submit :  //提交
                    getPresenter().submitAppraise(order_id,star,checkboxShowName.isChecked() ? 1 : 0);
                break;
        }
    }

    @Override
    public void submitAppraiseSuccess() {
        ToastUtils.showLongToast(mContext.getResources()
                .getString(R.string.submit_appraise_success));
        EventBus.getDefault().post(new AppraiseEvent());
        finish();
    }
}
