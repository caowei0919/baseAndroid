package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.activity.SubjectsActivity;
import com.zj.wz.wbyx.wbyxAndroid.bean.SubjectsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: HomeSubjectsAdapter
 * Author: 曹伟
 * Date: 2019/10/29 19:07
 * Description:专题详情
 */

public class HomeSubjectsAdapter extends RecyclerView.Adapter<HomeSubjectsAdapter.ViewHolder>{
    private List<SubjectsBean> subjectsBeans = new ArrayList<>();
    private Context mContext ;
    private SubjectsGoodsAdapter subjectsGoodsAdapter ;
    private OnAddShopCarClick carClick ;

    public interface OnAddShopCarClick{
        void OnAddToShopCar(View addCar,int adapterPosition , int position);
    }

    public HomeSubjectsAdapter(List<SubjectsBean> subjectsBeans, Context mContext
            ,OnAddShopCarClick carClick) {
        this.subjectsBeans = subjectsBeans;
        this.mContext = mContext;
        this.carClick = carClick ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_subject, parent, false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(subjectsBeans.get(position).getBanner().getImg())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgSubject);
        /**
         * 跳转专题详情页面
         */
        holder.imgSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subjectsIntent = new Intent(mContext, SubjectsActivity.class);
                subjectsIntent.putExtra("activity_id"
                        ,subjectsBeans.get(position).getParams().getParam().getActivity_id());
                mContext.startActivity(subjectsIntent);
            }
        });

        subjectsGoodsAdapter = new SubjectsGoodsAdapter(subjectsBeans.get(position).getGoods()
                , mContext, new SubjectsGoodsAdapter.onClcickMoreListener() {
            @Override
            public void clickMore() {
                Intent subjectsIntent = new Intent(mContext,SubjectsActivity.class);
                subjectsIntent.putExtra("activity_id"
                        ,subjectsBeans.get(position).getParams().getParam().getActivity_id());
                mContext.startActivity(subjectsIntent);
            }

            @Override
            public void SubjectToShopCar(View view, int index) {
                carClick.OnAddToShopCar(view,position,index);
            }
        });
        GridLayoutManager gridLayoutManagerNewRecommend = new GridLayoutManager(mContext, 1
                , GridLayoutManager.HORIZONTAL, false);
        //设置布局管理器
        holder.recycleSubjectsGoods.setLayoutManager(gridLayoutManagerNewRecommend);
        //设置Adapter
        holder.recycleSubjectsGoods.setAdapter(subjectsGoodsAdapter);
    }

    @Override
    public int getItemCount() {
        return subjectsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_subject)
        ImageView imgSubject ;  //专题图片
        @BindView(R.id.recycle_subjectsGoods)
        RecyclerView recycleSubjectsGoods ;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
