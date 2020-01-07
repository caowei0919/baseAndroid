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
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryAddress;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: DormitoryAdapter
 * Author: 曹伟
 * Date: 2019/10/18 15:38
 * Description:宿舍小店地址
 */

public class DormitoryAdapter extends RecyclerView.Adapter<DormitoryAdapter.ViewHolder> {

    private List<DormitoryAddress> addressList = new ArrayList<>();
    private Context mContext ;
    private boolean isExpend = true ;  //是否可展开
    private OnDoritoryItemClick onDoritoryItemClick ;

    public boolean isExpend() {
        return isExpend;
    }

    public void setExpend(boolean expend) {
        isExpend = expend;
    }

    public DormitoryAdapter(Context mContext, List<DormitoryAddress> dormitoryAddressList
            ,OnDoritoryItemClick onDoritoryItemClick) {
        this.mContext = mContext ;
        addressList = dormitoryAddressList ;
        this.onDoritoryItemClick = onDoritoryItemClick ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dormitory_address, parent, false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(addressList.get(position).getName());
        holder.tvPhone.setText(addressList.get(position).getCellphone());
        holder.tvDetailAddress.setText(addressList.get(position).getAddress_detail());
        holder.checkboxDefault.setChecked(addressList.get(position).getIs_default().equals("1"));
        holder.checkboxDefault.setClickable(!addressList.get(position).getIs_default().equals("1"));
        //设置默认
        holder.checkboxDefault
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onDoritoryItemClick.setDefaultClick(addressList.get(position));
            }
        });

        //编辑宿舍地址
        holder.tvWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoritoryItemClick.onWriteClick(addressList.get(position));
            }
        });

        //删除宿舍地址（注：默认地址不能删除）
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addressList.get(position).getIs_default().equals("1")){
                    ToastUtils.showLongToast(mContext.getResources()
                            .getString(R.string.address_default_dont_delete));
                }else{
                    onDoritoryItemClick.onDeleteClick(addressList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return isExpend && addressList.size() > 2 ? 2 : addressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tvName ;   //姓名
        @BindView(R.id.tv_phone)
        TextView tvPhone    ;   //电话
        @BindView(R.id.tv_detailAddress)
        TextView tvDetailAddress ;  //详细地址
        @BindView(R.id.checkbox_default)
        CheckBox checkboxDefault ;  //默认地址
        @BindView(R.id.tv_write)
        TextView tvWrite ;  //编辑
        @BindView(R.id.tv_delete)
        TextView tvDelete ;     //删除

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    /**
     *
     */
    public interface OnDoritoryItemClick{
        void setDefaultClick(DormitoryAddress dormitoryAddress);         //设为默认
        void onWriteClick(DormitoryAddress dormitoryAddress);            //编辑
        void onDeleteClick(DormitoryAddress dormitoryAddress);           //删除
        void onItemClick(DormitoryAddress dormitoryAddress);             //整个item选择
    }
}
