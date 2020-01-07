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
import com.zj.wz.wbyx.wbyxAndroid.bean.MarketGoodsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: CategoriesGoodsAdapter
 * Author: 曹伟
 * Date: 2019/11/9 19:30
 * Description:分类商品适配器
 */

public class MarketGoodsAdapter extends RecyclerView.Adapter<MarketGoodsAdapter.ViewHolder> {
    private List<MarketGoodsBean.GoodsListBean> goodsListBeans = new ArrayList<>();
    private Context mContext ;
    private addToShopCarClick carClick ;

    public MarketGoodsAdapter(Context mContext, List<MarketGoodsBean.GoodsListBean> goodsListBeans
            , addToShopCarClick carClick) {
        this.goodsListBeans = goodsListBeans;
        this.mContext = mContext;
        this.carClick = carClick ;
    }

    public interface addToShopCarClick{
        void onAddToShopCar(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_display_goods, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (position){
            case 0 :
                holder.imgTop.setImageResource(R.mipmap.icon_top_one);
                break;

            case 1 :
                holder.imgTop.setImageResource(R.mipmap.icon_top_two);
                break;

            case 2 :
                holder.imgTop.setImageResource(R.mipmap.icon_top_three);
                break;
        }

        holder.tvNomPrice.setText(mContext.getResources().getString(R.string.the_retail_price)
                + goodsListBeans.get(position).getSell_price());
        holder.tvVipPrice.setText(mContext.getResources().getString(R.string.the_vip_price)
                + goodsListBeans.get(position).getVip_price());
        Glide.with(mContext)
                .load(goodsListBeans.get(position).getImage())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGoodsPicture);
        holder.tvTitle.setText(goodsListBeans.get(position).getName());
        holder.imgAddToShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carClick.onAddToShopCar(holder.imgAddToShopCar,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodsListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_goodsPicture)
        ImageView imgGoodsPicture ; //图片
        @BindView(R.id.tv_title)
        TextView tvTitle ;  //标题
        @BindView(R.id.tv_vipPrice)
        TextView tvVipPrice ;   //会员价
        @BindView(R.id.tv_nomPrice)
        TextView tvNomPrice ;   //零售价
        @BindView(R.id.img_addToShopCar)
        ImageView imgAddToShopCar ;  //加入购物车
        @BindView(R.id.img_top)
        ImageView imgTop ;      //标签

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
