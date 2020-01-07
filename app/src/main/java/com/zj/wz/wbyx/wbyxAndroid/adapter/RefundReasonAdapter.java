package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.wbyxAndroid.bean.ReasonBean;

import java.util.List;


/**
 * FileName: RefundOrderAdapter
 * Author: 曹伟
 * Date: 2019/9/19 15:16
 * Description:
 */

public class RefundReasonAdapter extends RecyclerView.Adapter<RefundReasonAdapter.VH>{
    private Context mContext ;
    private List<ReasonBean> goodsBeans ;
    private CheckReasonClickListener listener ;

    public RefundReasonAdapter(Context mContext, List<ReasonBean> bean, CheckReasonClickListener listener) {
        this.mContext = mContext;
        this.goodsBeans = bean;
        this.listener = listener ;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_refund_reason, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.tvReason.setText(goodsBeans.get(position).getReason());
        holder.checkGood.setChecked(goodsBeans.get(position).isCheck());
        holder.checkGood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                holder.checkGood.setChecked(isChecked);
                listener.clickReason(position,isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        PLog.e("getItemCount() === " + goodsBeans.size());
        return goodsBeans.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        CheckBox checkGood ;
        TextView tvReason ;

        public VH(View itemView) {
            super(itemView);
            checkGood = itemView.findViewById(R.id.check_good);
            tvReason = itemView.findViewById(R.id.tv_reason);
         }
    }

    public interface CheckReasonClickListener{
        void clickReason(int position, boolean isCheck);
    }
}
