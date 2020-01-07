package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhouwei.mzbanner.holder.MZViewHolder;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryBean;


/**
 * FileName: BannerViewHolder
 * Author: 曹伟
 * Date: 2019/10/28 17:51
 * Description:banner适配器
 */

public class BannerDormitoryViewHolder implements MZViewHolder<DormitoryBean.BannersBean>{
    private ImageView mImageView;
    private Context mContext ;
    public BannerDormitoryViewHolder(Context mContext) {
        this.mContext = mContext ;
    }

    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
        mImageView = (ImageView) view.findViewById(R.id.img_banner);
        return view;
    }

    @Override
    public void onBind(Context context, int i, DormitoryBean.BannersBean bannersBean) {
        Glide.with(mContext)
                .load(bannersBean.getImg())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(mImageView);
    }
}
