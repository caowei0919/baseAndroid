package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.MyGridLayoutManager;
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

public class MarketGoodsTagAdapter extends RecyclerView.Adapter<MarketGoodsTagAdapter.ViewHolder> {
    private List<MarketGoodsBean> goodsListBeans = new ArrayList<>();
    private Context mContext ;
    private addToShopCarClick carClick ;
    private MarketGoodsAdapter marketGoodsAdapter ;

    public MarketGoodsTagAdapter(Context mContext, List<MarketGoodsBean> goodsListBeans
            , addToShopCarClick carClick) {
        this.goodsListBeans = goodsListBeans;
        this.mContext = mContext;
        this.carClick = carClick ;
    }

    public interface addToShopCarClick{
        void onAddToShopCar(View view,int adapterPosition, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_display_goods_tag, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvGoodsTag.setText(goodsListBeans.get(position).getName());

        MyGridLayoutManager gridLayoutManager = new MyGridLayoutManager(mContext, 2
                , GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setScrollEnabled(true);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        holder.recycleTagGoods.setHasFixedSize(true);
        holder.recycleTagGoods.setNestedScrollingEnabled(false);
        holder.recycleTagGoods.setLayoutManager(gridLayoutManager);
        marketGoodsAdapter = new MarketGoodsAdapter(mContext, goodsListBeans.get(position).getGoods_list()
                , new MarketGoodsAdapter.addToShopCarClick() {
            @Override
            public void onAddToShopCar(View view, int index) {
                carClick.onAddToShopCar(view,position,index);
            }
        });
        holder.recycleTagGoods.setAdapter(marketGoodsAdapter);
    }

    @Override
    public int getItemCount() {
        return goodsListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_goodsTag)
        TextView tvGoodsTag ;  //标题
        @BindView(R.id.recycle_tagGoods)
        RecyclerView recycleTagGoods ;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
