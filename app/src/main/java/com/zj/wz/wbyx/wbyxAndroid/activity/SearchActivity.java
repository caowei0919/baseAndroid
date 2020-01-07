package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.enlogy.statusview.StatusRelativeLayout;
import com.google.gson.Gson;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.BuyGoodChooseSkuDialog;
import com.zj.wz.wbyx.baseandroid.view.FlowLayout;
import com.zj.wz.wbyx.baseandroid.view.GoodSelectView;
import com.zj.wz.wbyx.baseandroid.view.MyGridLayoutManager;
import com.zj.wz.wbyx.baseandroid.view.dialog.CommonDialog;
import com.zj.wz.wbyx.wbyxAndroid.adapter.SearchGoodsAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SearchGoodsBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SearchGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SearchHistoryListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.WareHouseBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.SearchPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * FileName: SearchActivity
 * Author: 曹伟
 * Date: 2019/11/13 10:29
 * Description:搜索页面
 */

public class SearchActivity extends BaseMvpActivity<SearchView, SearchPresenter>
        implements SearchView, GoodSelectView.OnSelectItemClickListener
        , SearchGoodsAdapter.AddCarListener {
    @BindView(R.id.status_search)
    StatusRelativeLayout statusSearch ;
    @BindView(R.id.ed_search)
    EditText edSearch ; //搜索
    @BindView(R.id.goodSelectView)
    GoodSelectView goodSelectView ;
    @BindView(R.id.recycle_subjects)
    RecyclerView recycleSubjects ;

    private FlowLayout flowSearchHistory ;
    private int page = 1 ;
    private int per_page = 10 ;
    private String content = "" ;   //搜索内容
    private String sort_type = "1" ;
    private String warehouse_id = "0" ;
    private String direstion = "" ;
    private List<SearchGoodsBean> searchGoodsBeans = new ArrayList<>();
    private SearchGoodsAdapter searchGoodsAdapter ;
    private Map<String,String> getGoodDetailsMap = new HashMap<>();

    //默认不传 vip_price价格 sale销量 up_time新品
    private static String SORT_TYPE_DEFAULT = "1";
    private static String SORT_TYPE_PRICE = "2";
    private static String SORT_TYPE_SALES = "3";
    private static String SORT_TYPE_NEW = "4";

    //asc 或 desc
    private static String DIRESTION_ASC = "asc";   //升序
    private static String DIRESTION_DESC = "desc"; //降序

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 当按了搜索之后关闭软键盘
                    AndroidUtils.hideKeyboard(edSearch);
                    content = edSearch.getText().toString().trim();
                    sort_type = SORT_TYPE_DEFAULT ;
                    getPresenter().searchGoods(content,sort_type,warehouse_id,direstion,page,per_page);
                    return true;
                }
                return false;
            }
        });
        statusSearch.showExtendContent();
        goodSelectView.setOnSelectItemClickListener(this);
        statusSearch.findViewById(R.id.img_deleteHistory)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().delSearchHistory();
            }
        });

        searchGoodsAdapter = new SearchGoodsAdapter(mContext,searchGoodsBeans,warehouse_id,this);
        MyGridLayoutManager gridLayoutManager = new MyGridLayoutManager(mContext, 2
                , GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setScrollEnabled(false);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        recycleSubjects.setHasFixedSize(true);
        recycleSubjects.setNestedScrollingEnabled(false);
        recycleSubjects.setLayoutManager(gridLayoutManager);
        recycleSubjects.setAdapter(searchGoodsAdapter);
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().getSearchHistory();
        getPresenter().getDayLocation(Constant.getMyLocation().longitude
                ,Constant.getMyLocation().latitude,Constant.getMyLocation().adCode);
    }

    @OnClick({R.id.relative_back,R.id.tv_search})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.relative_back :   //返回
                    finish();
                break;

            case R.id.tv_search :   //搜索
                if(TextUtils.isEmpty(edSearch.getText().toString().trim())){
                    ToastUtils.showLongToast(mContext.getResources()
                            .getString(R.string.please_to_write_search_text));
                    return;
                }
                content = edSearch.getText().toString().trim();
                getPresenter().searchGoods(content,sort_type,warehouse_id,direstion,page,per_page);
                break;
        }
    }

    /**
     * 获取搜索历史成功
     * @param response
     */
    @Override
    public void getSearchHistorySuccess(SearchHistoryListBean response) {
        flowSearchHistory = statusSearch.findViewById(R.id.flow_searchHistory);
        flowSearchHistory.removeAllViews();
        for (int j = 0 ; j < response.size(); j++){
            View view = LayoutInflater.from(mContext).inflate(R.layout.add_dormitory_item_view,null);
            TextView tv_group = view.findViewById(R.id.dorm_num_tv);
            tv_group.setText(response.get(j).getKeywords());
            flowSearchHistory.addView(view);
            tv_group.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edSearch.setText(tv_group.getText());
                }
            });
        }
    }

    /**
     * 清除搜索历史成功
     */
    @Override
    public void deleteHistorySuccess() {
        flowSearchHistory = statusSearch.findViewById(R.id.flow_searchHistory);
        flowSearchHistory.removeAllViews(); flowSearchHistory = statusSearch.findViewById(R.id.flow_searchHistory);
        flowSearchHistory.removeAllViews();
    }

    @Override
    public void getSearchGoodsSuccess(SearchGoodsListBean response) {
        if(page == 1){
            searchGoodsBeans.clear();
            searchGoodsBeans.addAll(response);
        }else {
            searchGoodsBeans.addAll(response);
        }
        if(searchGoodsBeans.size() == 0){
            statusSearch.showEmptyContent();
        }else{
            statusSearch.showContent();
            searchGoodsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDayLocationSuccess(WareHouseBean wareHouseBean) {
        warehouse_id = wareHouseBean.getWarehouse_id() ;
    }

    @Override
    public void getGoodDetailSucccess(GoodDetailBean baseBean) {
        if(baseBean.getSku_group().size() > 1){
            CheckSkuToAddShopCar(baseBean);
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
                    getPresenter().recommendAddCar(params);
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
                    getPresenter().marketAddCar(marketparams);
                    break;
            }
        }
    }

    /**
     * 选择规格并加入购物车
     * @param baseBean
     */
    private void CheckSkuToAddShopCar(GoodDetailBean baseBean) {
        CommonDialog.create(getSupportFragmentManager())
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
                                        getPresenter().recommendAddCar(params);
                                        break;

                                    case Constant.TYPE_GOODS_MARKET :   //超市商品
                                        getPresenter().marketAddCar(params);
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
    public void OnSelectItemClick(int position) {
        page = 1 ;
        content = edSearch.getText().toString().trim() ;
        switch (position) {
            case 0:
                sort_type = SORT_TYPE_DEFAULT ;
                direstion =  DIRESTION_DESC ;//默认
                getPresenter().searchGoods(content,sort_type,warehouse_id,direstion,page,per_page);
                break;
            case 1:
                sort_type =  SORT_TYPE_PRICE ;
                if (goodSelectView.ismPrice()) {
                    direstion = DIRESTION_DESC ;
                } else {
                    direstion = DIRESTION_ASC ;
                }
                getPresenter().searchGoods(content,sort_type,warehouse_id,direstion,page,per_page);
                break;
            case 2:
                sort_type =  SORT_TYPE_SALES;
                if (goodSelectView.ismNum()) {
                    direstion = DIRESTION_DESC ;
                } else {
                    direstion = DIRESTION_ASC ;
                }
                getPresenter().searchGoods(content,sort_type,warehouse_id,direstion,page,per_page);
                break;
            case 3:
                sort_type= SORT_TYPE_NEW ;
                SORT_TYPE_DEFAULT = SORT_TYPE_NEW;
                if (goodSelectView.ismNew()) {
                    direstion =  DIRESTION_DESC ;
                } else {
                    direstion = DIRESTION_ASC ;
                }
                getPresenter().searchGoods(content,sort_type,warehouse_id,direstion,page,per_page);
                break;
        }
    }

    /**
     * 加入购物车
     * @param position
     */
    @Override
    public void onAddCarClick(int position) {
        getGoodDetailsMap.clear();
        getGoodDetailsMap.put("goods_type",searchGoodsBeans.get(position).getGoods_type());
        getGoodDetailsMap.put("goods_id",searchGoodsBeans.get(position).getGoods_id());
        getGoodDetailsMap.put("warehouse_id",warehouse_id);
        getGoodDetailsMap.put("inlet","common");
        getPresenter().getGoodsDetail(getGoodDetailsMap);
    }
}
