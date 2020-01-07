package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.activity.GoodsDetailActivity;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodSortBean;

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

public class CategoriesGoodsAdapter extends RecyclerView.Adapter<CategoriesGoodsAdapter.ViewHolder> {
    private List<GoodSortBean.GoodsListBean> goodsListBeans = new ArrayList<>();
    private Context mContext ;
    private addToShopCarClick carClick ;

    public CategoriesGoodsAdapter(Context mContext,List<GoodSortBean.GoodsListBean> goodsListBeans
            ,addToShopCarClick carClick) {
        this.goodsListBeans = goodsListBeans;
        this.mContext = mContext;
        this.carClick = carClick ;
    }

    public interface addToShopCarClick{
        void onAddToShopCar(View view,int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_categories_good, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(goodsListBeans.get(position).getImage())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgPicture);
        holder.tvGoodsName.setText(goodsListBeans.get(position).getName());
        holder.tvVipPrice.setText(mContext.getResources().getString(R.string.the_vip_price)
                + goodsListBeans.get(position).getVip_price());
        holder.tvNomPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsListBeans.get(position).getSell_price());
        holder.tvInventory.setText(mContext.getResources().getString(R.string.inventory)
                + goodsListBeans.get(position).getStore_nums());
        holder.tvGoodType.setVisibility(goodsListBeans.get(position).getGoods_type().equals("2")
                ? View.VISIBLE : View.GONE);
        holder.imgAddToShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carClick.onAddToShopCar(holder.imgAddToShopCar,position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("goods_type",goodsListBeans.get(position).getGoods_type());
                intent.putExtra("warehouse_id"
                        ,goodsListBeans.get(position).getWarehouse_id());
                intent.putExtra("goods_id",goodsListBeans.get(position).getGoods_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodsListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_picture)
        ImageView imgPicture ;  //商品图片
        @BindView(R.id.tv_goodType)
        TextView tvGoodType ;   //窝边超市商品类别
        @BindView(R.id.tv_goodsName)
        TextView tvGoodsName ;  //商品名
        @BindView(R.id.tv_vipPrice)
        TextView tvVipPrice ;   //会员价
        @BindView(R.id.tv_nomPrice)
        TextView tvNomPrice ;   //零售价
        @BindView(R.id.tv_inventory)
        TextView tvInventory;   //零售价
        @BindView(R.id.img_addToShopCar)
        ImageView imgAddToShopCar ; //加入购物车

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
