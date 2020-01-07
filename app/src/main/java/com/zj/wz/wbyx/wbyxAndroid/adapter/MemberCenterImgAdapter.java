package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.wbyxAndroid.bean.MemberCenterBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: MemberCenterImgAdapter
 * Author: 曹伟
 * Date: 2019/9/23 20:02
 * Description: 会员中心展示图片适配器
 */

public class MemberCenterImgAdapter extends RecyclerView.Adapter<MemberCenterImgAdapter.VH> {
    private Context mContext ;
    private List<MemberCenterBean.ImgBean> mImageBeans = new ArrayList<>();

    public MemberCenterImgAdapter(Context context, List<MemberCenterBean.ImgBean> imgBeans) {
        mContext = context ;
        mImageBeans = imgBeans ;
    }

    @NonNull
    @Override
    public MemberCenterImgAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_member_img, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberCenterImgAdapter.VH holder, int position) {
        Glide.with(mContext)
                .load(mImageBeans.get(position).getImage())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgMemberPlay);
        if(mImageBeans.get(position).getTitle()
                .contains(mContext.getResources().getString(R.string.share_polite))){
            holder.btnShare.setVisibility(View.VISIBLE);
            holder.btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PLog.e(mContext.getResources().getString(R.string.share_polite));
                }
            });
        }
     }

    @Override
    public int getItemCount() {
        return mImageBeans.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        @BindView(R.id.img_member_play)
        ImageView imgMemberPlay ;   //展示的图片
        @BindView(R.id.btn_share)
        Button btnShare ;   //分享

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
