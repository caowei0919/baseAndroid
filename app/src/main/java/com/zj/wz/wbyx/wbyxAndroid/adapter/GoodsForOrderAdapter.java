package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: GoodsForOrderAdapter
 * Author: 曹伟
 * Date: 2019/11/20 20:31
 * Description:订单详细商品展示
 */

public class GoodsForOrderAdapter extends RecyclerView.Adapter<GoodsForOrderAdapter.ViewHolder> {
    private Context mContext ;
    private List<OrderDetailBean.OrderBean.GoodsBean> goodsBeans = new ArrayList<>();

    public GoodsForOrderAdapter(Context mContext, List<OrderDetailBean.OrderBean.GoodsBean> goodsBeans) {
        this.mContext = mContext;
        this.goodsBeans = goodsBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_order_detail_goods, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(goodsBeans.get(position).getImg())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGoods);
        holder.tvPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeans.get(position).getPrice());
        holder.tvSku.setText(goodsBeans.get(position).getSpecs() + "×"
                + goodsBeans.get(position).getNum());
        holder.tvTitle.setText(goodsBeans.get(position).getTitle());
        holder.refundTxt.setText(TextUtils.isEmpty(goodsBeans.get(position).getRefund_txt()) ? ""
                : goodsBeans.get(position).getRefund_txt());
    }

    @Override
    public int getItemCount() {
        return goodsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_goods)
        ImageView imgGoods ;    //商品图片
        @BindView(R.id.tv_title)
        TextView tvTitle ;  //标题
        @BindView(R.id.tv_sku)
        TextView tvSku ;    //规格加数量
        @BindView(R.id.tv_price)
        TextView tvPrice ;  //价格
        @BindView(R.id.tv_refund)
        TextView refundTxt;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
