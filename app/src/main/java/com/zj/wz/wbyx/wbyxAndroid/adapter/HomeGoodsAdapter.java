package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.RadiusBackgroundSpan;
import com.zj.wz.wbyx.wbyxAndroid.activity.GoodsDetailActivity;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: HomeGoodsAdapter
 * Author: 曹伟
 * Date: 2019/11/8 18:46
 * Description: 首页商品展示适配器
 */

public class HomeGoodsAdapter extends RecyclerView.Adapter<HomeGoodsAdapter.ViewHolder> {
    private Context mContext ;
    private List<HomeGoodsOfAllBean> goods = new ArrayList<>();
    private OnAddToShopCarClick carClick ;

    public HomeGoodsAdapter(Context mContext, List<HomeGoodsOfAllBean> allGoods
            ,OnAddToShopCarClick carClick) {
        this.mContext = mContext ;
        goods = allGoods ;
        this.carClick = carClick ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_display_goods, parent, false);
        return new ViewHolder(v) ;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNomPrice.setText(mContext.getResources().getString(R.string.the_retail_price)
                + goods.get(position).getSell_price());
        holder.tvVipPrice.setText(mContext.getResources().getString(R.string.the_vip_price)
                + goods.get(position).getVip_price());
        Glide.with(mContext)
                .load(goods.get(position).getImage())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGoodsPicture);
//        if(goods.get(position).)
//        String content =
//        SpannableStringBuilder spannable = new SpannableStringBuilder(content);
        holder.tvTitle.setText(goods.get(position).getName());
        holder.imgAddToShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carClick.addCarClick(holder.imgAddToShopCar,position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("goods_type",goods.get(position).getGoods_type());
                intent.putExtra("warehouse_id"
                        ,goods.get(position).getWarehouse_id());
                intent.putExtra("goods_id",goods.get(position).getGoods_id());
                mContext.startActivity(intent);
            }
        });
    }

    public interface OnAddToShopCarClick{
        void addCarClick(View addCar,int position);
    }

    @Override
    public int getItemCount() {
        return goods.size();
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
