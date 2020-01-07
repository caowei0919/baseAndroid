
package com.zj.wz.wbyx.baseandroid.view;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SkuBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 全局实现购买选择规格dialog
 */
public class BuyGoodChooseSkuDialog extends LinearLayout implements TagFlowLayout.OnSelectListener
        , TextWatcher
        ,View.OnClickListener{
    @BindView(R.id.img_close)
    ImageView imgClose ;    //关闭视图
    @BindView(R.id.tv_goodsName)
    TextView tvGoodsName ;  //商品名称
    @BindView(R.id.tv_vipPrice)
    TextView tvVipPrice ;   //vip价格
    @BindView(R.id.tv_storeNum)
    TextView tvStoreNum ;   //库存
    @BindView(R.id.imgBt_subtraction)
    ImageButton imgBtSubtraction ;  //减数量
    @BindView(R.id.tv_SelectOrStoreNum)
    TextView tvSelectOrStoreNum ;   //显示规格或者显示库存
    @BindView(R.id.ed_goodNum)
    EditText edGoodNum ;    //数量输入框
    @BindView(R.id.imgBt_add)
    ImageButton imgBtAdd ;  //加数量
    @BindView(R.id.tv_sure)
    TextView tvSure ;   //确定
    @BindView(R.id.img_goodsPicture)
    ImageView imgGoodsPicture ; //商品图片
    @BindView(R.id.flow_sku)
    TagFlowLayout flowSku ;    //规格

    private int MAX_NUM = 100 ; //最大购买量（库存和限购较小值）
    private int MIN_NUM = 1 ;   //最小购买量
    private int STEP_LONG = 1 ; //步长
    private boolean isLimit = false ;   //是否限购

    private int buyNum = 0 ;    //购买数量

    private Context mContext ;
    private GoodDetailBean bean ;
    private List<SkuBean> skuBeans = new ArrayList<>();

    public void setDialogOnClickListener(DialogOnClickListener dialogOnClickListener) {
        this.dialogOnClickListener = dialogOnClickListener;
    }

    private DialogOnClickListener dialogOnClickListener ;

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private Runnable mPostRunnable = new Runnable() {
        @SuppressLint("StringFormatMatches")
        @Override
        public void run() {
            PLog.e("曹伟");
            if(TextUtils.isEmpty(edGoodNum.getText().toString().trim())){       //输入框无输入
                ToastUtils.showLongToast(String.format(mContext.getResources()
                        .getString(R.string.starting_at),MIN_NUM));
                edGoodNum.removeTextChangedListener(BuyGoodChooseSkuDialog.this);
                edGoodNum.setText(MIN_NUM + "");
                buyNum = MIN_NUM ;
                edGoodNum.clearFocus();
                edGoodNum.addTextChangedListener(BuyGoodChooseSkuDialog.this);
            }else if(Integer.valueOf(edGoodNum.getText().toString().trim()) > MAX_NUM){
                ToastUtils.showLongToast(isLimit ? String.format(mContext.getResources()
                        .getString(R.string.for_purchasing),MAX_NUM) :
                        String.format(mContext.getResources()
                                .getString(R.string.only_leave),MAX_NUM));
                edGoodNum.removeTextChangedListener(BuyGoodChooseSkuDialog.this);
                edGoodNum.setText(MAX_NUM + "");
                buyNum = MAX_NUM ;
                edGoodNum.clearFocus();
                edGoodNum.addTextChangedListener(BuyGoodChooseSkuDialog.this);
            }else if((Integer.valueOf(edGoodNum.getText().toString().trim())) % STEP_LONG != 0){
                ToastUtils.showLongToast(String.format(mContext.getResources()
                        .getString(R.string.purchase_quantity),STEP_LONG));
                edGoodNum.removeTextChangedListener(BuyGoodChooseSkuDialog.this);
                edGoodNum.setText((Integer.valueOf(edGoodNum.getText().toString().trim()))
                        - ((Integer.valueOf(edGoodNum.getText().toString().trim())) % STEP_LONG) + "");
                buyNum = Integer.valueOf(edGoodNum.getText().toString().trim()) ;
                edGoodNum.clearFocus();
                edGoodNum.addTextChangedListener(BuyGoodChooseSkuDialog.this);
            }
        }
    };


    public BuyGoodChooseSkuDialog(Context context) {
        this(context, null);
        mContext = context;
        View.inflate(context, R.layout.dialog_buy_good_choose_sku, this);
        ButterKnife.bind(this);
        edGoodNum.addTextChangedListener(this);
        flowSku.setOnSelectListener(this);
    }

    public BuyGoodChooseSkuDialog(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public BuyGoodChooseSkuDialog(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(mPostRunnable != null){
            mHandler.removeCallbacks(mPostRunnable);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if(!checkSku()){
            edGoodNum.clearFocus();
            return;
        }
        mHandler.postDelayed(mPostRunnable,5000);
    }

    @SuppressLint("StringFormatMatches")
    @OnClick({R.id.imgBt_subtraction,R.id.imgBt_add,R.id.img_close,R.id.tv_sure})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBt_subtraction :   //减
                    if(checkSku()){
                        if(buyNum - STEP_LONG < MIN_NUM){
                            ToastUtils.showLongToast(String.format(mContext.getResources()
                                    .getString(R.string.starting_at),MIN_NUM));
                            return;
                        }
                        buyNum = buyNum - STEP_LONG ;
                        edGoodNum.removeTextChangedListener(this);
                        edGoodNum.setText(buyNum+"");
                        edGoodNum.clearFocus();
                        edGoodNum.addTextChangedListener(this);
                    }
                break;

            case R.id.imgBt_add :   //加
                    if(checkSku()){
                        if(buyNum + STEP_LONG > MAX_NUM){
                            ToastUtils.showLongToast(isLimit ? String.format(mContext.getResources()
                                    .getString(R.string.for_purchasing),MAX_NUM) :
                                    String.format(mContext.getResources()
                                    .getString(R.string.only_leave),MAX_NUM));
                            return;
                        }
                        buyNum = buyNum + STEP_LONG ;
                        edGoodNum.removeTextChangedListener(this);
                        edGoodNum.setText(buyNum+"");
                        edGoodNum.clearFocus();
                        edGoodNum.addTextChangedListener(this);
                    }
                break;

            case R.id.img_close :   //关闭视图
                if (dialogOnClickListener != null)
                    dialogOnClickListener.closeOnclick();
                break;

            case R.id.tv_sure : //确定
                    if(!checkSku()){
                        return;
                    }else{
                        Map<String, Object> params = new HashMap<>();
                        if(bean.getDetails().getGoods_type().equals("1")){
                            params.put("goods_id", bean.getDetails().getGoods_id());
                            params.put("sku_id", bean.getSku_group()
                                    .get(Integer.valueOf(flowSku.getSelectedIds())).getSku_id());
                            params.put("shop_num", buyNum);
                            params.put("order_type", "recom");
                            params.put("change_type", "add");
                        }else{
                            Map<String, Object> shopCartMap = new HashMap<>();
                            shopCartMap.put("product_id", bean.getSku_group()
                                    .get(Integer.valueOf(flowSku.getSelectedIds())).getProduct_id());
                            shopCartMap.put("goods_id", bean.getDetails().getGoods_id());
                            shopCartMap.put("shop_num", buyNum);
                            shopCartMap.put("sku_id",  bean.getSku_group()
                                    .get(Integer.valueOf(flowSku.getSelectedIds())).getSku_id());
                            String shopCart = new Gson().toJson(shopCartMap);
                            params.put("order_type", "day");
                            params.put("warehouse_id", bean.getDetails().getWarehouse_id());
                            params.put("change_type", "add");
                            params.put("shop_cart", shopCart);
                        }
                        dialogOnClickListener.confirmOnClick(bean.getDetails().getGoods_type(),params);
                    }
                break;
        }
    }

    /**
     * 规格数据设置
     * @return
     */
    private boolean checkSku() {
        if(TextUtils.isEmpty(flowSku.getSelectedIds())){
            ToastUtils.showLongToast(mContext.getResources().getString(R.string.please_select_sku));
            return false ;
        }
        return true;
    }

    @Override
    public void onSelect(int position) {
        if (position != -1) {
            flowSku.reset();
            flowSku.setDefaultSelectId(position + "");
            edGoodNum.clearFocus();
            if(bean.getSku_group().get(position) != null){
                GoodDetailBean.SkuGroupBean skuBean = bean.getSku_group().get(position) ;
                //选中的商品
                tvSelectOrStoreNum.setText(mContext.getResources().getString(R.string.has_choose)
                        + skuBean.getSpec_value());
                //显示价格要判断是否是IVP
                if (Constant.isVipUser()) {  //是VIP
                    tvVipPrice.setText(mContext.getResources().getString(R.string.rmb) + skuBean.getVip_price());
                } else {  //不是vip
                    tvVipPrice.setText(mContext.getResources().getString(R.string.rmb) + skuBean.getSell_price());
                }
                edGoodNum.setText(skuBean.getMinimum() );
                tvStoreNum.setText(mContext.getResources().getString(R.string.inventory)
                        + skuBean.getStore_nums());  //规格库存
                //TODO zan 默认图片
                PLog.e(skuBean.getImage());
                Glide.with(mContext)
                        .load(skuBean.getImage())
                        .error(R.mipmap.img_default)
                        .placeholder(R.mipmap.img_default)
                        .dontAnimate()
                        .into(imgGoodsPicture);//规格图片`

                isLimit = !skuBean.getLimit_num().equals("0");
                MIN_NUM = Integer.valueOf(skuBean.getMinimum());
                MAX_NUM = isLimit ? Integer.valueOf(skuBean.getLimit_num())
                        : Integer.valueOf(skuBean.getStore_nums());
                STEP_LONG = Integer.valueOf(skuBean.getStep_size());
                buyNum = MIN_NUM ;
            }
        }
    }

    @Override
    public void onOutLimit() {

    }


    @SuppressLint("SetTextI18n")
    public BuyGoodChooseSkuDialog setGoodsData(GoodDetailBean baseBean, int del_image) {
        if (baseBean != null) {
            //默认选中第一个商品规格
            bean = baseBean;
            tvGoodsName.setText(TextUtils.isEmpty(bean.getDetails().getName()) ? ""  : bean.getDetails().getName());
            skuBeans.clear();
            for(int i = 0 ; i < bean.getSku_group().size() ; i ++){
                GoodDetailBean.SkuGroupBean skuBean = bean.getSku_group().get(i);
                skuBeans.add(new SkuBean(i+"",skuBean.getSpec_value(),skuBean.getStore_nums()));
            }
            for (GoodDetailBean.SkuGroupBean skuBean : bean.getSku_group()) {
                if (!"0".equals(skuBean.getStore_nums())) {
                    //选中的商品
                    tvSelectOrStoreNum.setText(mContext.getResources().getString(R.string.has_choose)
                            + skuBean.getSpec_value());
                    //显示价格要判断是否是IVP
                    if (Constant.isVipUser()) {  //是VIP
                        tvVipPrice.setText(mContext.getResources().getString(R.string.rmb) + skuBean.getVip_price());
                    } else {  //不是vip
                        tvVipPrice.setText(mContext.getResources().getString(R.string.rmb) + skuBean.getSell_price());
                    }
                    edGoodNum.setText(skuBean.getMinimum() );
                    tvStoreNum.setText(mContext.getResources().getString(R.string.inventory)
                            + skuBean.getStore_nums());  //规格库存
                    //TODO zan 默认图片
                    Glide.with(mContext)
                            .load(skuBean.getImage())
                            .error(del_image)
                            .into(imgGoodsPicture);//规格图片`
                    break;
                }
            }
            flowSku.setFlowData(skuBeans);
        }
        return this;
    }

    /**
     * dialog对外暴露的关闭，确定按钮点击事件
     */
    public interface DialogOnClickListener {
        //关闭
        void closeOnclick();
        //确定
        void confirmOnClick(String goods_type, Map<String, Object> params);
    }
}
