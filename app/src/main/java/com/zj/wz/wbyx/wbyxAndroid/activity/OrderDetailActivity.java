package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.TimeUtils;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.MyGridLayoutManager;
import com.zj.wz.wbyx.baseandroid.view.RecyclerViewSpacesItemDecoration;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.adapter.GoodsForOrderAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.HomeGoodsAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.event.ApplyRefundEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.AppraiseEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.CanelRefundEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.TimeOutEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.OrderDetailPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.OrderDetailView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: OrderDetailActivity
 * Author: 曹伟
 * Date: 2019/11/20 14:27
 * Description:
 */

public class OrderDetailActivity extends BaseMvpActivity<OrderDetailView, OrderDetailPresenter>
        implements OrderDetailView, HomeGoodsAdapter.OnAddToShopCarClick {
    @BindView(R.id.smart_order)
    SmartRefreshLayout smartOrder ;
    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.img_status)
    ImageView imgStatus ;   //订单状态对应的图标
    @BindView(R.id.tv_status)
    TextView tvStatus ; //订单状态
    @BindView(R.id.tv_endTime)
    TextView tvEndTime ;    //订单处理时间
    @BindView(R.id.tv_nameAndPhone)
    TextView tvNameAndPhone ;   //收货人
    @BindView(R.id.tv_address)
    TextView tvAddress ;    //收货地址
    @BindView(R.id.tv_shopName)
    TextView tvShopName ;  //订单所属
    @BindView(R.id.tv_refund)
    TextView tvRefund ;     //申请退款
    @BindView(R.id.tv_freight)
    TextView tvFreight ;    //运费
    @BindView(R.id.tv_service)
    TextView tvService ;    //服务费
    @BindView(R.id.tv_total)
    TextView tvTotal ;  //总额
    @BindView(R.id.tv_remark)
    TextView tvRemark ; //备注
    @BindView(R.id.tv_orderId)
    TextView tvOrderId ;    //订单编号
    @BindView(R.id.tv_orderPayType)
    TextView tvOrderPayType ;   //订单支付方式
    @BindView(R.id.tv_orderCreateTime)
    TextView tvOrderCreateTime ;    //订单创建时间
    @BindView(R.id.tv_payTime)
    TextView tvPayTime ;    //支付时间
    @BindView(R.id.tv_sendTime)
    TextView tvSendTime ;   //发货时间
    @BindView(R.id.tv_delivTime)
    TextView tvDelivTime ;  //成交时间
    @BindView(R.id.bt_orderStatus)
    TextView btOrderStatus ;
    @BindView(R.id.recycle_allGoods)
    RecyclerView recycleAllGoods ;      //商品列表
    @BindView(R.id.recycle_goodForOrder)
    RecyclerView recycleGoodForOrder ;

    private HomeGoodsAdapter adapter ;  //商品展示适配器
    private GoodsForOrderAdapter goodsForOrderAdapter ;
    private List<HomeGoodsOfAllBean> allGoods = new ArrayList<>();
    private List<OrderDetailBean.OrderBean.GoodsBean> goodsBeans = new ArrayList<>();

    private String order_id = "" ;  //订单id
    private String areaId ; //区域编码
    private String goodsType ;  //商品类型
    private int page = 1 ;
    private int pageNum = 10 ;  //每页展示的数据

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.order_detail));

        adapter = new HomeGoodsAdapter(mContext,allGoods,this);
        MyGridLayoutManager gridLayoutManager = new MyGridLayoutManager(mContext, 2
                , GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setScrollEnabled(false);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        recycleAllGoods.setHasFixedSize(true);
        recycleAllGoods.setNestedScrollingEnabled(false);
        recycleAllGoods.setLayoutManager(gridLayoutManager);
        recycleAllGoods.setAdapter(adapter);

        smartOrder.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page ++ ;
                getPresenter().getHomeGoods(Constant.getMyLocation().adCode,goodsType,page,pageNum);
            }
        });

        goodsForOrderAdapter = new GoodsForOrderAdapter(mContext,goodsBeans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recycleGoodForOrder.setHasFixedSize(true);
        recycleGoodForOrder.setNestedScrollingEnabled(false);
        //设置布局管理器
        recycleGoodForOrder.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recycleGoodForOrder.setAdapter(goodsForOrderAdapter);
        recycleGoodForOrder.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext,
                LinearLayoutManager.HORIZONTAL
                ,20
                ,mContext.getResources().getColor(R.color.c_F5F5F5)));
        //设置增加或删除条目的动画
        recycleGoodForOrder.setItemAnimator( new DefaultItemAnimator());
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        if(null != getIntent().getStringExtra("order_id"))
            order_id = getIntent().getStringExtra("order_id") ;
        getPresenter().getOrderDetail(order_id);
        goodsType = Constant.HOME_GOODS_ALL ;
        areaId = Constant.getMyLocation().adCode ;
        getPresenter().getHomeGoods(Constant.getMyLocation().adCode,goodsType,page,pageNum);
    }

    @OnClick({R.id.linear_back})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                    finish();
                break;
        }
    }

    /**
     * 获取订单详情成功
     * @param response
     */
    @Override
    public void getOrderDetailSuccess(OrderDetailBean response) {
        smartOrder.finishLoadMore();
        goodsBeans.clear();
        goodsBeans.addAll(response.getOrder().getGoods());
        goodsForOrderAdapter.notifyDataSetChanged();
        tvStatus.setText(response.getOrder().getStatus());
        tvShopName.setText(response.getOrder().getShop_name()
                + (response.getOrder().getBuilding_name() != null
                ? response.getOrder().getBuilding_name() : ""));
        tvFreight.setText(mContext.getResources().getString(R.string.rmb)
                + " " + response.getOrder().getFreight_price());
        tvService.setText(mContext.getResources().getString(R.string.rmb)
                + " " + response.getOrder().getService_price());
        tvTotal.setText(mContext.getResources().getString(R.string.rmb)
                + " " + response.getOrder().getPrice_total());
        tvRemark.setText(mContext.getResources().getString(R.string.order_remark)
                + " " + (TextUtils.isEmpty(response.getOrder().getRemark())
                ? "" : response.getOrder().getRemark()));
        tvOrderId.setText(mContext.getResources().getString(R.string.order_id_point)
                + response.getOrder().getOrder_sn());
        tvOrderPayType.setText(mContext.getResources().getString(R.string.pay_time_point)
                + response.getOrder().getPay_type());
        tvOrderCreateTime.setText(mContext.getResources().getString(R.string.create_time_point)
                +response.getOrder().getCreat_t());
        tvPayTime.setText(mContext.getResources().getString(R.string.pay_time_point)
                +response.getOrder().getPay_t());
        tvSendTime.setText(mContext.getResources().getString(R.string.send_time_point)
                +response.getOrder().getSend_t());
        tvDelivTime.setText(mContext.getResources().getString(R.string.complete_time_point)
                +response.getOrder().getDeliv_t());
        if(response.getOrder().getType().equals("dorm")){
            tvRefund.setVisibility(View.VISIBLE);
            switch (response.getOrder().getRefund_status_code()){
                case "0" :
                        tvRefund.setText(mContext.getResources().getString(R.string.apply_refund));
                        //申请退款
                        tvRefund.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, RefundDetailsActivity.class);
                                intent.putExtra("order_id",order_id);
                                mContext.startActivity(intent);
                            }
                        });
                    break;

                default:
                        tvRefund.setText(response.getOrder().getRefund_status());
                        //退款详情
                        tvRefund.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, ConfirmRefundDetailsActivity.class);
                                intent.putExtra("order_id",order_id);
                                mContext.startActivity(intent);
                            }
                        });
                    break;
            }
        }

        switch (response.getOrder().getStatus_code()){
            case "1" :  //已完成
                imgStatus.setImageResource(R.mipmap.icon_order_complete);
                if(response.getOrder().getIs_commented() .equals("0")){     //未批评订单
                    btOrderStatus.setVisibility(View.VISIBLE);
                    btOrderStatus.setText(mContext.getResources().getString(R.string.to_evaluate));
                    btOrderStatus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if ("dorm".equals(response.getOrder().getType())) {
                                //店铺评价
                                Intent appraiseShopIntent
                                        = new Intent(mContext, AppraiseShopActivity.class);
                                appraiseShopIntent.putExtra("order_id",order_id);
                                appraiseShopIntent.putExtra("shop_name"
                                        ,response.getOrder().getShop_name());
                                startActivity(appraiseShopIntent);
                            } else {
                                //商品评价
                                Intent appraiseGoodIntent
                                        = new Intent(mContext, AppraiseGoodActivity.class);
                                appraiseGoodIntent.putExtra("order_id",order_id);
                                startActivity(appraiseGoodIntent);
                            }
                        }
                    });
                }
                break;

            case "2" : //待发货
                tvRefund.setVisibility(View.GONE);
                imgStatus.setImageResource(R.mipmap.icon_order_sendgood);
                break;

            case "3" :  //待收货
                tvRefund.setVisibility(View.GONE);
                tvEndTime.setVisibility(View.VISIBLE);
                imgStatus.setImageResource(R.mipmap.icon_order_goods);
                TimeUtils.getCountDownTimeUnit(
                        mContext.getResources().getString(R.string.complete_to_sure_the_order),
                        (Integer.valueOf(response.getOrder().getConfirm_exp()))
                                - Integer.valueOf(response.getCurrent_time())+"",tvEndTime);
                btOrderStatus.setVisibility(View.VISIBLE);
                btOrderStatus.setText(mContext.getResources().getString(R.string.confirm_the_goods));
                btOrderStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        present.confirmOrder(orderID);
                    }
                });
                break;

            case "4" :  //待付款
                tvRefund.setVisibility(View.GONE);
                tvEndTime.setVisibility(View.VISIBLE);
                imgStatus.setImageResource(R.mipmap.icon_order_payment);
                TimeUtils.getCountDownTimeUnit(
                        mContext.getResources().getString(R.string.time_remaining_for_pay),
                        (Integer.valueOf(response.getOrder().getPay_exp()))
                                - Integer.valueOf(response.getCurrent_time())+"",tvEndTime);
                break;

            case "5" :  //已关闭
                tvRefund.setVisibility(View.GONE);
                imgStatus.setImageResource(R.mipmap.icon_order_close);
                break;
        }
        tvNameAndPhone.setText(mContext.getResources().getString(R.string.the_order_owner)
                + "  " + response.getAddress().getName() + " " + response.getAddress().getTel());
        tvAddress.setText(response.getAddress().getAddr());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(TimeOutEvent event){
        getPresenter().getOrderDetail(order_id);
    }

    /**
     * 获取首页底部全部商品成功
     * @param response
     */
    @Override
    public void getOnGoodsSuccess(HomeGoodsOfAllListBean response) {
        allGoods.addAll(response);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(ApplyRefundEvent event){
        getPresenter().getOrderDetail(order_id);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(AppraiseEvent event){
        btOrderStatus.setVisibility(View.GONE);
        getPresenter().getOrderDetail(order_id);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(CanelRefundEvent event){
        btOrderStatus.setVisibility(View.GONE);
        getPresenter().getOrderDetail(order_id);
    }

    /**
     * 加入购物车
     * @param addCar
     * @param position
     */
    @Override
    public void addCarClick(View addCar, int position) {
        ToastUtils.showLongToast("加入购物车");
    }
}
