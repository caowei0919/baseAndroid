package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefundCheckBean;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: RefundOrderAdapter
 * Author: 曹伟
 * Date: 2019/9/19 15:16
 * Description:
 */

public class RefundOrderAdapter extends RecyclerView.Adapter<RefundOrderAdapter.VH>{
    private Context mContext ;
    private List<RefundCheckBean> goodsBeans = new ArrayList<>();
    private CheckClickListener listener ;

    public RefundOrderAdapter(Context mContext, List<RefundCheckBean> goodsBeans, CheckClickListener listener) {
        this.mContext = mContext;
        this.goodsBeans = goodsBeans;
        this.listener = listener ;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_refund_goods, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.tvGoodsName.setText(goodsBeans.get(position).getBean().getTitle());
        holder.tvGoodsPrice.setText("¥" + goodsBeans.get(position).getBean().getPrice());
        holder.tvGoodsSkuNum.setText(goodsBeans.get(position).getBean().getSpecs().get(0) + "×" + goodsBeans.get(position).getBean().getNum());
        Glide.with(mContext)
                .load(goodsBeans.get(position).getBean().getImg())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGood);
        holder.checkGood.setChecked(goodsBeans.get(position).isCheck());
        holder.checkGood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                holder.checkGood.setChecked(isChecked);
                listener.click(position,isChecked);
            }
        });
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

    public interface CheckClickListener{
        void click(int position, boolean isCheck);
    }
}
