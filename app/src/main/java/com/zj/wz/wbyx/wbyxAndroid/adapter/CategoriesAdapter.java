package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.wbyxAndroid.activity.CategoriesActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.DormitoryActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.LoginActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.MarketActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.OtherWebActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.RecommendActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.SharePoliteActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.SubjectsActivity;
import com.zj.wz.wbyx.wbyxAndroid.bean.CategoriesBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: CategoriesAdapter
 * Author: 曹伟
 * Date: 2019/10/28 20:21
 * Description:分类适配器
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private List<CategoriesBean> categoriesBeans = new ArrayList<>();
    private Context mContext ;

    public CategoriesAdapter(List<CategoriesBean> categoriesBeans, Context mContext) {
        this.categoriesBeans = categoriesBeans;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categories, parent, false);
        return new CategoriesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(categoriesBeans.get(position).getName());
        Glide.with(mContext)
                .load(categoriesBeans.get(position).getIcon())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgIcon);
        /**
         * 跳转到分类页面
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 没有type，宿舍小店
                 */
                switch (categoriesBeans.get(position).getJump()){
                    case "0" :  //跳转原生页面
                        switch (categoriesBeans.get(position).getType()){
                            case "1" :  //推荐商城
                                    Intent recommendIntent = new Intent(mContext, RecommendActivity.class);
                                    mContext.startActivity(recommendIntent);
                                break;

                            case "2" : //超市
                                    Intent marketIntent = new Intent(mContext, MarketActivity.class);
                                    mContext.startActivity(marketIntent);
                                break;

                            case "3" : //宿舍小店
                                    if(!Constant.hasLogin()){
                                        mContext.startActivity(new Intent(mContext
                                                , LoginActivity.class));
                                        return;
                                    }
                                    Intent intent = new Intent(mContext, DormitoryActivity.class);
                                    mContext.startActivity(intent);
                                break;

                            case "4" :  //专题
                                    Intent subjectIntent = new Intent(mContext, SubjectsActivity.class);
                                    subjectIntent.putExtra("activity_id",categoriesBeans.get(position).getCate_id());
                                    mContext.startActivity(subjectIntent);
                                break;

                            case "5" :  //分享有礼
                                mContext.startActivity(new Intent(mContext
                                        , SharePoliteActivity.class));
                                break;

                            case "6" : //分类
                                    Intent categoriesIntent = new Intent(mContext
                                            , CategoriesActivity.class);
                                categoriesIntent.putExtra("cate_id"
                                        ,categoriesBeans.get(position).getCate_id());
                                    mContext.startActivity(categoriesIntent);
                                break;

                            case "7" :  //其他待确认

                                break;
                        }
                        break;

                    case "1" :  //跳转web页面
                        Intent urlIntent = new Intent(mContext, OtherWebActivity.class);
                        urlIntent.putExtra("url",categoriesBeans.get(position).getUrl());
                        mContext.startActivity(urlIntent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tvName ;   //分类名
        @BindView(R.id.img_icon)
        ImageView imgIcon ;     //分类图标

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
