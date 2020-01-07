package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.DividerGridItemDecoration;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.adapter.ConfirmRefundOrderAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefundDetailsBean;
import com.zj.wz.wbyx.wbyxAndroid.event.CanelRefundEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.ConfirmRefundDetailsPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.ConfirmRefundDetailsView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: ConfirmRefundDetailsActivity
 * Author: 曹伟
 * Date: 2019/11/20 17:20
 * Description:退款详情
 */

public class ConfirmRefundDetailsActivity extends BaseMvpActivity<ConfirmRefundDetailsView
        , ConfirmRefundDetailsPresenter> implements ConfirmRefundDetailsView {
    @BindView(R.id.tv_title)
    TextView tvTitle ;
    @BindView(R.id.tv_shopName)
    TextView tvShopName ;   //商铺名称
    @BindView(R.id.tv_creatTime)
    TextView tvCreatTime ;  //创建时间
    @BindView(R.id.btn_confirm)
    Button btnConfirm ;
    @BindView(R.id.tv_chooseAll)
    TextView tvChooseAll ;  //退款状态
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei ;
    @BindView(R.id.tv_fuwufei)
    TextView tvFuwufei ;
    @BindView(R.id.tv_total)
    TextView tvTotal ;
    @BindView(R.id.recy_order)
    RecyclerView recyOrder ;

    private String order_id = "" ;      //订单id
    private List<RefundDetailsBean.GoodsBean> goodsBeans = new ArrayList<>() ;
    private ConfirmRefundOrderAdapter confirmRefundOrderAdapter ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirrm_refund;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.refund_detail));

        confirmRefundOrderAdapter = new ConfirmRefundOrderAdapter(this, goodsBeans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyOrder.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        //设置分隔线
        recyOrder.addItemDecoration( new DividerGridItemDecoration(this));
        //设置增加或删除条目的动画
        recyOrder.setItemAnimator( new DefaultItemAnimator());
        recyOrder.setAdapter(confirmRefundOrderAdapter);
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        order_id = TextUtils.isEmpty(getIntent().getStringExtra("order_id")) ? ""
                : getIntent().getStringExtra("order_id") ;
        getPresenter().getRefundDetail(order_id);
    }

    @Override
    public void getRefundDetailsSuccess(RefundDetailsBean response) {
        tvShopName.setText(response.getShop_name());
        tvCreatTime.setText(response.getCreated_at());
        tvYunfei.setText(mContext.getResources().getString(R.string.rmb)
                + response.getFreight_price());
        tvFuwufei.setText(mContext.getResources().getString(R.string.rmb)
                + response.getService_price());
        tvTotal.setText(mContext.getResources().getString(R.string.rmb)
                + response.getTotal_price());
        switch (response.getStatus_code()){
            case "0" :  //退款中
                tvChooseAll.setText(mContext.getResources()
                        .getString(R.string.in_refund_detail));
                btnConfirm.setVisibility(View.VISIBLE);
                break;

            case "1" :  //退款完成
                tvChooseAll.setText(mContext.getResources()
                        .getString(R.string.complete_refund_detail));
                btnConfirm.setVisibility(View.GONE);
                break;
        }
        goodsBeans.clear();
        goodsBeans.addAll(response.getGoods());
        confirmRefundOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void cancelRefundSuccess() {
        ToastUtils.showLongToast(mContext.getResources().getString(R.string.cancel_refund_success));
        EventBus.getDefault().post(new CanelRefundEvent());
        finish();
    }

    @OnClick({R.id.linear_back,R.id.btn_confirm})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                    finish();
                break;

            case R.id.btn_confirm : //取消退款
                    getPresenter().cancelRefund(order_id);
                break;
        }
    }
}
