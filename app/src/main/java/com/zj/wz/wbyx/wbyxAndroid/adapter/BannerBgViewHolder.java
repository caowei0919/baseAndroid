package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zhouwei.mzbanner.holder.MZViewHolder;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.BannersBean;

import jp.wasabeef.blurry.Blurry;

/**
 * FileName: BannerViewHolder
 * Author: 曹伟
 * Date: 2019/10/28 17:51
 * Description:banner适配器
 */

public class BannerBgViewHolder implements MZViewHolder<BannersBean>{
    private ImageView mImageView;
    private Context mContext ;
    public BannerBgViewHolder(Context mContext) {
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
    public void onBind(Context context, int i, BannersBean bannersBean) {
        Glide.with(mContext).asBitmap().load(bannersBean.getImg())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource
                            , @Nullable Transition<? super Bitmap> transition) {
                        Blurry.with(mContext)
                                .radius(10)     //值越大越模糊
                                .sampling(2)
                                .async()
                                .from(resource)
                                .into(mImageView);
                    }
        });
    }
}
