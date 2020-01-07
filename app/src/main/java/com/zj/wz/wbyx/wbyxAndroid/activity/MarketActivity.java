package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.baseandroid.view.WrapContentHeightViewPager;
import com.zj.wz.wbyx.wbyxAndroid.adapter.BannerMarketViewHolder;
import com.zj.wz.wbyx.wbyxAndroid.adapter.MarketIndicatorAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.MarketIndicatorTopAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.MarketMenuPageAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.AdvListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MarketGoodsBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MarketGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemListBean;
import com.zj.wz.wbyx.wbyxAndroid.event.TagChangeEvent;
import com.zj.wz.wbyx.wbyxAndroid.fragment.MarketFragment;
import com.zj.wz.wbyx.wbyxAndroid.presenter.MarketPresenter;
import com.zj.wz.wbyx.wbyxAndroid.utils.GoodsAnimUtil;
import com.zj.wz.wbyx.wbyxAndroid.view.MarketView;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import static com.zj.wz.wbyx.baseandroid.config.Constant.TYPE_MARKET;

/**
 * FileName: MarketActivity
 * Author: 曹伟
 * Date: 2019/11/9 10:07
 * Description:窝边超市
 */

public class MarketActivity extends BaseMvpActivity<MarketView, MarketPresenter>
        implements MarketView, MarketIndicatorAdapter.onIndicatorClick
        , MarketIndicatorTopAdapter.onIndicatorTopClick , MarketFragment.OnAddToShopInFragmentClick{
    @BindView(R.id.tv_tltle)
    TextView tvTltle ;  //标题
    @BindView(R.id.layout_title)
    RelativeLayout layoutTitle ;    //标题栏
    @BindView(R.id.tv_search)
    TextView tvSearch ; //搜索
    @BindView(R.id.img_goShop)
    ImageView imgGoShop ; //购物车
    @BindView(R.id.img_service)
    ImageView imgService ;
    @BindView(R.id.tv_background)
    TextView tvBackground ;     //沉浸背景
    @BindView(R.id.banner_market_bg)
    MZBannerView bannerMarketBg ;       //广告图
    @BindView(R.id.nested_market)
    NestedScrollView nestedMarket ;
    @BindView(R.id.magic_indicatorPicture)
    MagicIndicator magicIndicatorPicture ;  //底部指示器
    @BindView(R.id.magic_indicatorTop)
    MagicIndicator magicIndicatorTop ;  //置顶指示器
    @BindView(R.id.magic_goodIndicatorTop)
    MagicIndicator magicGoodIndicatorTop ;  //商品标签顶部
    @BindView(R.id.magic_goodIndicator)
    MagicIndicator magicGoodIndicator ; //商品标签指示器
    @BindView(R.id.tv_lineGray)
    TextView tvLineGray ;
    @BindView(R.id.wrap_goods)
    WrapContentHeightViewPager wrapGoods ;

    private static final int START_ALPHA = 0;
    private static final int END_ALPHA = 255;
    private int bannerBottom = 0 ;    //广告图底部
    private int magicIndicatorTopOfBottom = 0 ; //顶部指示器底部
    private int magicIndicatorOfBottom = 0 ;    //底部指示器底部
    private int magicGoodIndicatorTopOfBottom = 0 ; //商品标签顶部指示器底部
    private int magicGoodIndicatorOfBottom = 0 ;    //商品标签底部指示器底部
    private int backgroundBottom = 0 ;  //沉浸背景底部
    private int alphe = 0;
    private String type = "" ;  //类别
    private String area_id = "" ;   //区域id
    private String cart_id = "" ;   //选中菜单id
    private List<MenuItemBean> menuItemBeans = new ArrayList<>();
    private List<MarketGoodsBean> goodsListBeans = new ArrayList<>();
    private MarketIndicatorAdapter marketIndicatorAdapter ;
    private MarketIndicatorTopAdapter marketIndicatorTopAdapter ;
    private MarketMenuPageAdapter marketMenuPageAdapter ;
    private List<MarketFragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_market;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
        magicGoodIndicator.setBackgroundColor(Color.argb(0,255,255,255));
        tvTltle.setText(mContext.getResources().getString(R.string.around_the_supermarket));
        /**
         * 获取banner底部位置
         */
        ViewTreeObserver viewTreeObserver = bannerMarketBg.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bannerBottom = bannerMarketBg.getBottom();
            }
        });

        /**
         * 获取商品顶部指示器底部位置
         */
        ViewTreeObserver magicGoodIndicatorTopBottom = magicGoodIndicatorTop.getViewTreeObserver();
        magicGoodIndicatorTopBottom.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                magicGoodIndicatorTopOfBottom = magicGoodIndicatorTop.getBottom();
            }
        });

        /**
         * 获取商品底部指示器底部位置
         */
        ViewTreeObserver magicGoodIndicatorBottom = magicGoodIndicator.getViewTreeObserver();
        magicGoodIndicatorBottom.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                magicGoodIndicatorOfBottom = magicGoodIndicator.getBottom();
            }
        });

        /**
         * 获取沉浸背景底部位置
         */
        ViewTreeObserver backgroundTreeObserver = tvBackground.getViewTreeObserver();
        backgroundTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                backgroundBottom = tvBackground.getBottom();
            }
        });

        /**
         * 获取顶部指示器底部位置
         */
        ViewTreeObserver magicIndicatorTopBottom = magicIndicatorTop.getViewTreeObserver();
        magicIndicatorTopBottom.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                magicIndicatorTopOfBottom = magicIndicatorTop.getBottom() ;
            }
        });

        /**
         * 获取底部指示器底部位置
         */
        ViewTreeObserver magicIndicatorBottom = magicIndicatorPicture.getViewTreeObserver();
        magicIndicatorTopBottom.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                magicIndicatorOfBottom = magicIndicatorPicture.getBottom() ;
            }
        });

        /**
         * 滚动监听
         */
        nestedMarket.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY
                    , int oldScrollX, int oldScrollY) {
                changeTextView(scrollY);
                magicIndicatorTop.setVisibility(scrollY >= magicIndicatorOfBottom
                        - magicIndicatorTopOfBottom ? View.VISIBLE :View.INVISIBLE);
                tvLineGray.setVisibility(scrollY >= magicIndicatorOfBottom
                        - magicIndicatorTopOfBottom ? View.VISIBLE :View.INVISIBLE);
                magicGoodIndicatorTop.setVisibility(scrollY >= magicGoodIndicatorOfBottom
                        - magicGoodIndicatorTopOfBottom ? View.VISIBLE :View.INVISIBLE);
            }
        });
        marketMenuPageAdapter = new MarketMenuPageAdapter(getSupportFragmentManager()
                ,menuItemBeans,fragments,this);
        wrapGoods.setAdapter(marketMenuPageAdapter);

        bannerMarketBg.setIndicatorVisible(false);  //不显示指示器
        initMagicIndicator();
    }

    /**
     * 初始化菜单指示器
     */
    public void initMagicIndicator(){
        CommonNavigator commonNavigator = new CommonNavigator(this);
        marketIndicatorAdapter = new MarketIndicatorAdapter(menuItemBeans,mContext,this);
        commonNavigator.setAdapter(marketIndicatorAdapter);
        magicIndicatorPicture.setNavigator(commonNavigator);

        CommonNavigator commonNavigatorTop = new CommonNavigator(this);
        marketIndicatorTopAdapter = new MarketIndicatorTopAdapter(menuItemBeans,mContext,this);
        commonNavigatorTop.setAdapter(marketIndicatorTopAdapter);
        magicIndicatorTop.setNavigator(commonNavigatorTop);

        /**
         * 商品分类
         */
        CommonNavigator commonNavigatorGood = new CommonNavigator(mContext);
        CommonNavigator commonNavigatorGoodTop = new CommonNavigator(mContext);
        commonNavigatorGood.setAdjustMode(false);
        commonNavigatorGood.setEnablePivotScroll(true);
        commonNavigatorGood.setFollowTouch(false);
        commonNavigatorGood.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return goodsListBeans == null ? 0 : goodsListBeans.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(goodsListBeans.get(index).getName());
                simplePagerTitleView
                        .setNormalColor(mContext.getResources().getColor(R.color.c_8F949C));
                simplePagerTitleView
                        .setSelectedColor(mContext.getResources().getColor(R.color.c_FF3E3E));
                simplePagerTitleView.setGravity(Gravity.CENTER_VERTICAL);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commonNavigatorGood.onPageSelected(index);
                        commonNavigatorGood.notifyDataSetChanged();
                        commonNavigatorGoodTop.onPageSelected(index);
                        commonNavigatorGoodTop.notifyDataSetChanged();
                        EventBus.getDefault().post(new TagChangeEvent(index));
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
        magicGoodIndicator.setNavigator(commonNavigatorGood);

        /**
         * 商品分类置顶
         */
        commonNavigatorGoodTop.setAdjustMode(false);
        commonNavigatorGoodTop.setEnablePivotScroll(true);
        commonNavigatorGoodTop.setFollowTouch(false);
        commonNavigatorGoodTop.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return goodsListBeans == null ? 0 : goodsListBeans.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(goodsListBeans.get(index).getName());
                simplePagerTitleView
                        .setNormalColor(mContext.getResources().getColor(R.color.c_8F949C));
                simplePagerTitleView
                        .setSelectedColor(mContext.getResources().getColor(R.color.c_FF3E3E));
                simplePagerTitleView.setGravity(Gravity.CENTER_VERTICAL);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commonNavigatorGood.onPageSelected(index);
                        commonNavigatorGood.notifyDataSetChanged();
                        commonNavigatorGoodTop.onPageSelected(index);
                        commonNavigatorGoodTop.notifyDataSetChanged();
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

        wrapGoods.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                commonNavigator.onPageScrolled(position, positionOffset, positionOffsetPixels);
                commonNavigatorTop.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicGoodIndicator.onPageSelected(0);
                commonNavigator.onPageSelected(position);
                commonNavigatorTop.onPageSelected(position);
                cart_id = menuItemBeans.get(position).getId();
                getPresenter().getGoodList(area_id,cart_id,Constant.TYPE_GOODS_MARKET);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                commonNavigator.onPageScrollStateChanged(state);
                commonNavigatorTop.onPageScrollStateChanged(state);
            }
        });
        magicGoodIndicatorTop.setNavigator(commonNavigatorGoodTop);
        ViewPagerHelper.bind(magicIndicatorTop, wrapGoods);
        ViewPagerHelper.bind(magicIndicatorPicture,wrapGoods);
    }

    /**
     * 根据滚动距离改变TextView
     * @param scrollY
     */
    private void changeTextView(int scrollY) {
        GradientDrawable drawable = (GradientDrawable) tvSearch.getBackground().mutate();
        if((float)(backgroundBottom - scrollY)/backgroundBottom < 0.4){
            tvTltle.setText("");
            imgService.setImageResource(R.mipmap.icon_service_red);
            drawable.setStroke(2, mContext.getResources().getColor(R.color.c_FF3E3E));
        }else{
            tvTltle.setText(mContext.getResources().getString(R.string.around_the_supermarket));
            imgService.setImageResource(R.mipmap.icon_service);
            drawable.setStroke(2, mContext.getResources().getColor(R.color.c_FFFFFF));
        }
        tvSearch.setBackgroundDrawable(drawable);
        if(scrollY > 0 && scrollY < bannerBottom){
            /**
             * 动态根据滑动距离，改变控件宽度
             */
            int width = (int)((tvSearch.getMaxWidth()
                    - tvSearch.getMinWidth()) * scrollY /bannerBottom
                    + tvSearch.getMinWidth());
            tvTltle.setSelected(false);
            ViewGroup.LayoutParams layoutParams = tvSearch.getLayoutParams();
            layoutParams.width = width ;
            tvSearch.setLayoutParams(layoutParams);
        }else if(scrollY != 0){
            scrollY = bannerBottom ;
        }
        alphe = scrollY * (END_ALPHA - START_ALPHA) / bannerBottom + START_ALPHA ;
        StatusBarUtil.setStatusBarColor(mContext
                ,Color.argb(alphe,255,255,255));
        magicGoodIndicator.setBackgroundColor(Color.argb(scrollY * (END_ALPHA - START_ALPHA) / (magicGoodIndicatorOfBottom
                - magicGoodIndicatorTopOfBottom) + START_ALPHA,255,255,255));
        layoutTitle.setBackgroundColor(Color.argb(alphe,255,255,255));
        tvTltle.setSelected(alphe > 200 ? true : false);
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        area_id = Constant.getMyLocation().adCode ;
        type = Constant.TYPE_GOODS_MARKET ;
        getPresenter().getAdv(TYPE_MARKET);
        getPresenter().getMenu("",type,area_id);
    }

    @OnClick({R.id.tv_tltle,R.id.img_service,R.id.tv_search})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_tltle :    //标题
                    finish();
                break;

            case R.id.img_service : //客服
                Intent serviceIntent = new Intent(mContext, CustomerActivity.class);
                mContext.startActivity(serviceIntent);
                break;

            case R.id.tv_search :   //搜索
                mContext.startActivity(new Intent(mContext,SearchActivity.class));
                break;
        }
    }

    /**
     * 获取窝边超市广告图成功
     * @param response
     */
    @Override
    public void getAdvSuccess(AdvListBean response) {
        if(response.size()>0){
            tvBackground.setBackgroundColor(Color.parseColor(response.get(0).getDesc()));
        }else{
            tvBackground.setBackgroundColor(mContext.getResources().getColor(R.color.c_57B1F1));
        }
        bannerMarketBg.setPages(response, new MZHolderCreator<BannerMarketViewHolder>() {
            @Override
            public BannerMarketViewHolder createViewHolder() {
                return new BannerMarketViewHolder(mContext);
            }
        });
    }

    /**
     * 获取超市顶级菜单成功
     * @param response
     */
    @Override
    public void getMenuSuccess(MenuItemListBean response) {
        menuItemBeans.clear();
        menuItemBeans.addAll(response);
        marketIndicatorAdapter.notifyDataSetChanged();
        for (int i = 0 ; i < menuItemBeans.size() ; i ++){
            fragments.add(MarketFragment.newInstance(menuItemBeans.get(i),Constant.TYPE_GOODS_MARKET));
        }
        marketMenuPageAdapter.notifyDataSetChanged();
        marketIndicatorTopAdapter.notifyDataSetChanged();
        cart_id = menuItemBeans.get(0).getId();
        getPresenter().getGoodList(area_id,cart_id,Constant.TYPE_GOODS_MARKET);
    }

    /**
     * 菜单指示器点击事件
     * @param index
     */
    @Override
    public void onIndicatorClick(int index) {
        PLog.e("onIndicatorClick   " + index);
        cart_id = menuItemBeans.get(index).getId();
        getPresenter().getGoodList(area_id,cart_id,Constant.TYPE_GOODS_MARKET);
        magicIndicatorTop.getNavigator().onPageSelected(index);
        magicIndicatorTop.getNavigator().notifyDataSetChanged();
        magicIndicatorPicture.getNavigator().onPageSelected(index);
        magicIndicatorPicture.getNavigator().notifyDataSetChanged();
        wrapGoods.setCurrentItem(index);
    }

    /**
     * 顶部菜单指示器
     * @param index
     */
    @Override
    public void onIndicatorTopClick(int index) {
        cart_id = menuItemBeans.get(index).getId();
        getPresenter().getGoodList(area_id,cart_id,Constant.TYPE_GOODS_MARKET);
        magicIndicatorPicture.getNavigator().onPageSelected(index);
        magicIndicatorPicture.getNavigator().notifyDataSetChanged();
        magicIndicatorTop.getNavigator().onPageSelected(index);
        magicIndicatorTop.getNavigator().notifyDataSetChanged();
        wrapGoods.setCurrentItem(index);
    }

    /**
     * 加入购物车动画
     * @param view
     */
    @Override
    public void onAddToShopInFragmentClick(View view) {
        GoodsAnimUtil.setAnim(mContext,view,imgGoShop);
    }

    /**
     * 获取商品成功
     * @param response
     */
    @Override
    public void getGoodsListSuccess(MarketGoodsListBean response) {
        goodsListBeans.clear();
        if(response != null && response.size() > 0){
            goodsListBeans.addAll(response);
        }
        magicGoodIndicator.getNavigator().notifyDataSetChanged();
        magicGoodIndicatorTop.getNavigator().notifyDataSetChanged();
    }
}
