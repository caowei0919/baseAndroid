package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.os.CountDownTimer;
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
import com.zj.wz.wbyx.wbyxAndroid.bean.GroupItemBean;
import com.zj.wz.wbyx.wbyxAndroid.utils.WxUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: GroupAdapter
 * Author: 曹伟
 * Date: 2019/10/21 15:15
 * Description:拼团列表适配器
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private Context mContext ;
    private List<GroupItemBean> groupItemBeans = new ArrayList<>();
    private CountDownTimer timer ;

    public GroupAdapter(Context mContext, List<GroupItemBean> groupItemBeans) {
        this.mContext = mContext ;
        this.groupItemBeans = groupItemBeans ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group, parent, false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgIsColonel.setVisibility(groupItemBeans.get(position).getIs_host().equals("1")
                ? View.VISIBLE : View.GONE);
        holder.tvTitle.setText(groupItemBeans.get(position).getGoods_name());
        holder.tvGroupNum.setText(groupItemBeans.get(position).getGroup_number()
                + mContext.getResources().getString(R.string.group_num));
        holder.tvRetailPrice.setText(mContext.getResources().getString(R.string.rmb)
                + groupItemBeans.get(position).getSell_price());
        holder.tvVipPrice.setText(mContext.getResources().getString(R.string.rmb)
                + groupItemBeans.get(position).getGroup_price());
        Glide.with(mContext)
                .load(groupItemBeans.get(position).getImage())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGroup);
        switch (groupItemBeans.get(position).getGroup_status()){
            case "0" :      //拼团失败
                holder.tvGroupTime.setText(groupItemBeans.get(position).getAddtime());
                holder.tvGroupStatus.setText(mContext.getResources()
                        .getString(R.string.group_fail_and_refund));
                holder.tvGroupStatus.setTextColor(mContext.getResources()
                        .getColor(R.color.c_666666));
                break;

            case "1" :      //拼团中
                holder.imgGroupTime.setVisibility(View.VISIBLE);
                holder.btnShare.setVisibility(View.VISIBLE);
                //倒计时
                timer = new CountDownTimer(Long.valueOf(groupItemBeans.get(position).getAddtime())
                        ,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        long hour = millisUntilFinished/(1000*60*60);
                        long minute = (millisUntilFinished - hour*(1000*60*60))/(1000*60);
                        long second = (millisUntilFinished - hour * (1000*60*60)
                                - minute * (1000 * 60)) /1000;
                        holder.tvGroupTime.setText(mContext.getResources()
                                .getString(R.string.time_remaining) + "\r"
                                + hour + ":" + minute + ":" + second);
                    }

                    @Override
                    public void onFinish() {

                    }
                };
                timer.start();
                holder.btnShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WxUtils.share();
                    }
                });
                break;

            case "2" :      //拼团成功
                holder.tvGroupTime.setText(groupItemBeans.get(position).getAddtime());
                holder.tvGroupStatus.setText(mContext.getResources()
                        .getString(R.string.group_success));
                holder.tvGroupStatus.setTextColor(mContext.getResources()
                        .getColor(R.color.c_666666));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return groupItemBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.btn_share)
        Button btnShare ;   //分享
        @BindView(R.id.tv_groupNum)
        TextView tvGroupNum ;   //几人团
        @BindView(R.id.tv_vipPrice)
        TextView tvVipPrice ;   //会员价
        @BindView(R.id.tv_retailPrice)
        TextView tvRetailPrice ;    //零售价
        @BindView(R.id.tv_title)
        TextView tvTitle ;  //标题
        @BindView(R.id.img_group)
        ImageView imgGroup ;    //团图片
        @BindView(R.id.img_isColonel)
        ImageView imgIsColonel ;    //团长图标
        @BindView(R.id.tv_groupTime)
        TextView tvGroupTime ;  //拼团时间（成团倒计时）
        @BindView(R.id.img_groupTime)
        ImageView imgGroupTime ;    //倒计时图标（拼团中显示）
        @BindView(R.id.tv_groupStatus)
        TextView tvGroupStatus ;    //拼团状态

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
