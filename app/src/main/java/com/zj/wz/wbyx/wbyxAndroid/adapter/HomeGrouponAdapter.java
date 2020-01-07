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
import com.zj.wz.wbyx.wbyxAndroid.bean.GrouponBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: HomeGrouponAdapter
 * Author: 曹伟
 * Date: 2019/10/29 18:02
 * Description:拼团
 */

public class HomeGrouponAdapter extends RecyclerView.Adapter<HomeGrouponAdapter.ViewHolder> {
    private List<GrouponBean.GoodsBean> goodsBeans = new ArrayList<>();
    private Context mContext ;

    public HomeGrouponAdapter(List<GrouponBean.GoodsBean> goodsBeans, Context mContext) {
        this.goodsBeans = goodsBeans;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_group, parent, false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(goodsBeans.get(position).getImg())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGoodsPicture);
        holder.tvGroupNum.setText(goodsBeans.get(position).getReq_num()
                + mContext.getResources().getString(R.string.group_num));
        holder.tvNomPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeans.get(position).getNormal_price());
        holder.tvVipPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeans.get(position).getActivity_price());
    }

    @Override
    public int getItemCount() {
        return goodsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_goodsPicture)
        ImageView imgGoodsPicture ;     //图片
        @BindView(R.id.tv_groupNum)
        TextView tvGroupNum ;   //团人数
        @BindView(R.id.tv_vipPrice)
        TextView tvVipPrice ;   //拼团价
        @BindView(R.id.tv_nomPrice)
        TextView tvNomPrice ;   //零售价

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
