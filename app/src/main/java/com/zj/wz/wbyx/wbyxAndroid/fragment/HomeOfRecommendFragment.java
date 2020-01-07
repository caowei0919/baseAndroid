package com.zj.wz.wbyx.wbyxAndroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpFragment;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.BuyGoodChooseSkuDialog;
import com.zj.wz.wbyx.baseandroid.view.MyGridLayoutManager;
import com.zj.wz.wbyx.baseandroid.view.dialog.CommonDialog;
import com.zj.wz.wbyx.wbyxAndroid.adapter.HomeGoodsAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllListBean;
import com.zj.wz.wbyx.wbyxAndroid.event.AddToShopCarEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.HomeLoadMoreEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.HomeRefreshEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.LocationResultEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.HomeOfRecommendPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.HomeOfRecommendView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * FileName: HomeOfRecommendFragment
 * Author: 曹伟
 * Date: 2019/11/8 10:29
 * Description:
 */

public class HomeOfRecommendFragment extends BaseMvpFragment<HomeOfRecommendView
        , HomeOfRecommendPresenter> implements HomeOfRecommendView, HomeGoodsAdapter.OnAddToShopCarClick {
    @BindView(R.id.smart_recommend)
    SmartRefreshLayout smartRecommend ;
    @BindView(R.id.recycle_allGoods)
    RecyclerView recycleAllGoods ;      //商品列表

    private HomeGoodsAdapter adapter ;  //商品展示适配器
    private List<HomeGoodsOfAllBean> allGoods = new ArrayList<>();

    private String areaId ; //区域编码
    private String goodsType ;  //商品类型
    private int page = 1 ;
    private int pageNum = 10 ;  //每页展示的数据

    private Map<String,String> getGoodDetailsMap = new HashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_of_recommend;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupView(View rootView) {
        adapter = new HomeGoodsAdapter(mContext,allGoods,this);
        MyGridLayoutManager gridLayoutManager = new MyGridLayoutManager(mContext, 2
                , GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setScrollEnabled(false);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        recycleAllGoods.setHasFixedSize(true);
        recycleAllGoods.setNestedScrollingEnabled(false);
        recycleAllGoods.setLayoutManager(gridLayoutManager);
        recycleAllGoods.setAdapter(adapter);

        smartRecommend.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page ++ ;
                getPresenter().getHomeGoods(Constant.getMyLocation().adCode,goodsType,page,pageNum);
            }
        });
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        goodsType = Constant.HOME_GOODS_RECOMMEND ;
        areaId = Constant.getMyLocation().adCode ;
        getPresenter().getHomeGoods(Constant.getMyLocation().adCode,goodsType,page,pageNum);
    }

    /**
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HomeLoadMoreEvent event){
        page ++ ;
        areaId = Constant.getMyLocation().adCode ;
        getPresenter().getHomeGoods(Constant.getMyLocation().adCode,goodsType,page,pageNum);
    }

    /**
     * 定位信息回调
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LocationResultEvent event){
        areaId = Constant.getMyLocation().adCode ;
    }

    /**
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HomeRefreshEvent event){
        page = 1 ;
        areaId = Constant.getMyLocation().adCode ;
        getPresenter().getHomeGoods(Constant.getMyLocation().adCode,goodsType,page,pageNum);
    }

    /**
     * 获取首页底部全部商品成功
     * @param response
     */
    @Override
    public void getOnGoodsSuccess(HomeGoodsOfAllListBean response) {
        if(page == 1){
            allGoods.clear();
            allGoods.addAll(response);
        }else{
            allGoods.addAll(response);
        }
        adapter.notifyDataSetChanged();
    }

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

    /**
     * 加入购物车
     * @param addCar
     * @param position
     */
    @Override
    public void addCarClick(View addCar, int position) {
        getGoodDetailsMap.clear();
        getGoodDetailsMap.put("goods_type",allGoods.get(position).getGoods_type());
        getGoodDetailsMap.put("goods_id",allGoods.get(position).getGoods_id());
        getGoodDetailsMap.put("warehouse_id",allGoods.get(position).getWarehouse_id());
        getGoodDetailsMap.put("inlet","common");
        getPresenter().getGoodsDetail(addCar,getGoodDetailsMap);
    }

    @Override
    public void recommendAddCarSuccess(View addCar) {
        EventBus.getDefault().post(new AddToShopCarEvent(true,addCar));
    }

    @Override
    public void marketAddCarSuccess(View addCar) {
        EventBus.getDefault().post(new AddToShopCarEvent(true,addCar));
    }
}
