package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.utils.PLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: LoadImageAdapter
 * Author: 曹伟
 * Date: 2019/10/12 16:07
 * Description:
 */

public class LoadImageAdapter extends RecyclerView.Adapter<LoadImageAdapter.ViewHolder> {
    private Context mContext ;
    private List<String> imgUriString = new ArrayList<>();
    private OnAddPictureClick click ;

    public LoadImageAdapter(Context mContext, List<String> imgUriString
            , OnAddPictureClick listener) {
        this.mContext = mContext ;
        this.imgUriString = imgUriString ;
        click = listener ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_img_and_del, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PLog.e(imgUriString.get(position));
        Glide.with(mContext)
                .load(imgUriString.get(position))
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgFeedBack);
        holder.imgDelete.setVisibility(TextUtils.isEmpty(imgUriString.get(position))
                ? View.GONE : View.VISIBLE);

        /**
         * 删除点击事件
         */
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgUriString.remove(position);
                if(imgUriString.size() == 0 || imgUriString.size() == 5){
                    imgUriString.add("");
                }
                notifyDataSetChanged();
            }
        });

        /**
         * 添加图片
         */
        holder.imgFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(imgUriString.get(position))){
                    return;
                }
                click.onAddPictureClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return imgUriString.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_feedBack)
        ImageView imgFeedBack ;    //选择的照片
        @BindView(R.id.img_delete)
        ImageView imgDelete ;   //删除

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnAddPictureClick{
        void onAddPictureClick(int position);
    }
}
