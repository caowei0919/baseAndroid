package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefundDetailsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: RefundOrderAdapter
 * Author: 曹伟
 * Date: 2019/9/19 15:16
 * Description:
 */

public class ConfirmRefundOrderAdapter extends RecyclerView.Adapter<ConfirmRefundOrderAdapter.VH>{
    private Context mContext ;
    private List<RefundDetailsBean.GoodsBean> goodsBeans = new ArrayList<>();

    public ConfirmRefundOrderAdapter(Context mContext, List<RefundDetailsBean.GoodsBean> goodsBeans) {
        this.mContext = mContext;
        this.goodsBeans = goodsBeans;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_refund_goods, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.tvGoodsName.setText(goodsBeans.get(position).getGoods_name());
        holder.tvGoodsPrice.setText("¥" + (Constant.isVipUser()
                ? goodsBeans.get(position).getPrice_vip() : goodsBeans.get(position).getPrice_normal()));
        holder.tvGoodsSkuNum.setText(goodsBeans.get(position)
                .getGoods_name() + "×" + goodsBeans.get(position).getNum());
        Glide.with(mContext)
                .load(goodsBeans.get(position).getGoods_img())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGood);
        holder.checkGood.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return goodsBeans.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        CheckBox checkGood ;
        ImageView imgGood ;
        TextView tvGoodsName ;
        TextView tvGoodsSkuNum ;
        TextView tvGoodsPrice ;

        public VH(View itemView) {
            super(itemView);
            checkGood = itemView.findViewById(R.id.check_good);
            imgGood = itemView.findViewById(R.id.img_good);
            tvGoodsName = itemView.findViewById(R.id.tv_goodsName);
            tvGoodsSkuNum = itemView.findViewById(R.id.tv_goodsSku_num);
            tvGoodsPrice = itemView.findViewById(R.id.tv_goodsPrice);
         }
    }
}
