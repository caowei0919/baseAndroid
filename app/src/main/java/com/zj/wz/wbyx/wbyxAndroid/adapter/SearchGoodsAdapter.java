package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.wbyxAndroid.activity.GoodsDetailActivity;
import com.zj.wz.wbyx.wbyxAndroid.bean.SearchGoodsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: SearchGoodsAdapter
 * Author: 曹伟
 * Date: 2019/11/29 13:41
 * Description:搜索结果列表适配器
 */

public class SearchGoodsAdapter extends RecyclerView.Adapter<SearchGoodsAdapter.ViewHolder> {
    private Context mContext ;
    private String warehouse_id  ;
    private AddCarListener listener ;

    public SearchGoodsAdapter(Context mContext, List<SearchGoodsBean> searchGoodsBeans
            , String warehouse_id ,AddCarListener listener) {
        this.mContext = mContext;
        this.searchGoodsBeans = searchGoodsBeans;
        this.warehouse_id = warehouse_id ;
        this.listener = listener ;
    }

    private List<SearchGoodsBean> searchGoodsBeans = new ArrayList<>();
    public interface AddCarListener{
        void onAddCarClick(int position) ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_display_subject_goods, parent, false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNomPrice.setText(mContext.getResources().getString(R.string.the_retail_price)
                + searchGoodsBeans.get(position).getSell_price());
        holder.tvVipPrice.setText(mContext.getResources().getString(R.string.the_vip_price)
                + searchGoodsBeans.get(position).getVip_price());
        Glide.with(mContext)
                .load(searchGoodsBeans.get(position).getImage())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGoodsPicture);
        holder.tvTitle.setText(searchGoodsBeans.get(position).getName());
        holder.relativeSoldOut.setVisibility(!searchGoodsBeans.get(position).getStore_nums().equals("0")
                ? View.VISIBLE : View.GONE);
        holder.imgAddToShopCar.setVisibility(!searchGoodsBeans.get(position).getStore_nums().equals("0")
                ? View.VISIBLE : View.GONE);
        holder.imgAddToShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddCarClick(position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("goods_type",searchGoodsBeans.get(position).getGoods_type());
                intent.putExtra("warehouse_id",warehouse_id);
                intent.putExtra("goods_id",searchGoodsBeans.get(position).getGoods_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchGoodsBeans.size();
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
        @BindView(R.id.relative_soldOut)
        RelativeLayout relativeSoldOut ;    //卖光了

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
