package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.DividerGridItemDecoration;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.adapter.RefundOrderAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.RefundReasonAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ReasonBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefundCheckBean;
import com.zj.wz.wbyx.wbyxAndroid.event.ApplyRefundEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.RefundDetailsPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.RefundDetailsView;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: RefundDetailsActivity
 * Author: 曹伟
 * Date: 2019/11/20 17:14
 * Description:申请退款
 */

public class RefundDetailsActivity extends BaseMvpActivity<RefundDetailsView, RefundDetailsPresenter>
        implements RefundDetailsView, RefundOrderAdapter.CheckClickListener, RefundReasonAdapter.CheckReasonClickListener {
    @BindView(R.id.tv_title)
    TextView tvTitle ;
    @BindView(R.id.tv_shopName)
    TextView tvShopName ;   //店铺名称
    @BindView(R.id.tv_creatTime)
    TextView tvCreatTime ;  //创建时间
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei ; //运费
    @BindView(R.id.tv_fuwufei)
    TextView tvFuwufei ;    //服务费
    @BindView(R.id.tv_total)
    TextView tvTotal ;  //总额
    @BindView(R.id.recy_order)
    RecyclerView recyOrder ;    //订单
    @BindView(R.id.recy_reason)
    RecyclerView recyReason ;   //退款原因
    @BindView(R.id.btn_confirm)
    Button btnConfirm ;

    private String orderId ;
    private RefundOrderAdapter adapter ;
    private RefundReasonAdapter mAdapter ;
    private double refundTotal = 0 ;
    private double totalPrice = 0 ;
    private OrderDetailBean mData ;
    private List<RefundCheckBean> refundCheckBeans = new ArrayList<>();
    private List<ReasonBean> reasonBeans = new ArrayList<>();
    private boolean isHas = false ;
    private Map<String,Object> parms = new HashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refund_detail;
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

        adapter = new RefundOrderAdapter(mContext, refundCheckBeans,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyOrder.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        //设置分隔线
        recyOrder.addItemDecoration( new DividerGridItemDecoration(this));
        //设置增加或删除条目的动画
        recyOrder.setItemAnimator( new DefaultItemAnimator());
        recyOrder.setAdapter(adapter);

        mAdapter = new RefundReasonAdapter(mContext,reasonBeans,this);
        LinearLayoutManager layoutManagerReason = new LinearLayoutManager(mContext);
        //设置为垂直布局，这也是默认的
        layoutManagerReason.setOrientation(OrientationHelper. VERTICAL);
        recyReason.setLayoutManager(layoutManagerReason);
        recyReason.addItemDecoration( new DividerGridItemDecoration(mContext));
        //设置增加或删除条目的动画
        recyReason.setItemAnimator( new DefaultItemAnimator());
        recyReason.setAdapter(mAdapter);
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        orderId = getIntent().getStringExtra("order_id") != null
                ? getIntent().getStringExtra("order_id") : "";
        getPresenter().getOrderDetail(orderId);
        getPresenter().getDetailForReason(orderId);
    }

    @Override
    public void getOrderDetailSuccess(OrderDetailBean response) {
        refundCheckBeans.clear();
        for (OrderDetailBean.OrderBean.GoodsBean bean:response.getOrder().getGoods()) {
            RefundCheckBean checkBean = new RefundCheckBean(bean);
            refundCheckBeans.add(checkBean);
        }
        adapter.notifyDataSetChanged();
        mData = response ;
        tvShopName.setText(response.getOrder().getShop_name()
                + (response.getOrder().getBuilding_name() != null
                ? response.getOrder().getBuilding_name() : ""));
        tvCreatTime.setText(response.getOrder().getCreat_t());
        tvYunfei.setText(mContext.getResources().getString(R.string.rmb_zero));
        tvFuwufei.setText(mContext.getResources().getString(R.string.rmb_zero));
        tvTotal.setText(mContext.getResources().getString(R.string.rmb_zero));
        totalPrice = Double.valueOf(response.getOrder().getPrice_total());
    }

    @Override
    public void getReasonSuccess(List<ReasonBean> reasonBeans) {
        PLog.e(reasonBeans.size() + "");
        this.reasonBeans.clear();
        this.reasonBeans.addAll(reasonBeans);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getRefundSuccess() {
        EventBus.getDefault().post(new ApplyRefundEvent());
        ToastUtils.showLongToast(mContext.getResources().getString(R.string.apply_refund_success));
        finish();
    }

    @Override
    public void click(int position, boolean isCheck) {
        refundCheckBeans.get(position).setCheck(isCheck);
        boolean hasAllCheck = true ;
        Double total = 0.00 ;
        Double fuwu = 0.00 ;
        for (int i = 0 ; i < refundCheckBeans.size() ; i++){
            hasAllCheck = hasAllCheck && refundCheckBeans.get(i).isCheck() ;
            total = total + Double.valueOf((refundCheckBeans.get(i).isCheck() ?
                    (Double.valueOf(refundCheckBeans.get(i).getBean().getPrice()))
                            * (Double.valueOf(refundCheckBeans.get(i).getBean().getNum())): 0));
            fuwu = total * 0.1 ;
            total = total + fuwu ;
        }
        if(hasAllCheck){
            tvYunfei.setText("¥ " + mData.getOrder().getFreight_price());
            tvFuwufei.setText("¥ " + mData.getOrder().getService_price());
            tvTotal.setText("¥ " + mData.getOrder().getPrice_total());
            refundTotal = Double.parseDouble(new DecimalFormat("#0.00")
                    .format(Double.valueOf(mData.getOrder().getPrice_total())));
        }else{
            tvYunfei.setText("¥ 0.00");
            tvFuwufei.setText("¥ " + new DecimalFormat("#0.00").format(fuwu));
            tvTotal.setText("¥ " + new DecimalFormat("#0.00").format(total));
            refundTotal = Double.parseDouble(new DecimalFormat("#0.00").format(Double.valueOf(total)));
        }
        if(isHas && refundTotal > 0.00){
            btnConfirm.setEnabled(true);
        }else{
            btnConfirm.setEnabled(false);
        }
    }

    @Override
    public void clickReason(int position, boolean isCheck) {
        reasonBeans.get(position).setCheck(isCheck);
        isHas = isCheck ;
        for (int i = 0; i < reasonBeans.size();i++){
            isHas = isHas || reasonBeans.get(i).isCheck() ;
            if(isCheck){
                if(i != position){
                    reasonBeans.get(i).setCheck(false);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
        if(isHas && refundTotal > 0.00){
            btnConfirm.setEnabled(true);
        }else{
            btnConfirm.setEnabled(false);
        }
    }

    @OnClick({R.id.tv_chooseAll,R.id.btn_confirm})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_chooseAll :
                for (int i =0;i<refundCheckBeans.size();i++) {
                    refundCheckBeans.get(i).setCheck(true);
                }
                refundTotal = totalPrice ;
                adapter.notifyDataSetChanged();
                break;

            case R.id.btn_confirm :
                for (int i = 0; i < reasonBeans.size();i++){
                    if(reasonBeans.get(i).isCheck()){
                        parms.put("reason_id",reasonBeans.get(i).getId());
                    }
                }
                List<String> sku_ids = new ArrayList<>() ;
                for (int i = 0 ; i < refundCheckBeans.size() ; i++){
                    if (refundCheckBeans.get(i).isCheck()){
                        sku_ids.add(refundCheckBeans.get(i).getBean().getSku_id());
                    }
                }
                parms.put("req_price",refundTotal);
                parms.put("sku_ids",sku_ids);
                presenter.getRefund(orderId,parms);
                break;
        }
    }
}
