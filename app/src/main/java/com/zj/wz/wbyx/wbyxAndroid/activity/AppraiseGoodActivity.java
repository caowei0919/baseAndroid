package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.adapter.AppraiseGoodAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.AppraiseRequestBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.AppraiseGoodPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.AppraiseGoodView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: AppraiseGoodActivity
 * Author: 曹伟
 * Date: 2019/11/20 19:26
 * Description:商品评价
 */

public class AppraiseGoodActivity extends BaseMvpActivity<AppraiseGoodView, AppraiseGoodPresenter>
        implements AppraiseGoodView{
    @BindView(R.id.tv_title)
    TextView  tvTitle ;
    @BindView(R.id.checkbox_showName)
    CheckBox checkboxShowName ;
    @BindView(R.id.btn_submit)
    Button btnSubmit ;   //提交
    @BindView(R.id.recycle_goods)
    RecyclerView recycleGoods ; //评价商品列表

    private AppraiseGoodAdapter appraiseGoodAdapter ;
    private int anonymous = 0 ; //1匿名   0 不匿名
    private String order_id = "" ;
    private List<OrderDetailBean.OrderBean.GoodsBean> goodsBeans = new ArrayList<>() ;
    private List<AppraiseRequestBean> appraiseRequestBeans = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_appraise_good;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.appraise_good));
        checkboxShowName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                anonymous = isChecked ? 1 : 0 ;
            }
        });
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        order_id = TextUtils.isEmpty(getIntent().getStringExtra("order_id"))
                ? ""  : getIntent().getStringExtra("order_id")  ;
        getPresenter().getOrderDetail(order_id);
    }

    @OnClick({R.id.linear_back,R.id.btnSubmit})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back :
                    finish();
                break;

            case R.id.btnSubmit :       //提交

                break;
        }
    }

    /**
     * 获取订单下商品集合成功
     * @param goods
     */
    @Override
    public void getOrderGoodSuccess(List<OrderDetailBean.OrderBean.GoodsBean> goods) {
        goodsBeans.clear();
        goodsBeans.addAll(goods);
    }
}
