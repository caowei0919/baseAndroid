package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.SchoolBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: ChooseSchoolAdapter
 * Author: 曹伟
 * Date: 2019/10/10 12:00
 * Description:
 */

public class ChooseSchoolAdapter extends RecyclerView.Adapter<ChooseSchoolAdapter.ViewHolder> {
    private List<SchoolBean> dataBeans = new ArrayList<>();
    private Context mContext ;
    private OnItemClick listener ;
    public ChooseSchoolAdapter(Context mContext, List<SchoolBean> schoolBeans, OnItemClick listener) {
        dataBeans = schoolBeans ;
        this.mContext = mContext ;
        this.listener = listener ;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_choose_school, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(dataBeans.get(position).getName());
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(dataBeans.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tvName;    //学校名称
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClick{
        void onItemClick(SchoolBean bean);
    }
}
