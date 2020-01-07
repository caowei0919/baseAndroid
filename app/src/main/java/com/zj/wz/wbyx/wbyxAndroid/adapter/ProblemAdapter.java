package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.ServiceBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: ProblemAdapter
 * Author: 曹伟
 * Date: 2019/10/14 22:36
 * Description:常见问题适配器
 */

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder> {
    private Context mContext ;
    private List<ServiceBean.ListBean> problems = new ArrayList<>();

    public ProblemAdapter(Context mContext, List<ServiceBean.ListBean> problems) {
        this.mContext = mContext ;
        this.problems = problems ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_problem, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(Html.fromHtml(problems.get(position).getTitle()));
        holder.tvContent.setText(Html.fromHtml(problems.get(position).getContent()));
    }

    @Override
    public int getItemCount() {
        return problems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView tvTitle ;  //标题
        @BindView(R.id.tv_content)
        TextView tvContent ;    //内容

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
