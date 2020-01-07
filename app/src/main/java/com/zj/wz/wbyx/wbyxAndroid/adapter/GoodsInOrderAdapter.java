package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: GoodsInOrderAdapter
 * Author: 曹伟
 * Date: 2019/10/22 20:03
 * Description:
 */

public class GoodsInOrderAdapter extends RecyclerView.Adapter<GoodsInOrderAdapter.ViewHolder> {
    private Context mContext ;
    private List<OrderBean.GoodsBean> goodsBeans = new ArrayList<>();

    public GoodsInOrderAdapter(Context mContext, List<OrderBean.GoodsBean> goodsBeans) {
        this.mContext = mContext ;
        this.goodsBeans = goodsBeans ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_order_goods, viewGroup, false);
        return new GoodsInOrderAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(mContext)
                .load(goodsBeans.get(i).getImg())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(viewHolder.imgGoods);
        viewHolder.tvPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeans.get(i).getPrice());
        viewHolder.tvSku.setText(goodsBeans.get(i).getSpec_txt() + "×"
                + goodsBeans.get(i).getNum_total());
        viewHolder.tvTitle.setText(goodsBeans.get(i).getTitle());
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
