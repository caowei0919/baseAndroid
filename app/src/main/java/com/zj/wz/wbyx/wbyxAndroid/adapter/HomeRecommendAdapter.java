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
 * FileName: HomeDormitoryAdapter
 * Author: 曹伟
 * Date: 2019/10/29 14:25
 * Description:首页宿舍小店适配器
 */

public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendAdapter.ViewHolder> {
    private List<RecomBean.RecomShopBean.GoodsBean> goodsBeans = new ArrayList<>();
    private Context mContext ;

    public HomeRecommendAdapter(List<RecomBean.RecomShopBean.GoodsBean> goodsBeanXXList
            , Context mContext) {
        this.goodsBeans = goodsBeanXXList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_recommend, parent, false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvVipPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeans.get(position).getVip_price());
        holder.tvNomPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeans.get(position).getNormal_price());
        Glide.with(mContext)
                .load(goodsBeans.get(position).getImg())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGoodsPicture);
    }

    @Override
    public int getItemCount() {
        return goodsBeans.size();
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
