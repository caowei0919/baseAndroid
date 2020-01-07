package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.BuyGoodChooseSkuDialog;
import com.zj.wz.wbyx.baseandroid.view.GoodSelectView;
import com.zj.wz.wbyx.baseandroid.view.MyGridLayoutManager;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.baseandroid.view.dialog.CommonDialog;
import com.zj.wz.wbyx.wbyxAndroid.adapter.SubjectGoodsAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SubjectBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.SubjectsPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.SubjectsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: SubjectsActivity
 * Author: 曹伟
 * Date: 2019/11/9 10:15
 * Description:专题活动
 */

public class SubjectsActivity extends BaseMvpActivity<SubjectsView, SubjectsPresenter>
        implements SubjectsView , GoodSelectView.OnSelectItemClickListener
        , SubjectGoodsAdapter.onAddToShopCarClick {
    @BindView(R.id.tv_title)
    TextView tvTitle ;      //标题
    @BindView(R.id.smart_subject)
    SmartRefreshLayout smartSubject ;
    @BindView(R.id.img_subject)
    ImageView imgSubject ;  //专题详情图片
    @BindView(R.id.goodSelectView)
    GoodSelectView goodSelectView ; //筛选
    @BindView(R.id.recycle_subjects)
    RecyclerView recycleSubjects ;  //商品

    private SubjectGoodsAdapter subjectGoodsAdapter ;

    private String activityId = "" ;    //专题id
    //默认不传 vip_price价格 sale销量 up_time新品
    private static String SORT_TYPE_DEFAULT = "";
    private static String SORT_TYPE_PRICE = "vip_price";
    private static String SORT_TYPE_SALES = "sale";
    private static String SORT_TYPE_NEW = "up_time";
    //asc 或 desc
    private static String DIRESTION_ASC = "asc";   //升序
    private static String DIRESTION_DESC = "desc"; //降序
    private int page = 1 ;  //页码
    private int pageSize = 10 ;
    private String rule = "" ;  //排序规则
    private String field = "" ; //商品类型

    private List<SubjectBean.GoodListBean> goodListBeanList = new ArrayList<>();        //商品
    private Map<String,String> getGoodDetailsMap = new HashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subjects;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        tvTitle.setText(mContext.getResources().getString(R.string.subject_detail));
        StatusBarUtil.setStatusBarColor(mContext,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        goodSelectView.setOnSelectItemClickListener(this);
        subjectGoodsAdapter = new SubjectGoodsAdapter(mContext,goodListBeanList,this);
        MyGridLayoutManager gridLayoutManager = new MyGridLayoutManager(mContext, 2
                , GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setScrollEnabled(false);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        recycleSubjects.setHasFixedSize(true);
        recycleSubjects.setNestedScrollingEnabled(false);
        recycleSubjects.setLayoutManager(gridLayoutManager);
        recycleSubjects.setAdapter(subjectGoodsAdapter);

        smartSubject.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page ++ ;
                getPresenter().getSpecialList(activityId,rule,page,pageSize,field);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1 ;
                getPresenter().getSpecialList(activityId,rule,page,pageSize,field);
            }
        });
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        if(null != getIntent().getStringExtra("activity_id")){
            activityId = getIntent().getStringExtra("activity_id");
        }
        rule = DIRESTION_DESC ;
        getPresenter().getSpecialList(activityId,rule,page,pageSize,field);
    }

    @OnClick({R.id.linear_back})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                    finish();
                break;
        }
    }

    /**
     * 筛选选择
     * @param position 0：默认  1：价格 2：销量  3：新品
     */
    @Override
    public void OnSelectItemClick(int position) {
        page = 1 ;
        switch (position) {
            case 0:
                field = SORT_TYPE_DEFAULT ;
                rule =  DIRESTION_DESC ;//默认
                getPresenter().getSpecialList(activityId,rule,page,pageSize,field);
                break;
            case 1:
                field =  SORT_TYPE_PRICE ;
                if (goodSelectView.ismPrice()) {
                    rule = DIRESTION_DESC ;
                } else {
                    rule = DIRESTION_ASC ;
                }
                getPresenter().getSpecialList(activityId,rule,page,pageSize,field);
                break;
            case 2:
                field =  SORT_TYPE_SALES;
                if (goodSelectView.ismNum()) {
                    rule = DIRESTION_DESC ;
                } else {
                    rule = DIRESTION_ASC ;
                }
                getPresenter().getSpecialList(activityId,rule,page,pageSize,field);
                break;
            case 3:
                field= SORT_TYPE_NEW ;
                SORT_TYPE_DEFAULT = SORT_TYPE_NEW;
                if (goodSelectView.ismNew()) {
                    rule =  DIRESTION_DESC ;
                } else {
                    rule = DIRESTION_ASC ;
                }
                getPresenter().getSpecialList(activityId,rule,page,pageSize,field);
                break;
        }
    }

    @Override
    public void getSpecialListSuccess(SubjectBean response) {
        smartSubject.finishRefresh();
        smartSubject.finishLoadMore();
        Glide.with(mContext)
                .load(response.getPoster())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(imgSubject);

        if(page == 1){  //刷新，或者重新选择标签
            goodListBeanList.clear();
            goodListBeanList.addAll(response.getGood_list());
            subjectGoodsAdapter.notifyDataSetChanged();
        }else{
            goodListBeanList.addAll(response.getGood_list());
            subjectGoodsAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取商品详情成功
     * @param baseBean
     */
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

    /**
     * 商品加入购物车
     * @param position
     */
    @Override
    public void addToShopCar(int position) {
        getGoodDetailsMap.clear();
        getGoodDetailsMap.put("goods_type",goodListBeanList.get(position).getGoods_type());
        getGoodDetailsMap.put("goods_id",goodListBeanList.get(position).getGoods_id());
        getGoodDetailsMap.put("warehouse_id",goodListBeanList.get(position).getWarehouse_id());
        getGoodDetailsMap.put("inlet","common");
        getPresenter().getGoodsDetail(getGoodDetailsMap);
    }
}
