package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.ShopCarBean;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: GoodsInShopAdapter
 * Author: 曹伟
 * Date: 2019/10/24 17:03
 * Description:购物车每项中商品列表适配器
 */

public class GoodsInvalidAdapter extends RecyclerView.Adapter<GoodsInvalidAdapter.ViewHolder> {
    private List<ShopCarBean.InvalidBean> invalidBeans = new ArrayList<>();
    private Context mContext ;

    public GoodsInvalidAdapter(Context mContext,  List<ShopCarBean.InvalidBean> invalidBeans) {
        this.mContext = mContext ;
        this.invalidBeans = invalidBeans ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop_car_shop_invalid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCommonPrice.setText(mContext.getResources().getString(R.string.rmb)
                + invalidBeans.get(position).getPrice_normal());
        holder.tvGoodsName.setText(invalidBeans.get(position).getGoods_name());
        holder.tvGoodsSku.setText(invalidBeans.get(position).getSpecs_txt());
        holder.tvGoodsVipPrice.setText(mContext.getResources().getString(R.string.rmb)
                + invalidBeans.get(position).getPrice_vip());
        Glide.with(mContext)
                .load(invalidBeans.get(position).getGoods_img())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGoodsPicture);

        /**
         * 删除
         */
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post(new GoodsInShopEvent(invalidBeans.get(position)
//                        , GoodsInShopEvent.GoodsInShopEventTag.DELETE));
            }
        });
    }

    @Override
    public int getItemCount() {
        return invalidBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_goodsPicture)
        ImageView imgGoodsPicture ;    //商品图片
        @BindView(R.id.tv_goodsName)
        TextView tvGoodsName ;  //商品名称
        @BindView(R.id.tv_goodsSku)
        TextView tvGoodsSku ;   //商品规格
        @BindView(R.id.tv_goodsVipPrice)
        TextView tvGoodsVipPrice ;  //vip价格
        @BindView(R.id.tv_commonPrice)
        TextView tvCommonPrice ;    //普通价格
        @BindView(R.id.btn_delete)
        Button btnDelete ;  //删除

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
