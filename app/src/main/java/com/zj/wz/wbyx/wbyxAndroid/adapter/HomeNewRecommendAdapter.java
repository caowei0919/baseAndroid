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
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.wbyxAndroid.activity.GoodsDetailActivity;
import com.zj.wz.wbyx.wbyxAndroid.bean.NewRecommendBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: HomeNewRecommendAdapter
 * Author: 曹伟
 * Date: 2019/10/29 16:39
 * Description:
 */

public class HomeNewRecommendAdapter extends RecyclerView.Adapter<HomeNewRecommendAdapter.ViewHolder> {
    private List<NewRecommendBean.GoodsBean> goodsBeans = new ArrayList<>();
    private Context mContext ;
    private OnAddToshopCarListener listener ;

    public interface OnAddToshopCarListener{
        void OnNewRecommendAddToShopCar(View addCar,int position);
    }

    public HomeNewRecommendAdapter(List<NewRecommendBean.GoodsBean> newRecommendGoods
            , Context mContext,OnAddToshopCarListener listener) {
        this.goodsBeans = newRecommendGoods ;
        this.mContext = mContext ;
        this.listener = listener ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_new_recommend, parent, false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(goodsBeans.get(position).getTitle());
        holder.tvNomPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeans.get(position).getNormal_price());
        holder.tvVipPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeans.get(position).getVip_price());
        Glide.with(mContext)
                .load(goodsBeans.get(position).getImg())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGoodsPicture);
        holder.imgAddToShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnNewRecommendAddToShopCar(holder.imgAddToShopCar,position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLog.e("goods_type === " + goodsBeans.get(position).getGoods_type() + ",goods_id == " + goodsBeans.get(position).getGoods_id());
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("goods_type",goodsBeans.get(position).getGoods_type());
                intent.putExtra("warehouse_id"
                        ,goodsBeans.get(position).getWarehouse_id());
                intent.putExtra("goods_id",goodsBeans.get(position).getGoods_id());
                mContext.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return goodsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_addToShopCar)
        ImageView imgAddToShopCar ;     //加入购物车
        @BindView(R.id.tv_nomPrice)
        TextView tvNomPrice ;       //零售价
        @BindView(R.id.tv_vipPrice)
        TextView tvVipPrice ;   //会员价
        @BindView(R.id.tv_title)
        TextView tvTitle ;  //标题
        @BindView(R.id.img_goodsPicture)
        ImageView imgGoodsPicture ; //商品图片

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
