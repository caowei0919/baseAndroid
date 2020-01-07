package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.activity.OrderDetailActivity;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: OrderAdapter
 * Author: 曹伟
 * Date: 2019/10/22 16:01
 * Description:订单列表
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<OrderBean> orderBeanList = new ArrayList<>();
    private Context mContext ;
    private List<OrderBean.GoodsBean> goodsBeans = new ArrayList<>();
    private RecyclerView.RecycledViewPool viewPool;
    private OnOrderClick click ;

    public OrderAdapter(Context mContext, List<OrderBean> orderBeans) {
        this.mContext = mContext ;
        this.orderBeanList = orderBeans ;
        viewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_order, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvShopName.setText(orderBeanList.get(i).getShop_name());
        viewHolder.tvCreatTime.setText(orderBeanList.get(i).getOrder_creat_t());
        viewHolder.tvStatus.setText(orderBeanList.get(i).getOrder_status());
        viewHolder.tvTotalNum.setText(mContext.getResources().getString(R.string.total)
                + orderBeanList.get(i).getNum_total()
                + mContext.getResources().getString(R.string.num_goods));
        /**
         * 根据店铺类型区分运费服务费的显示
         */
        switch (orderBeanList.get(i).getShop_type()){
            case "dorm" :   //宿舍小店 （含运费，服务费）
                viewHolder.tvTotalPrice.setText(mContext.getResources().getString(R.string.rmb)
                        + orderBeanList.get(i).getPrice_total() + "("
                        + mContext.getResources().getString(R.string.include_freight)
                        + mContext.getResources().getString(R.string.rmb)
                        + orderBeanList.get(i).getFreight_p()
                        + mContext.getResources().getString(R.string.service_price)
                        + mContext.getResources().getString(R.string.rmb)
                        + orderBeanList.get(i).getService_p()+")");
                break;

            case "warehouse" :      //窝边超市（含服务费，运费）
                viewHolder.tvTotalPrice.setText(mContext.getResources().getString(R.string.rmb)
                        + orderBeanList.get(i).getPrice_total() + "("
                        + mContext.getResources().getString(R.string.include_freight)
                        + mContext.getResources().getString(R.string.rmb)
                        + orderBeanList.get(i).getFreight_p()
                        + mContext.getResources().getString(R.string.service_price)
                        + mContext.getResources().getString(R.string.rmb)
                        + orderBeanList.get(i).getService_p()+")");
                break;

            default:        //窝边商城（无服务费，无运费）
                viewHolder.tvTotalPrice.setText(mContext.getResources().getString(R.string.rmb)
                        + orderBeanList.get(i).getPrice_total());
                break;
        }

        /**
         *根据订单状态区分按钮显示
         */
        switch (orderBeanList.get(i).getOrder_status_code()){
            case "1" :  //已完成订单
                viewHolder.tvHandleOne.setVisibility(View.VISIBLE);     //删除订单
                viewHolder.tvHandleOne.setText(mContext.getResources()
                        .getString(R.string.delete_order));
                viewHolder.tvHandleOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click.onDeleteClick(orderBeanList.get(i).getOrder_id());
                    }
                });
                //根据评价数量判断是否显示评价按钮
                viewHolder.tvHandleTwo.setVisibility(orderBeanList.get(i).getIs_commented()
                        .equals("0") ? View.VISIBLE : View.GONE);
                viewHolder.tvHandleTwo.setText(mContext.getResources()
                        .getString(R.string.to_evaluate));
                break;

            case "2" :      //待发货
                viewHolder.tvHandleOne.setVisibility(View.GONE);
                viewHolder.tvHandleTwo.setVisibility( View.VISIBLE);
                //宿舍小店——催单  否则——提醒发货
                viewHolder.tvHandleTwo.setText(orderBeanList.get(i).getShop_type().equals("dorm")
                        ? mContext.getResources()
                        .getString(R.string.reminder)
                        : mContext.getResources()
                        .getString(R.string.remind_the_delivery));
                break;

            case "3" :      //待收货
                viewHolder.tvHandleOne.setVisibility(View.GONE);     //删除订单
                viewHolder.tvHandleTwo.setVisibility(View.VISIBLE);
                viewHolder.tvHandleTwo.setText(mContext.getResources()
                        .getString(R.string.confirm_the_goods));
                break;

            case "4" :      //待付款
                viewHolder.tvHandleOne.setVisibility(View.GONE);     //删除订单
                viewHolder.tvHandleTwo.setVisibility(View.VISIBLE);
                viewHolder.tvHandleTwo.setText(mContext.getResources()
                        .getString(R.string.to_payment));
                viewHolder.tvHandleTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click.onPayClick(orderBeanList.get(i).getOrder_id());
                    }
                });
                break;

            case "5" :      //已关闭
                viewHolder.tvHandleOne.setVisibility(View.VISIBLE);     //删除订单
                viewHolder.tvHandleOne.setText(mContext.getResources()
                        .getString(R.string.delete_order));
                viewHolder.tvHandleOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click.onDeleteClick(orderBeanList.get(i).getOrder_id());
                    }
                });
                viewHolder.tvHandleTwo.setVisibility(View.GONE);
                break;

            case "6" :      //待评价
                viewHolder.tvHandleOne.setVisibility(View.VISIBLE);     //删除订单
                viewHolder.tvHandleOne.setText(mContext.getResources()
                        .getString(R.string.delete_order));
                viewHolder.tvHandleOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click.onDeleteClick(orderBeanList.get(i).getOrder_id());
                    }
                });
                //根据评价数量判断是否显示评价按钮
                viewHolder.tvHandleTwo.setVisibility(View.VISIBLE);
                viewHolder.tvHandleTwo.setText(mContext.getResources()
                        .getString(R.string.to_evaluate));
                break;

            default:    //包含"0" "7"
                viewHolder.tvHandleOne.setVisibility(View.VISIBLE);
                viewHolder.tvHandleTwo.setVisibility(View.GONE);
                break;
        }

        /**
         * 商品部分处理
         */
        goodsBeans = orderBeanList.get(i).getGoods();
        GoodsInOrderAdapter adapter = new GoodsInOrderAdapter(mContext,goodsBeans);
        viewHolder.recycleGoods.setRecycledViewPool(viewPool);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        viewHolder.recycleGoods.setNestedScrollingEnabled(false);
        viewHolder.recycleGoods.setHasFixedSize(true);
        viewHolder.recycleGoods.setFocusable(false);
        viewHolder.recycleGoods.setFocusableInTouchMode(false);
        //设置布局管理器
        viewHolder.recycleGoods.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        viewHolder.recycleGoods.setAdapter(adapter);
        /**
         * 跳转到订单详情页面
         */
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderIntent = new Intent(mContext, OrderDetailActivity.class);
                orderIntent.putExtra("order_id",orderBeanList.get(i).getOrder_id());
                mContext.startActivity(orderIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_shopName)
        TextView tvShopName ;   //店铺名
        @BindView(R.id.tv_creatTime)
        TextView tvCreatTime ;  //创建时间
        @BindView(R.id.tv_status)
        TextView tvStatus ; //订单状态
        @BindView(R.id.tv_totalNum)
        TextView tvTotalNum ;   //商品数量
        @BindView(R.id.tv_totalPrice)
        TextView tvTotalPrice ; //合计价格
        @BindView(R.id.tv_sureTime)
        TextView tvSureTime ;   //确认收货（或者剩余付款时间）
        @BindView(R.id.tv_handleOne)
        TextView tvHandleOne ;  //操作一（如删除）
        @BindView(R.id.tv_handleTwo)
        TextView tvHandleTwo ;  //操作二 （如付款）
        @BindView(R.id.recycle_goods)
        RecyclerView recycleGoods ;     //商品列表

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    /**
     * 订单相关操作
     */
    public interface OnOrderClick{
        void onPayClick(String order_id);  //付款
        void onDeleteClick(String order_id);   //删除订单
        void onReminderClick(); //催单
        void onDelivery() ; //提醒发货
    }
}
