package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * FileName: ChooseShopAdapter
 * Author: 曹伟
 * Date: 2019/11/14 13:49
 * Description: //选择店铺列表适配器
 */

public class ChooseShopAdapter extends RecyclerView.Adapter<ChooseShopAdapter.ViewHolder> {
    private Context mContext ;
    private List<DormitoryBean.ShoperBean> shoperBeans = new ArrayList<>(); //店铺集合
    private int index = 0 ;      //被选择店铺position

    public void setClick(OnShopSelectClick click) {
        this.click = click;
    }

    private OnShopSelectClick click ;

    public interface OnShopSelectClick{
        void onChoose(int position);
    }

    public int getPosition() {
        return index;
    }

    public void setPosition(int position) {
        this.index = position;
    }

    public ChooseShopAdapter(Context mContext, List<DormitoryBean.ShoperBean> shoperBeans
            ,int shopIndex) {
        this.mContext = mContext;
        this.shoperBeans = shoperBeans;
        this.index = shopIndex ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_shop_for_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.checkboxShop.setChecked(position == index);
        Glide.with(mContext)
                .load(shoperBeans.get(position).getAvatar())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgAvatar);
        holder.tvShopName.setText(shoperBeans.get(position).getName());
        holder.tvShopNotice.setText(shoperBeans.get(position).getShop_info());
        holder.tvClose.setVisibility(shoperBeans.get(position).getIs_open().equals("1")
                ? View.GONE : View.VISIBLE);
        holder.checkboxShop.setClickable(false);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onChoose(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoperBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.checkbox_shop)
        AppCompatCheckBox checkboxShop ;    //选中与否表示
        @BindView(R.id.img_avatar)
        CircleImageView imgAvatar ;   //商铺店主头像
        @BindView(R.id.tv_close)
        TextView tvClose ;
        @BindView(R.id.tv_shopName)
        TextView tvShopName ;   //商铺名称
        @BindView(R.id.tv_shopNotice)
        TextView tvShopNotice ;     //商铺公告

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
