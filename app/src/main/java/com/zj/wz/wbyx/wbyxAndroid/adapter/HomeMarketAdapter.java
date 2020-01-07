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
import com.zj.wz.wbyx.wbyxAndroid.bean.RecomBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: HomeMarketAdapter
 * Author: 曹伟
 * Date: 2019/10/29 15:10
 * Description:窝边超市模块适配器
 */

public class HomeMarketAdapter extends RecyclerView.Adapter<HomeMarketAdapter.ViewHolder> {
    private List<RecomBean.CityShopBean.GoodsBeanX> goodsBeanXList = new ArrayList<>();
    private Context mContext ;

    public HomeMarketAdapter(List<RecomBean.CityShopBean.GoodsBeanX> goodsBeanXList
            , Context mContext) {
        this.goodsBeanXList = goodsBeanXList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_market, parent, false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvVipPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeanXList.get(position).getVip_price());
        holder.tvNomPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeanXList.get(position).getNormal_price());
        Glide.with(mContext)
                .load(goodsBeanXList.get(position).getImg())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGoodsPicture);
    }

    @Override
    public int getItemCount() {
        return goodsBeanXList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_goodsPicture)
        ImageView imgGoodsPicture ; //商品图片
        @BindView(R.id.tv_vipPrice)
        TextView tvVipPrice  ;  //vip价格
        @BindView(R.id.tv_nomPrice)
        TextView tvNomPrice ;   //普通价格

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
