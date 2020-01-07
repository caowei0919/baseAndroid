package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: CategoriesLeftMenuAdapter
 * Author: 曹伟
 * Date: 2019/11/9 15:58
 * Description: 商品分类左侧菜单适配器
 */

public class CategoriesLeftMenuAdapter
        extends RecyclerView.Adapter<CategoriesLeftMenuAdapter.ViewHolder> {
    private Context mContext ;
    private List<MenuItemBean> menuItemBeans = new ArrayList<>();

    public void setCateId(String cateId) {
        this.cateId = cateId;
        notifyDataSetChanged();
    }

    private String cateId ; //选中菜单id
    private OnItemClick click ;

    public CategoriesLeftMenuAdapter(Context mContext, List<MenuItemBean> menuItemBeans
            , String cateId ,OnItemClick click) {
        this.mContext = mContext;
        this.menuItemBeans = menuItemBeans;
        this.cateId = cateId ;
        this.click = click ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_categories_left, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvKind.setText(menuItemBeans.get(position).getName());
        if(menuItemBeans.get(position).getId().equals(cateId)){
            holder.tvKind.setSelected(true);
            holder.tvKind.setTextSize(14);
            holder.imgMenuBottom.setVisibility(View.VISIBLE);
        }else{
            holder.tvKind.setSelected(false);
            holder.tvKind.setTextSize(12);
            holder.imgMenuBottom.setVisibility(View.GONE);
        }
        holder.tvKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItemBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_kind)
        TextView tvKind ;
        @BindView(R.id.img_menuBottom)
        ImageView imgMenuBottom;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    /**
     * 对外提供的点击事件
     */
    public interface OnItemClick{
        void onItemClick(int position);
    }
}
