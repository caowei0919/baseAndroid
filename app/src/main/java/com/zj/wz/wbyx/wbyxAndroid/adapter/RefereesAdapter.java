package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefereesBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * FileName: RefereesAdapter
 * Author: 曹伟
 * Date: 2019/10/8 15:16
 * Description: 推荐人列表适配器
 */

public class RefereesAdapter extends RecyclerView.Adapter<RefereesAdapter.ViewHolder> {
    private List<RefereesBean.MarketerBean> marketerBeans = new ArrayList<>();
    private Context mContext ;
    private onItemClickListener mListener ;

    public RefereesAdapter(Context context ,List<RefereesBean.MarketerBean> marketerBeans
            ,onItemClickListener listener) {
        mListener = listener ;
        mContext = context ;
        this.marketerBeans = marketerBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_referees, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(marketerBeans.get(position).getHead_image())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.circleAvatar);
        holder.tvIntroduce.setText(marketerBeans.get(position).getDescribe());
        holder.tvNickName.setText(marketerBeans.get(position).getTruename());
        holder.btnBecome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(marketerBeans.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return marketerBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.circle_avatar)
        CircleImageView circleAvatar ;  //头像
        @BindView(R.id.tv_nickName)
        TextView tvNickName ;   //昵称
        @BindView(R.id.tv_introduce)
        TextView tvIntroduce ;  //介绍
        @BindView(R.id.btn_become)
        Button btnBecome    ;   //成为推荐人

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface onItemClickListener{
        void onItemClick(RefereesBean.MarketerBean bean);
    }
}
