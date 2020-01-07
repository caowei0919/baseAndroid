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
import com.zj.wz.wbyx.wbyxAndroid.bean.VipProducBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: VipProducAdapter
 * Author: 曹伟
 * Date: 2019/11/20 9:40
 * Description:
 */

public class VipProducPayAdapter extends RecyclerView.Adapter<VipProducPayAdapter.ViewHolder> {
    private List<VipProducBean.PayListBean> payListBeans = new ArrayList<>();
    private Context mContext ;
    private OnPayTypeClick onPayTypeClick ;
    private int index = 0 ;

    public int getPosition() {
        return index;
    }

    public void setPosition(int position) {
        this.index = position;
        notifyDataSetChanged();
    }

    public VipProducPayAdapter(List<VipProducBean.PayListBean> payListBeans, Context mContext
            , OnPayTypeClick onPayTypeClick) {
        this.payListBeans = payListBeans;
        this.mContext = mContext;
        this.onPayTypeClick = onPayTypeClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_pay_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(payListBeans.get(position).getTitle());
        Glide.with(mContext)
                .load(payListBeans.get(position).getPicture())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgPayType);
        if(position == index){
            onPayTypeClick.onPayTypeCheck(position);
        }
        holder.tvSelect.setSelected(position == index);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPayTypeClick.onPayTypeClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return payListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_select)
        TextView tvSelect ;
        @BindView(R.id.tv_name)
        TextView tvName ;
        @BindView(R.id.img_payType)
        ImageView imgPayType ;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnPayTypeClick{
        void onPayTypeClick(int position) ;
        void onPayTypeCheck(int position);
    }
}
