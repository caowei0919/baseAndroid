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
import com.zj.wz.wbyx.wbyxAndroid.bean.SubjectsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: SubjectsGoodsAdapter
 * Author: 曹伟
 * Date: 2019/10/29 19:36
 * Description:专题活动商品
 */

public class SubjectsGoodsAdapter extends RecyclerView.Adapter{
    private final int ITEM_COMMON = 1 ;   //普通列表
    private final int ITEM_MORE = 2 ; //查看更多
    private List<SubjectsBean.GoodsBean> goodsBeans = new ArrayList<>();
    private Context mContext ;
    private onClcickMoreListener listener ;


    public interface onClcickMoreListener{
        void clickMore();
        void SubjectToShopCar(View view,int position);
    }

    public SubjectsGoodsAdapter(List<SubjectsBean.GoodsBean> goodsBeans, Context mContext
            ,onClcickMoreListener listener) {
        this.goodsBeans = goodsBeans;
        this.mContext = mContext;
        this.listener = listener ;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case ITEM_MORE :
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_subject_more, parent, false);
                return new ViewHolderMore(view) ;

            default:
                View view1 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_subject_goods, parent, false);
                return new ViewHolder(view1) ;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder){
            ((ViewHolder)holder).tvTitle.setText(goodsBeans.get(position).getTitle());
            ((ViewHolder)holder).tvNomPrice.setText(mContext.getResources().getString(R.string.rmb)
                    + goodsBeans.get(position).getNormal_price());
            ((ViewHolder)holder).tvVipPrice.setText(mContext.getResources().getString(R.string.rmb)
                    + goodsBeans.get(position).getActivity_price());
            Glide.with(mContext)
                    .load(goodsBeans.get(position).getImg())
                    .error(R.mipmap.img_default)
                    .placeholder(R.mipmap.img_default)
                    .dontAnimate()
                    .into(((ViewHolder)holder).imgGoodsPicture);
            ((ViewHolder)holder).imgAddToShopCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.SubjectToShopCar(((ViewHolder)holder).imgAddToShopCar,position);
                }
            });
            ((ViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                    intent.putExtra("goods_type",goodsBeans.get(position).getGoods_type());
                    intent.putExtra("warehouse_id"
                            ,goodsBeans.get(position).getWarehouse_id());
                    intent.putExtra("goods_id",goodsBeans.get(position).getGoods_id());
                    mContext.startActivity(intent);
                }
            });
        }else if(holder instanceof ViewHolderMore){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clickMore();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return goodsBeans.size() > 5 ? 6 : goodsBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 5 ? ITEM_MORE : ITEM_COMMON;
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

    public class ViewHolderMore extends RecyclerView.ViewHolder{
        public ViewHolderMore(View itemView) {
            super(itemView);
        }
    }
}
