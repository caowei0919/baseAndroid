
package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.ShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.event.GoodsInShopEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.ShopCarSelectAllEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: GoodsInShopAdapter
 * Author: 曹伟
 * Date: 2019/10/24 17:03
 * Description:购物车每项中商品列表适配器
 */

public class GoodsInShopAdapter extends RecyclerView.Adapter<GoodsInShopAdapter.ViewHolder> {
    private List<ShopCarBean.ShopBean.GoodsBean> goodsBeans = new ArrayList<>();
    private Context mContext ;
    private ShopCarBean.ShopBean bean ;
    private ShopCarAdapter shopCarAdapter ;
    private List<ShopCarBean.ShopBean> shopBeans = new ArrayList<>();

    public GoodsInShopAdapter(Context mContext, List<ShopCarBean.ShopBean.GoodsBean> goods
            , ShopCarBean.ShopBean bean, List<ShopCarBean.ShopBean> shopBeans
            , ShopCarAdapter shopCarAdapter) {
        this.goodsBeans = goods ;
        this.mContext = mContext ;
        this.bean = bean ;
        this.shopBeans = shopBeans ;
        this.shopCarAdapter = shopCarAdapter ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop_car_shop, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCommonPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeans.get(position).getPrice_normal());
        holder.tvGoodsName.setText(goodsBeans.get(position).getGoods_name());
        holder.tvGoodsSku.setText(goodsBeans.get(position).getSpecs_txt());
        holder.tvGoodsVipPrice.setText(mContext.getResources().getString(R.string.rmb)
                + goodsBeans.get(position).getPrice_vip());
        Glide.with(mContext)
                .load(goodsBeans.get(position).getGoods_img())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGoodsPicture);
        holder.edGoodNum.setText(goodsBeans.get(position).getCart_num());
        holder.checkboxGoods.setOnCheckedChangeListener(null);
        /**
         * 商品选中或者取消
         */
        holder.checkboxGoods.setChecked(goodsBeans.get(position).getIs_selected().equals("1"));
        holder.checkboxGoods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //更改item的选中状态的同时修改实体内的状态
                goodsBeans.get(position).setIs_selected(isChecked ? "1" : "0");
                boolean noSelect = false;
                //同时遍历内部所有实体的选中状态，从而来控制对外层选中状态的控制
                for (ShopCarBean.ShopBean.GoodsBean bean : goodsBeans) {
                    if (!bean.getIs_selected().equals("1")) {
                        noSelect = true;
                    }
                }
                bean.setSelected(!noSelect);
                shopCarAdapter.notifyDataSetChanged();
                if (!isChecked){    //有一个商品点击取消选中，则底部全选也无效
                    EventBus.getDefault().post(new ShopCarSelectAllEvent(false));
                }else{
                    boolean isSelectedAll = true ;
                    for (ShopCarBean.ShopBean bean : shopBeans) {
                        if (!bean.isSelected()) {
                            isSelectedAll = false ;
                        }
                    }
                    EventBus.getDefault().post(new ShopCarSelectAllEvent(isSelectedAll));
                }
                EventBus.getDefault().post(shopCarAdapter.getAllPrice());
            }
        });

        /**
         * 商品数量加
         */
        holder.imgBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GoodsInShopEvent(goodsBeans.get(position)
                        , GoodsInShopEvent.GoodsInShopEventTag.ADD));
            }
        });

        /**
         * 商品数量减
         */
        holder.imgBtSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GoodsInShopEvent(goodsBeans.get(position)
                        , GoodsInShopEvent.GoodsInShopEventTag.SUBTRACTION));
            }
        });

        /**
         * item点击进入详情页监听事件
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GoodsInShopEvent(goodsBeans.get(position)
                        , GoodsInShopEvent.GoodsInShopEventTag.ITEM));
            }
        });

        /**
         * 删除
         */
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GoodsInShopEvent(goodsBeans.get(position)
                        , GoodsInShopEvent.GoodsInShopEventTag.DELETE));
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.checkbox_goods)
        CheckBox checkboxGoods ;        //商品选中或取消
        @BindView(R.id.img_goodsPicture)
        ImageView imgGoodsPicture ;    //商品图片
        @BindView(R.id.tv_goodsName)
        TextView tvGoodsName ;  //商品名称
        @BindView(R.id.tv_goodsSku)
        TextView tvGoodsSku ;   //商品规格
        @BindView(R.id.tv_goodsVipPrice)
        TextView tvGoodsVipPrice ;  //vip价格
        @BindView(R.id.tv_commonPrice)
        TextView tvCommonPrice ;    //普通价格
        @BindView(R.id.tv_remind)
        TextView tvRemind ; //宿舍小店送货范围提醒
        @BindView(R.id.imgBt_subtraction)
        ImageButton imgBtSubtraction ;  //数量减少
        @BindView(R.id.imgBt_add)
        ImageButton imgBtAdd ;  //数量增加
        @BindView(R.id.ed_goodNum)
        TextView edGoodNum ;    //选择数量
        @BindView(R.id.btn_delete)
        Button btnDelete ;  //删除

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
