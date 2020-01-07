package com.zj.wz.wbyx.wbyxAndroid.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import com.google.gson.Gson;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpFragment;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.BuyGoodChooseSkuDialog;
import com.zj.wz.wbyx.baseandroid.view.dialog.CommonDialog;
import com.zj.wz.wbyx.wbyxAndroid.adapter.MarketGoodsTagAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MarketGoodsBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MarketGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemBean;
import com.zj.wz.wbyx.wbyxAndroid.event.TagChangeEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.MarketFragmentPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.MarketFragmentView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * FileName: MarketFragment
 * Author: 曹伟
 * Date: 2019/11/12 9:51
 * Description:窝边超市
 */

public class MarketFragment extends BaseMvpFragment<MarketFragmentView, MarketFragmentPresenter>
        implements MarketFragmentView, MarketGoodsTagAdapter.addToShopCarClick{
    @BindView(R.id.recycle_allGoods)
    RecyclerView recycleAllGoods ;


    private MenuItemBean bean = null;
    private String areaId = "" ;        //区域id
    private String goodsType = "" ;     //商品类型
    private String tag = "" ;
    private List<MarketGoodsBean> goodsListBeans = new ArrayList<>();   //分类集合
    private MarketGoodsTagAdapter marketGoodsAdapter ;

    private OnAddToShopInFragmentClick click ;
    private Map<String,String> getGoodDetailsMap = new HashMap<>();
    private LinearLayoutManager layoutManagerSubject;

    public static MarketFragment newInstance(MenuItemBean bean,String GoodsType){
        PLog.e("MarketFragment");
        MarketFragment fragment = new MarketFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean",bean);
        bundle.putString("goods_type",GoodsType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupView(View rootView) {
        layoutManagerSubject = new LinearLayoutManager(mContext);
        //设置为垂直布局，这也是默认的
        layoutManagerSubject.setOrientation(OrientationHelper.VERTICAL);
        layoutManagerSubject.setSmoothScrollbarEnabled(true);
        layoutManagerSubject.setAutoMeasureEnabled(true);
        //设置布局管理器
        recycleAllGoods.setLayoutManager(layoutManagerSubject);
        recycleAllGoods.setHasFixedSize(true);
        recycleAllGoods.setNestedScrollingEnabled(false);
        recycleAllGoods.setLayoutManager(layoutManagerSubject);
        marketGoodsAdapter = new MarketGoodsTagAdapter(mContext,goodsListBeans,this);
        recycleAllGoods.setAdapter(marketGoodsAdapter);
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        bean = (MenuItemBean)getArguments().get("bean");
        areaId = Constant.getMyLocation().adCode ;
        goodsType = getArguments().getString("goods_type") ;
        getPresenter().getGoodList(areaId,bean.getId(),goodsType);
    }

    @Override
    public void getGoodsListSuccess(MarketGoodsListBean response) {
        goodsListBeans.clear();
        PLog.e(response.size() + "");
        if(response != null && response.size() > 0){
            goodsListBeans.addAll(response);
        }
        marketGoodsAdapter.notifyDataSetChanged();
    }

    public void setClick(OnAddToShopInFragmentClick click) {
        this.click = click;
    }

    @Override
    public void onAddToShopCar(View view,int adapterPosition, int position) {
        getGoodDetailsMap.clear();
        getGoodDetailsMap.put("goods_type",goodsListBeans.get(adapterPosition).getGoods_list().get(position).getGoods_type());
        getGoodDetailsMap.put("goods_id",goodsListBeans.get(adapterPosition).getGoods_list().get(position).getGoods_id());
        getGoodDetailsMap.put("warehouse_id",goodsListBeans.get(adapterPosition).getGoods_list().get(position).getWarehouse_id());
        getGoodDetailsMap.put("inlet","common");
        getPresenter().getGoodsDetail(view,getGoodDetailsMap);
    }

    public interface OnAddToShopInFragmentClick{
        void onAddToShopInFragmentClick(View view);
    }

    /**
     * 获取商品详情成功
     * @param baseBean
     * @param addCar
     */
    @Override
    public void getGoodDetailSucccess(GoodDetailBean baseBean, View addCar) {
        if(baseBean.getSku_group().size() > 1){
            CheckSkuToAddShopCar(baseBean,addCar);
        }else{
            PLog.e("getGoodDetailSucccess");
            switch (baseBean.getDetails().getGoods_type()){
                case Constant.TYPE_GOODS_RECOMMEND :    //推荐商品
                    Map<String, Object> params = new HashMap<>();
                    params.put("goods_id", baseBean.getDetails().getGoods_id());
                    params.put("sku_id", baseBean.getSku_group().get(0).getSku_id());
                    params.put("shop_num", baseBean.getSku_group().get(0).getMinimum());
                    params.put("order_type", "recom");
                    params.put("change_type", "add");
                    getPresenter().recommendAddCar(params,addCar);
                    break;

                case Constant.TYPE_GOODS_MARKET :   //超市商品
                    Map<String, Object> marketparams = new HashMap<>();
                    Map<String, Object> shopCartMap = new HashMap<>();
                    shopCartMap.put("product_id", baseBean.getSku_group().get(0).getProduct_id());
                    shopCartMap.put("goods_id", baseBean.getDetails().getGoods_id());
                    shopCartMap.put("shop_num", baseBean.getSku_group().get(0).getMinimum());
                    shopCartMap.put("sku_id", baseBean.getSku_group().get(0).getSku_id());
                    String shopCart = new Gson().toJson(shopCartMap);
                    marketparams.put("order_type", "day");
                    marketparams.put("warehouse_id", baseBean.getDetails().getWarehouse_id());
                    marketparams.put("change_type", "add");
                    marketparams.put("shop_cart", shopCart);
                    getPresenter().marketAddCar(marketparams,addCar);
                    break;
            }
        }
    }

    /**
     * 选择规格并加入购物车
     * @param baseBean
     * @param addCar
     */
    private void CheckSkuToAddShopCar(GoodDetailBean baseBean, View addCar) {
        CommonDialog.create(getChildFragmentManager())
                .setMyCustomStyle(R.style.CommonDialogB_T)
                .setCustomView(new BuyGoodChooseSkuDialog(mContext).setGoodsData(baseBean,R.mipmap.img_default))
                .setViewListener(new CommonDialog.ViewListener() {
                    @Override
                    public void bindView(View v, CommonDialog dialog) {
                        BuyGoodChooseSkuDialog dialogView = (BuyGoodChooseSkuDialog)v;
                        dialogView.setDialogOnClickListener(new BuyGoodChooseSkuDialog
                                .DialogOnClickListener() {
                            @Override
                            public void closeOnclick() {
                                dialog.dismiss();
                            }

                            @Override
                            public void confirmOnClick(String goods_type, Map<String, Object> params) {
                                dialog.dismiss();
                                switch (goods_type){
                                    case Constant.TYPE_GOODS_RECOMMEND :    //推荐商品
                                        getPresenter().recommendAddCar(params,addCar);
                                        break;

                                    case Constant.TYPE_GOODS_MARKET :   //超市商品
                                        getPresenter().marketAddCar(params,addCar);
                                        break;
                                }
                            }
                        });
                    }
                })
                .setDimAmount(0.2f)
                .setGravity(Gravity.BOTTOM)
                .setCancelOutside(false)
                .setTag("BuyGoodChooseSkuDialog")
                .show();
    }

    @Override
    public void recommendAddCarSuccess(View addCar) {
        click.onAddToShopInFragmentClick(addCar);
    }

    @Override
    public void marketAddCarSuccess(View addCar) {
        click.onAddToShopInFragmentClick(addCar);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(TagChangeEvent event){
        PLog.e("event   " + event.getIndex() + "," + marketGoodsAdapter.getItemCount());
        layoutManagerSubject.scrollToPosition(event.getIndex());
    }
}
