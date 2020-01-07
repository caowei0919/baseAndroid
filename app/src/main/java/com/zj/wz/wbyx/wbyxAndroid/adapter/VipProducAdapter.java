package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class VipProducAdapter extends RecyclerView.Adapter<VipProducAdapter.ViewHolder> {
    private List<VipProducBean.ListBean> listBeans = new ArrayList<>();
    private Context mContext ;
    private int index = 0 ;
    private OnVipProductClick click;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }

    public VipProducAdapter(List<VipProducBean.ListBean> listBeans, Context mContext
            ,OnVipProductClick click) {
        this.listBeans = listBeans;
        this.mContext = mContext;
        this.click = click ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_vip_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(listBeans.get(position).getVip_name());
        holder.tvPrice.setText(listBeans.get(position).getPrice());
        holder.itemView.setSelected(position == index);
        if(position == index){
            click.onCheck(position);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onVipProductClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tvName ;   //名称
        @BindView(R.id.tv_price)
        TextView tvPrice ;  //价格

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnVipProductClick{
        void onVipProductClick(int position);
        void onCheck(int position) ;
    }
}
