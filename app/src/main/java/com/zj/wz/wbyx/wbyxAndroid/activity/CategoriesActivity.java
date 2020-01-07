package com.zj.wz.wbyx.wbyxAndroid.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.BuyGoodChooseSkuDialog;
import com.zj.wz.wbyx.baseandroid.view.FlowLayout;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.baseandroid.view.dialog.CommonDialog;
import com.zj.wz.wbyx.wbyxAndroid.adapter.CategoriesGoodsAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.CategoriesLeftMenuAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodSortBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodSortListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemListBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.CategoriesPresenter;
import com.zj.wz.wbyx.wbyxAndroid.utils.GoodsAnimUtil;
import com.zj.wz.wbyx.wbyxAndroid.view.CategoriesView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: CategoriesActivity
 * Author: 曹伟
 * Date: 2019/11/7 20:39
 * Description:商品分类
 */

public class CategoriesActivity extends BaseMvpActivity<CategoriesView, CategoriesPresenter>
        implements CategoriesView, CategoriesLeftMenuAdapter.OnItemClick, CategoriesGoodsAdapter.addToShopCarClick {
    @BindView(R.id.tv_tltle)
    TextView tvTltle;
    @BindView(R.id.recycle_leftCategories)
    RecyclerView recycleLeftCategories ;        //左侧菜单
    @BindView(R.id.magic_kindOfMenu)
    MagicIndicator magicKindOfMenu ;        //商品标签指示器
    @BindView(R.id.recycle_goods)
    RecyclerView recycleGoods ;     //商品展示
    @BindView(R.id.img_toBottom)
    ImageView imgToBottom ; //向下箭头，打开蒙层
    @BindView(R.id.relative_magicIndicator)
    RelativeLayout relativeMagicIndicator ;     //蒙层
    @BindView(R.id.flow_categories)
    FlowLayout flowCategories ; //分类容器
    @BindView(R.id.img_goShop)
    ImageView imgGoShop ;   //购物车

    private CategoriesLeftMenuAdapter categoriesLeftMenuAdapter ;   //左侧分类适配器
    private CategoriesGoodsAdapter categoriesGoodsAdapter ; //分类商品适配器

    private int hot = 1 ;       //表示没有热销的分类列表
    private String type = "" ;  //类别
    private String area_id = "" ;   //区域id
    private String cateId = "" ;    //选中菜单id
    private List<MenuItemBean> leftMenus = new ArrayList<>();   //左侧分类
    private List<GoodSortBean.GoodsListBean> goodsListBeans = new ArrayList<>() ;   //商品集合
    private List<String> goodsTag = new ArrayList<>() ; //商品标签集合
    private SuspensionDecoration mDecoration;
    private List<TextView> packageInfoViews = new ArrayList<>();

    private String tag = "" ;
    private LinearLayoutManager layoutManagerGoods ;

    private Map<String,String> getGoodDetailsMap = new HashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_categories;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_FFFFFF));
        tvTltle.setText(mContext.getResources().getString(R.string.categories_goods));
        /**
         * 左侧菜单栏
         */
        categoriesLeftMenuAdapter
                = new CategoriesLeftMenuAdapter(mContext,leftMenus,cateId,this);
        LinearLayoutManager layoutManagerLeft = new LinearLayoutManager(mContext);
        //设置为垂直布局，这也是默认的
        layoutManagerLeft.setOrientation(OrientationHelper.VERTICAL);
        layoutManagerLeft.setSmoothScrollbarEnabled(true);
        layoutManagerLeft.setAutoMeasureEnabled(true);
        recycleLeftCategories.setHasFixedSize(true);
        recycleLeftCategories.setNestedScrollingEnabled(false);
        //设置布局管理器
        recycleLeftCategories.setLayoutManager(layoutManagerLeft);
        //设置Adapter
        recycleLeftCategories.setAdapter(categoriesLeftMenuAdapter);

        /**
         * 商品展示
         */
        categoriesGoodsAdapter = new CategoriesGoodsAdapter(mContext,goodsListBeans,this);
        layoutManagerGoods = new LinearLayoutManager(mContext);
        //设置为垂直布局，这也是默认的
        layoutManagerGoods.setOrientation(OrientationHelper.VERTICAL);
        layoutManagerGoods.setSmoothScrollbarEnabled(true);
        layoutManagerGoods.setAutoMeasureEnabled(true);
        recycleGoods.setHasFixedSize(true);
        recycleGoods.setNestedScrollingEnabled(false);
        //设置布局管理器
        recycleGoods.setLayoutManager(layoutManagerGoods);
        mDecoration = new SuspensionDecoration(this, goodsListBeans).setHeaderViewCount(goodsTag.size());
        mDecoration.setColorTitleBg(mContext.getResources().getColor(R.color.c_FFFFFF));
        mDecoration.setTitleFontSize(36);
        mDecoration.setColorTitleFont(mContext.getResources().getColor(R.color.c_8F949C));
        mDecoration.setmTitleHeight((int) mContext.getResources().getDimension(R.dimen.H60));
        recycleGoods.addItemDecoration(mDecoration);
        //设置Adapter
        recycleGoods.setAdapter(categoriesGoodsAdapter);
        recycleGoods.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int position = getTagByIndex(goodsListBeans
                        .get(layoutManagerGoods.findFirstVisibleItemPosition()).getTag());
                if(position != -1)
                magicKindOfMenu.onPageSelected(position);
                magicKindOfMenu.getNavigator().notifyDataSetChanged();
            }
        });

        /**
         * 商品标签指示器
         */
        initMagicIndicator();
    }

    /**
     * 根据页面显示第一个显示的完全的tag获取tag的position
     * @return
     * @param tag
     */
    private int getTagByIndex(String tag) {
      if(this.tag.equals(tag)){     //提高效率，当前tag已经相同，不在做集合遍历
          return -1 ;
      }
      for (int i = 0 ; i < goodsTag.size() ; i++){
          if(tag.equals(goodsTag.get(i))){
              return i;
          }
      }
      return -1 ;
    }

    /**
     * 初始化指示器
     */
    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(false);
        commonNavigator.setEnablePivotScroll(true);
        commonNavigator.setFollowTouch(false);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return goodsTag == null ? 0 : goodsTag.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(goodsTag.get(index));
                simplePagerTitleView
                        .setNormalColor(mContext.getResources().getColor(R.color.c_8F949C));
                simplePagerTitleView
                        .setSelectedColor(mContext.getResources().getColor(R.color.c_FF3E3E));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tag = goodsTag.get(index);
                        magicKindOfMenu.getNavigator().onPageSelected(index);
                        magicKindOfMenu.getNavigator().notifyDataSetChanged();
                        layoutManagerGoods.scrollToPositionWithOffset(getIndexByTag(),0);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
                indicator.setFillColor(mContext.getResources().getColor(R.color.c_FFFFEAEA));
                return indicator;
            }
        });
        magicKindOfMenu.setNavigator(commonNavigator);
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        area_id = Constant.getMyLocation().adCode ;
        getPresenter().getMenu(hot,type,area_id);
    }

    @OnClick({R.id.tv_tltle,R.id.img_service,R.id.tv_search,R.id.img_toBottom,R.id.img_toTop})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_tltle :    //标题
                    finish();
                break;

            case R.id.img_service : //客服
                Intent serviceIntent = new Intent(mContext, CustomerActivity.class);
                startActivity(serviceIntent);
                break;

            case R.id.tv_search :   //搜索

                break;

            case R.id.img_toBottom :    //打开蒙层
                packageInfoViews.clear();
                flowCategories.removeAllViews();
                for (int j = 0 ; j < goodsTag.size(); j++){
                    View tagView = LayoutInflater.from(mContext).inflate(R.layout.add_categories_item_view,null);
                    TextView tv_group = tagView.findViewById(R.id.tv_tagCategories);
                    tv_group.setText(goodsTag.get(j));
                    tv_group.setSelected(goodsTag.get(j).equals(tag));
                    PLog.e("tag ==== " + goodsTag.get(j).equals(tag));
                    packageInfoViews.add(tv_group);
                    flowCategories.addView(tagView);
                    int finalJ = j;
                    tv_group.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            refreshFlow();
                            tv_group.setSelected(true);
                            tag = goodsTag.get(finalJ) ;
                            magicKindOfMenu.getNavigator().onPageSelected(finalJ);
                            magicKindOfMenu.getNavigator().notifyDataSetChanged();
                            layoutManagerGoods.scrollToPositionWithOffset(getIndexByTag(),0);
                        }

                    });
                }
                relativeMagicIndicator.setVisibility(View.VISIBLE);
                break;

            case R.id.img_toTop :       //关闭蒙层
                relativeMagicIndicator.setVisibility(View.GONE);
                break;
        }
    }

    /**
     *根据tag获取recycle的item
     * @return
     */
    private int getIndexByTag() {
        for (int i = 0; i < goodsListBeans.size(); i++) {
            if (tag.equals(goodsListBeans.get(i).getTag())) {
                return recycleGoods.getItemDecorationCount() + i -1;
            }
        }
        return -1 ;
    }

    /**
     * 左侧菜单栏获取成功
     * @param response
     */
    @Override
    public void getMenuLeftSuccess(MenuItemListBean response) {
        leftMenus.addAll(response);
        if(null != getIntent().getStringExtra("cate_id")){
            cateId = getIntent().getStringExtra("cate_id") ;
        }else{
            cateId = response.get(0).getId() ;
        }
        categoriesLeftMenuAdapter.setCateId(cateId);
        getPresenter().getGoodsSort(cateId,area_id);
    }

    /**
     * 获取菜单项下商品列表成功
     * @param response
     */
    @Override
    public void getGoodSortSuccess(GoodSortListBean response) {
        goodsListBeans.clear();
        goodsTag.clear();
        for (GoodSortBean bean : response) {
            goodsTag.add(bean.getName());
            for (GoodSortBean.GoodsListBean goodsListBean:bean.getGoods_list()){
                goodsListBean.setTag(bean.getName());
            }
            goodsListBeans.addAll(bean.getGoods_list());
        }
        tag = goodsTag.size() > 0 ? goodsTag.get(0) : "" ;
        imgToBottom.setVisibility(goodsTag.size() > 3 ? View.VISIBLE : View.GONE);
        magicKindOfMenu.getNavigator().notifyDataSetChanged();
        categoriesGoodsAdapter.notifyDataSetChanged();
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

    @Override
    public void recommendAddCarSuccess(View addCar) {
        GoodsAnimUtil.setAnim(mContext, addCar, imgGoShop);
    }

    @Override
    public void marketAddCarSuccess(View addCar) {
        GoodsAnimUtil.setAnim(mContext, addCar, imgGoShop);
    }

    /**
     * 选择规格并加入购物车
     * @param baseBean
     * @param addCar
     */
    private void CheckSkuToAddShopCar(GoodDetailBean baseBean, View addCar) {
        CommonDialog.create(mContext.getSupportFragmentManager())
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
     *将点击的view之外的控件背景修改
     */
    private void refreshFlow() {
        for(int i=0;i<packageInfoViews.size();i++){
            packageInfoViews.get(i).setSelected(false);
        }
    }

    /**
     * 左侧菜单栏点击监听
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        cateId = leftMenus.get(position).getId();
        categoriesLeftMenuAdapter.setCateId(cateId);
        getPresenter().getGoodsSort(cateId,area_id);
    }

    /**
     * 点击加入购物车
     * @param view
     * @param position
     */
    @Override
    public void onAddToShopCar(View view, int position) {
        getGoodDetailsMap.clear();
        getGoodDetailsMap.put("goods_type",goodsListBeans.get(position).getGoods_type());
        getGoodDetailsMap.put("goods_id",goodsListBeans.get(position).getGoods_id());
        getGoodDetailsMap.put("warehouse_id",goodsListBeans.get(position).getWarehouse_id());
        getGoodDetailsMap.put("inlet","common");
        getPresenter().getGoodsDetail(view,getGoodDetailsMap);
    }
}
