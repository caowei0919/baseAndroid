package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.wbyxAndroid.bean.ShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.event.DeleteAllInvalidEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.ShopCarSelectAllEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.UpdataPriceAndNumEvent;
import com.zj.wz.wbyx.wbyxAndroid.utils.Arith;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: ShopCarAdapter
 * Author: 曹伟
 * Date: 2019/10/24 15:46
 * Description:购物车Recycle的adapter
 */

public class ShopCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int ITEM_SHOP = 1;
    private int ITEM_INVALID = 2;

    private Context mContext ;
    private List<ShopCarBean.ShopBean> shopBeans = new ArrayList<>();
    private List<ShopCarBean.InvalidBean> invalidBeans = new ArrayList<>();
    private GoodsInShopAdapter adapter ;
    private GoodsInvalidAdapter mGoodsInvalidAdapter ;

    public ShopCarAdapter(Context mContext, List<ShopCarBean.ShopBean> shopBeans
            ,List<ShopCarBean.InvalidBean> invalidBeans) {
        this.mContext = mContext ;
        this.shopBeans = shopBeans ;
        this.invalidBeans = invalidBeans ;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == shopBeans.size())
            return ITEM_INVALID;
        return ITEM_SHOP ;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == ITEM_SHOP) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_shop_car, parent, false);
            holder = new ShopCarAdapter.ViewHolder(view);
        } else if (viewType == ITEM_INVALID) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_shop_car_invalid, parent, false);
            holder = new ShopCarAdapter.ViewHolderInvalid(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder){
            ((ViewHolder)holder).itemView.setTag(shopBeans.get(position));    //给item做标记
            ((ViewHolder)holder).checkboxShopCar.setText(shopBeans.get(position).getShop_name());
            ((ViewHolder)holder).checkboxShopCar.setOnCheckedChangeListener(null);
            boolean isSelected = true ;
            for (ShopCarBean.ShopBean.GoodsBean bean:shopBeans.get(position).getGoods()) {
                if(!bean.getIs_selected().equals("1")){
                    isSelected = false ;
                }
            }
            shopBeans.get(position).setSelected(isSelected);
            ((ViewHolder)holder).checkboxShopCar.setChecked(shopBeans.get(position).isSelected());
            /**
             * 商铺下所有商品选择或者取消
             */
            ((ViewHolder)holder).checkboxShopCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //item选中状态改变，同时改变内部实体的选中状态
                    shopBeans.get(position).setSelected(isChecked);
                    //遍历所有商铺的选中状态，同时改变外部所有的全选
                    boolean noSelect = true;
                    //同时遍历内部所有实体的选中状态，从而来控制对外层选中状态的控制
                    for (ShopCarBean.ShopBean bean : shopBeans) {
                        if (!bean.isSelected()) {
                            noSelect = false;
                        }
                    }
                    PLog.e(""+noSelect);
                    EventBus.getDefault().post(new ShopCarSelectAllEvent(noSelect));
                    for (ShopCarBean.ShopBean.GoodsBean bean: shopBeans.get(position).getGoods()) {
                        bean.setIs_selected(isChecked ? "1" : "0");
                    }
                    notifyDataSetChanged();
                    //修改底部结算金额，和选中数量
                    EventBus.getDefault().post(getAllPrice());
                }
            });

            switch (shopBeans.get(position).getShop_type()){
                case "global" :     //窝边推荐
                    ((ViewHolder)holder).tvShopNotice.setText("");
                    break;

                case "dorm" :       //宿舍小店
                    ((ViewHolder)holder).tvShopNotice.setText(mContext.getResources()
                            .getString(R.string.notic_dorm));
                    break;

                case "city" :       //窝边超市
                    ((ViewHolder)holder).tvShopNotice.setText(mContext.getResources()
                            .getString(R.string.notice_city));
                    break;

                default:
                    ((ViewHolder)holder).tvShopNotice.setText("");
                    break;
            }

            //单个商家的商品列表不需要滑动，所以在这里禁止掉商品item的滑动事件
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext
                    , LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            adapter = new GoodsInShopAdapter(mContext,shopBeans.get(position).getGoods()
                    ,shopBeans.get(position),shopBeans,this);
            ((ViewHolder)holder).recycleShop.setLayoutManager(linearLayoutManager);

            ((ViewHolder)holder).recycleShop.setAdapter(adapter);
            //下面两句是防止刷新商品的recyclerView导致商家recyclerView也发生滑动
            ((ViewHolder)holder).recycleShop.setFocusableInTouchMode(false);
            ((ViewHolder)holder).recycleShop.requestFocus();
        }else if(holder instanceof ViewHolderInvalid){
            /**
             * 一键清空失效商品
             */
            ((ViewHolderInvalid)holder).tvShopNotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new DeleteAllInvalidEvent());
                }
            });
            //单个商家的商品列表不需要滑动，所以在这里禁止掉商品item的滑动事件
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext
                    , LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };

            mGoodsInvalidAdapter = new GoodsInvalidAdapter(mContext,invalidBeans);
            ((ViewHolderInvalid)holder).recycleGoodsInvalid.setLayoutManager(linearLayoutManager);

            ((ViewHolderInvalid)holder).recycleGoodsInvalid.setAdapter(mGoodsInvalidAdapter);
            //下面两句是防止刷新商品的recyclerView导致商家recyclerView也发生滑动
            ((ViewHolderInvalid)holder).recycleGoodsInvalid.setFocusableInTouchMode(false);
            ((ViewHolderInvalid)holder).recycleGoodsInvalid.requestFocus();
        }
    }

    public UpdataPriceAndNumEvent getAllPrice() {
        UpdataPriceAndNumEvent event = new UpdataPriceAndNumEvent();
        BigDecimal allprice =new  BigDecimal("0");
        BigDecimal allNum = new BigDecimal("0");
        if(shopBeans!=null){
            for (int i=0;i<shopBeans.size();i++){
                List<ShopCarBean.ShopBean.GoodsBean> mdata=shopBeans.get(i).getGoods();
                for (int y=0;y<mdata.size();y++){
                    if(mdata.get(y).getIs_selected().equals("1")){
                        BigDecimal interestRate = new BigDecimal(mdata.get(y).getCart_num()); //数量
                        allNum = Arith.add(allNum,interestRate);
                        double interest = Arith.mul(Double.valueOf(mdata.get(y)
                                .getPrice_normal()), interestRate);
                        allprice=allprice.add(BigDecimal.valueOf(interest));
                    }
                }
            }
            event.setAllNum(allNum.toString());
            event.setAllPrice(allprice.toString());
        }
        return  event;
    }

    @Override
    public int getItemCount() {
        PLog.e(invalidBeans.size()+"");
        return invalidBeans.size() > 0 ? shopBeans.size() + 1 : shopBeans.size();
    }

    public void setlectedAll(boolean isChecked) {
        //设置全选/全不选
            for(int i=0;i<shopBeans.size();i++){
                shopBeans.get(i).setSelected(isChecked);
                for (ShopCarBean.ShopBean.GoodsBean bean: shopBeans.get(i).getGoods()) {
                    bean.setIs_selected(isChecked ? "1" : "0");
                }
            }
            notifyDataSetChanged();
            //发送 消息
            EventBus.getDefault().post(getAllPrice());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.checkbox_shopCar)
        CheckBox checkboxShopCar ;  //单项选中或者取消选中全部
        @BindView(R.id.tv_shopNotice)
        TextView tvShopNotice ; //店铺公告
        @BindView(R.id.recycle_shop)
        RecyclerView recycleShop ;  //商品展示列表

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class ViewHolderInvalid extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_shopNotice)
        TextView tvShopNotice ;  //一键清空
        @BindView(R.id.recycle_goodsInvalid)
        RecyclerView recycleGoodsInvalid ;  //商品展示列表

        public ViewHolderInvalid(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
