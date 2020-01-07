package com.zj.wz.wbyx.wbyxAndroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpFragment;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.BuyGoodChooseSkuDialog;
import com.zj.wz.wbyx.baseandroid.view.MyGridLayoutManager;
import com.zj.wz.wbyx.baseandroid.view.RecyclerViewSpacesItemDecoration;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.baseandroid.view.StickyScrollView;
import com.zj.wz.wbyx.baseandroid.view.WrapContentHeightViewPager;
import com.zj.wz.wbyx.baseandroid.view.dialog.CommonDialog;
import com.zj.wz.wbyx.wbyxAndroid.Gaode.GaoDeMap;
import com.zj.wz.wbyx.wbyxAndroid.activity.CategoriesActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.CustomerActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.DormitoryActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.MarketActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.RecommendActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.SearchActivity;
import com.zj.wz.wbyx.wbyxAndroid.adapter.BannerBgViewHolder;
import com.zj.wz.wbyx.wbyxAndroid.adapter.BannerViewHolder;
import com.zj.wz.wbyx.wbyxAndroid.adapter.CategoriesAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.CommonFragmentAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.HomeDormitoryAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.HomeGrouponAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.HomeMarketAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.HomeNewRecommendAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.HomeRecommendAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.HomeSubjectsAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.BannersBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.CategoriesBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GrouponBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.NewRecommendBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.RecomBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SubjectsBean;
import com.zj.wz.wbyx.wbyxAndroid.event.AddToShopCarEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.HomeLoadMoreEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.HomeRefreshEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.LocationResultEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.HomePresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.HomeView;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.OnClick;
import static com.zj.wz.wbyx.baseandroid.config.Constant.HOME_BANNER;
import static com.zj.wz.wbyx.baseandroid.config.Constant.HOME_CATEGORIES;
import static com.zj.wz.wbyx.baseandroid.config.Constant.HOME_GROUPON;
import static com.zj.wz.wbyx.baseandroid.config.Constant.HOME_NEW;
import static com.zj.wz.wbyx.baseandroid.config.Constant.HOME_RECOM;
import static com.zj.wz.wbyx.baseandroid.config.Constant.HOME_SUBJECTS;

/**
 * FileName: HomeFragment
 * Author: 曹伟
 * Date: 2019/9/20 20:25
 * Description: 首页fragment
 */

public class HomeFragment extends BaseMvpFragment<HomeView, HomePresenter>
        implements HomeView, MZBannerView.BannerPageClickListener
        , HomeNewRecommendAdapter.OnAddToshopCarListener, HomeSubjectsAdapter.OnAddShopCarClick {
    @BindView(R.id.smart_home)
    SmartRefreshLayout smartHome;
    @BindView(R.id.newsted_home)
    StickyScrollView newstedHome ;
    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar ;   //标题栏
    @BindView(R.id.tv_search)
    TextView tvSearch ; //搜索
    @BindView(R.id.tv_location)
    TextView tvLocation;        //地址
    @BindView(R.id.img_location)
    ImageView imgLocation ;
    @BindView(R.id.imgBt_service)
    ImageView imgBtService ;
    @BindView(R.id.banner_home)
    MZBannerView bannerHome ;       //轮播图
    @BindView(R.id.banner_home_bg)
    MZBannerView bannerHomeBg ;     //轮播图高斯处理
    @BindView(R.id.recycle_categories)
    RecyclerView recycleCategories ;       //分类
    @BindView(R.id.slide_indicator_point)
    SeekBar slide_indicator_point ;         //分类指示器
    @BindView(R.id.recycle_homeDormitory)
    RecyclerView recycleHomeDormitory ;     //首页宿舍小店
    @BindView(R.id.recycle_homeMarket)
    RecyclerView recycleHomeMarket ;        //窝边超市
    @BindView(R.id.recycle_recommend)
    RecyclerView recycleRecommend ; //窝边推荐
    @BindView(R.id.recycle_newRecommend)
    RecyclerView recycleNewRecommend ;  //最新上架
    @BindView(R.id.recycle_group)
    RecyclerView recycleGroup ; //拼团
    @BindView(R.id.recycle_subjects)
    RecyclerView recycleSubjects ;  //专题详情
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator ;     //底部viewpage指示器
    @BindView(R.id.viewpage_home)
    WrapContentHeightViewPager viewpageHome ;
    @BindView(R.id.magic_indicatorTop)
    MagicIndicator magicIindicatorTop ; //底部viewpage指示器置顶部分
    @BindView(R.id.relative_dormitory)
    RelativeLayout relativeDormitory;  //宿舍小店
    @BindView(R.id.relative_market)
    RelativeLayout relativeMarket ; //窝边超市
    @BindView(R.id.relative_recommended)
    RelativeLayout relativeRecommended ;    //窝边推荐


    private CategoriesAdapter categoriesAdapter ;       //分类适配器
    private HomeDormitoryAdapter homeDormitoryAdapter ;     //宿舍小店适配器
    private HomeMarketAdapter homeMarketAdapter ;   //窝边超市
    private HomeRecommendAdapter homeRecomendAdapter ;   //窝边推荐
    private HomeNewRecommendAdapter homeNewRecommendAdapter ;   //最新推荐
    private HomeGrouponAdapter homeGrouponAdapter ; //拼团
    private HomeSubjectsAdapter homeSubjectsAdapter ;   //专题详情

    private List<BannersBean> bannerImage = new ArrayList<>();      //banner集合
    private List<CategoriesBean> categoriesBeans = new ArrayList<>();   //分类集合
    private List<RecomBean.DormShopBean.GoodsBeanXX> goodsBeanXXList = new ArrayList<>(); //宿舍小店
    private List<RecomBean.CityShopBean.GoodsBeanX> goodsBeanXList = new ArrayList<>(); //窝边超市
    private List<RecomBean.RecomShopBean.GoodsBean> goodsBeanList = new ArrayList<>();  //窝边推荐
    private List<NewRecommendBean.GoodsBean> newRecommendGoods = new ArrayList<>(); //最新推荐
    private List<GrouponBean.GoodsBean> grouponGoods = new ArrayList<>() ;  //拼团
    private List<SubjectsBean> subjectsBeans = new ArrayList<>() ;  //专题详情

    private int bannerBottom = 0 ;   //banner底部位置
    private int toolBarBottom = 0 ;    //toolBar底部位置
    private int magicIindicatorBottom = 0 ; //viewpage指示器底部
    private int TopmagicIindicatorBottom = 0 ;  //隐藏的指示器底部

    private String[] titles ;   //标题
    private String[] subtitles ;    //副标题
    private List<Fragment> fragments = new ArrayList<>();
    private CommonFragmentAdapter commonFragmentAdapter ;  //底部viewpage适配器

    private Map<String,String> getGoodDetailsMap = new HashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        bannerHome.start();
        bannerHomeBg.start();
    }

    @Override
    protected void setupView(View rootView) {
        /**
         *  刷新，加载更多
         */
        smartHome.setEnableLoadMore(false);
        smartHome.setEnableRefresh(true);
        smartHome.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                EventBus.getDefault().post(new HomeLoadMoreEvent());
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                PLog.e(smartHome.getState() + "");
                getPresenter().getHomeDate(Constant.getMyLocation().adCode,HOME_BANNER
                        + "," + HOME_CATEGORIES + "," + HOME_RECOM + "," + HOME_NEW + ","
                        + HOME_GROUPON + "," + HOME_SUBJECTS);
                EventBus.getDefault().post(new HomeRefreshEvent());
            }
        });

        /**
         * 获取banner底部位置
         */
        ViewTreeObserver viewTreeObserver = bannerHome.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bannerBottom = bannerHome.getBottom() ;
            }
        });

        /**
         * 位置标题栏
         */
        ViewTreeObserver treeObserver = homeToolbar.getViewTreeObserver();
        treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                toolBarBottom = homeToolbar.getBottom() ;
            }
        });

        /**
         * viewpage顶部指示器
         */
        ViewTreeObserver magicIindicatorTopTreeObserver = magicIindicatorTop.getViewTreeObserver();
        magicIindicatorTopTreeObserver
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                TopmagicIindicatorBottom = magicIindicatorTop.getBottom() ;
            }
        });

        ViewTreeObserver magicIindicatorTreeObserver = magicIndicator.getViewTreeObserver();
        magicIindicatorTreeObserver
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                magicIindicatorBottom = magicIndicator.getBottom() ;
            }
        });

        /**
         * 滚动距离判断
         */
        newstedHome.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX
                    , int scrollY, int oldScrollX, int oldScrollY) {
                changeToolBar(scrollY - (bannerBottom - toolBarBottom));
                magicIindicatorTop.setVisibility((magicIindicatorBottom - TopmagicIindicatorBottom)
                        >= scrollY ? View.INVISIBLE : View.VISIBLE);
            }
        });
        bannerHome.setDelayedTime(3000);    //banner切换间隔
        bannerHomeBg.setDelayedTime(3000);
        bannerHomeBg.setIndicatorVisible(false);
        /**
         * 设置banner点击监听
         */
        bannerHome.setBannerPageClickListener(this);

        /**
         * 分类和分类指示器
         */
        slide_indicator_point.setPadding(0, 0, 0, 0);
        slide_indicator_point.setThumbOffset(0);
        categoriesAdapter = new CategoriesAdapter(categoriesBeans,mContext);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2
                , GridLayoutManager.HORIZONTAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recycleCategories.setHasFixedSize(true);
        recycleCategories.setNestedScrollingEnabled(false);
        recycleCategories.setLayoutManager(layoutManager);
        recycleCategories.setAdapter(categoriesAdapter);
        recycleCategories.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //显示区域的高度。
                int extent = recycleCategories.computeHorizontalScrollExtent();
                //整体的高度，注意是整体，包括在显示区域之外的。
                int range = recycleCategories.computeHorizontalScrollRange();
                //已经向下滚动的距离，为0时表示已处于顶部。
                int offset = recycleCategories.computeHorizontalScrollOffset();
                //此处获取seekbar的getThumb，就是可以滑动的小的滚动游标
                GradientDrawable gradientDrawable
                        =(GradientDrawable) slide_indicator_point.getThumb();
                //根据列表的个数，动态设置游标的大小，设置游标的时候
                // ，progress进度的颜色设置为和seekbar的颜色设置的一样的，所以就不显示进度了。
                gradientDrawable.setSize(extent/(categoriesBeans.size()),10);
                //设置可滚动区域
                slide_indicator_point.setMax((int)(range-extent));
                if (dx==0)
                    slide_indicator_point.setProgress(0);
                slide_indicator_point.setProgress(offset);
            }
        });

        /**
         * 宿舍小店
         */
        homeDormitoryAdapter = new HomeDormitoryAdapter(goodsBeanXXList,mContext);
        MyGridLayoutManager gridLayoutManager = new MyGridLayoutManager(mContext, 3
                , GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setScrollEnabled(false);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        recycleHomeDormitory.setHasFixedSize(true);
        recycleHomeDormitory.setNestedScrollingEnabled(false);
        recycleHomeDormitory.setLayoutManager(gridLayoutManager);
        recycleHomeDormitory.setAdapter(homeDormitoryAdapter);
        recycleHomeDormitory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return relativeDormitory.onTouchEvent(event);   //将点击监听透传给父容器
            }
        });

        /**
         * 窝边商城
         */
        homeMarketAdapter = new HomeMarketAdapter(goodsBeanXList,mContext);
        MyGridLayoutManager gridLayoutManagerMarket = new MyGridLayoutManager(mContext, 2
                , GridLayoutManager.VERTICAL, false);
        gridLayoutManagerMarket.setScrollEnabled(false);
        gridLayoutManagerMarket.setSmoothScrollbarEnabled(true);
        gridLayoutManagerMarket.setAutoMeasureEnabled(true);
        recycleHomeMarket.setHasFixedSize(true);
        recycleHomeMarket.setNestedScrollingEnabled(false);
        recycleHomeMarket.setLayoutManager(gridLayoutManagerMarket);
        recycleHomeMarket.setAdapter(homeMarketAdapter);
        recycleHomeMarket.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return relativeMarket.onTouchEvent(event);      //将点击监听透传给父容器
            }
        });

        /**
         * 窝边推荐
         */
        homeRecomendAdapter = new HomeRecommendAdapter(goodsBeanList,mContext);
        MyGridLayoutManager gridLayoutManagerRecommend = new MyGridLayoutManager(mContext
                , 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManagerRecommend.setScrollEnabled(false);
        gridLayoutManagerRecommend.setSmoothScrollbarEnabled(true);
        gridLayoutManagerRecommend.setAutoMeasureEnabled(true);
        recycleRecommend.setHasFixedSize(true);
        recycleRecommend.setNestedScrollingEnabled(false);
        recycleRecommend.setLayoutManager(gridLayoutManagerRecommend);
        recycleRecommend.setAdapter(homeRecomendAdapter);
        recycleRecommend.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return relativeRecommended.onTouchEvent(event);     //将点击监听透传给父容器
            }
        });

        /**
         *最新推荐
         */
        homeNewRecommendAdapter = new HomeNewRecommendAdapter(newRecommendGoods,mContext,this);
        GridLayoutManager gridLayoutManagerNewRecommend
                = new GridLayoutManager(mContext, 1
                , GridLayoutManager.HORIZONTAL, false);
        gridLayoutManagerNewRecommend.setSmoothScrollbarEnabled(true);
        gridLayoutManagerNewRecommend.setAutoMeasureEnabled(true);
        recycleNewRecommend.setHasFixedSize(true);
        recycleNewRecommend.setNestedScrollingEnabled(false);
        recycleNewRecommend.setLayoutManager(gridLayoutManagerNewRecommend);
        //设置Adapter
        recycleNewRecommend.setAdapter(homeNewRecommendAdapter);

        /**
         * 拼团
         */
        homeGrouponAdapter = new HomeGrouponAdapter(grouponGoods,mContext);
        GridLayoutManager gridLayoutManagerGroup = new GridLayoutManager(mContext, 1
                , GridLayoutManager.HORIZONTAL, true);
        //设置布局管理器
        gridLayoutManagerGroup.setSmoothScrollbarEnabled(true);
        gridLayoutManagerGroup.setAutoMeasureEnabled(true);
        recycleGroup.setHasFixedSize(true);
        recycleGroup.setNestedScrollingEnabled(false);
        recycleGroup.setLayoutManager(gridLayoutManagerGroup);
        //设置Adapter
        recycleGroup.setAdapter(homeGrouponAdapter);

        /**
         * 专题详情
         */
        homeSubjectsAdapter = new HomeSubjectsAdapter(subjectsBeans,mContext,this);
        LinearLayoutManager layoutManagerSubject = new LinearLayoutManager(mContext);
        //设置为垂直布局，这也是默认的
        layoutManagerSubject.setOrientation(OrientationHelper.VERTICAL);
        layoutManagerSubject.setSmoothScrollbarEnabled(true);
        layoutManagerSubject.setAutoMeasureEnabled(true);
        recycleSubjects.setHasFixedSize(true);
        recycleSubjects.setNestedScrollingEnabled(false);
        //设置布局管理器
        recycleSubjects.setLayoutManager(layoutManagerSubject);
        //设置Adapter
        recycleSubjects.setAdapter(homeSubjectsAdapter);
        recycleSubjects.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext,
                LinearLayoutManager.HORIZONTAL
                ,20
                ,mContext.getResources().getColor(R.color.c_EEEEEE)));

    }

    /**
     * 根据滑动对头部搜索框颜色修改
     * @param v
     */
    private void changeToolBar(float v) {
        if(v > 0){  //toolBar已经滑动到轮播图下方
            StatusBarUtil.setStatusBarColor(getActivity()
                    ,mContext.getResources().getColor(R.color.c_FFFFFF));
            homeToolbar.setBackgroundColor(mContext.getResources().getColor(R.color.c_FFFFFF));
            tvLocation.setTextColor(mContext.getResources().getColor(R.color.c_1B1B1B));
            imgLocation.setImageResource(R.mipmap.icon_address_gray);
            tvSearch.setBackground(mContext.getResources()
                    .getDrawable(R.drawable.shape_home_search_gray));
            tvSearch.setTextColor(mContext.getResources().getColor(R.color.c_666666));
            imgBtService.setImageResource(R.mipmap.icon_service_gray);
        }else if(v > -0.5 && v < 0){
            StatusBarUtil.setStatusBarColor(getActivity()
                    ,mContext.getResources().getColor(R.color.c_e0000000));
            homeToolbar.setBackgroundColor(mContext.getResources().getColor(R.color.c_e0000000));
            tvLocation.setTextColor(mContext.getResources().getColor(R.color.c_550000));
            imgLocation.setImageResource(R.mipmap.icon_address);
            imgBtService.setImageResource(R.mipmap.icon_service);
            tvSearch.setBackground(mContext.getResources()
                    .getDrawable(R.drawable.shape_home_search_gray));
            tvSearch.setTextColor(mContext.getResources().getColor(R.color.c_FFFFFF));
        }else {
            StatusBarUtil.setStatusBarColor(getActivity()
                    ,mContext.getResources().getColor(R.color.c_00000000));
            homeToolbar.setBackgroundColor(mContext.getResources().getColor(R.color.c_00000000));
            tvLocation.setTextColor(mContext.getResources().getColor(R.color.c_FFFFFF));
            imgLocation.setImageResource(R.mipmap.icon_address);
            imgBtService.setImageResource(R.mipmap.icon_service);
            tvSearch.setBackground(mContext.getResources()
                    .getDrawable(R.drawable.shape_home_search));
            tvSearch.setTextColor(mContext.getResources().getColor(R.color.c_666666));
        }
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        /**
         * 微笑指示器
         */
        initMagicIndicator();
        GaoDeMap.getInstance().startMap();
    }

    /**
     * 初始化指示器和viewpage相关
     */
    private void initMagicIndicator() {
        titles = mContext.getResources().getStringArray(R.array.home_title);
        subtitles = mContext.getResources().getStringArray(R.array.home_subtitle);
        fragments.add(new HomeOfAllFragment());
        fragments.add(new HomeOfMarketFragment());
        fragments.add(new HomeOfRecommendFragment());
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setAdjustMode(true);
        magicIndicator.setNavigator(commonNavigator);

        CommonNavigator commonNavigatorTop = new CommonNavigator(mContext);
        commonNavigatorTop.setAdjustMode(true);
        magicIindicatorTop.setNavigator(commonNavigatorTop);
        commonFragmentAdapter = new CommonFragmentAdapter(getActivity().getSupportFragmentManager()
                ,viewpageHome,fragments);
        viewpageHome.setAdapter(commonFragmentAdapter);
        commonFragmentAdapter
                .setOnExtraPageChangeListener(new CommonFragmentAdapter.OnExtraPageChangeListener(){
                    @Override
                    public void onExtraPageSelected(int i) {

                    }
                });
        viewpageHome.setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);
        commonNavigator.setAdapter(commonNavigatorAdapter);
        commonNavigatorTop.setAdapter(commonNavigatorAdapter);
        viewpageHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                commonNavigator.onPageSelected(arg0);
                commonNavigatorTop.onPageSelected(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                commonNavigator.onPageScrolled(arg0, arg1, arg2);
                commonNavigatorTop.onPageScrolled(arg0, arg1, arg2);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                commonNavigator.onPageScrollStateChanged(arg0);
                commonNavigatorTop.onPageScrollStateChanged(arg0);
            }
        });
        LinearLayout titleContainer = commonNavigator.getTitleContainer();
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(mContext, 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.smile_splitter));

        LinearLayout titleContainerTop = commonNavigatorTop.getTitleContainer();
        titleContainerTop.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainerTop.setDividerPadding(UIUtil.dip2px(mContext, 15));
        titleContainerTop.setDividerDrawable(getResources().getDrawable(R.drawable.smile_splitter));
        ViewPagerHelper.bind(magicIndicator, viewpageHome);
        ViewPagerHelper.bind(magicIindicatorTop,viewpageHome);
    }

    /**
     * viewpager指示器适配器
     */
    private CommonNavigatorAdapter commonNavigatorAdapter = new CommonNavigatorAdapter() {
        @Override
        public int getCount() {
            return titles == null ? 0 : titles.length;
        }

        @Override
        public IPagerTitleView getTitleView(Context context, final int index) {
            CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(mContext);
            View view = LayoutInflater.from(context).inflate(R.layout.smile_pager_title,null);
            //初始化
            TextView tvTitle = view.findViewById(R.id.tv_title);
            tvTitle.setText(titles[index]);
            TextView tvSubTitle = view.findViewById(R.id.tv_subTitle);
            tvSubTitle.setText(subtitles[index]);
            ImageView imgSmile = view.findViewById(R.id.img_smile);
            commonPagerTitleView.setContentView(view);
            commonPagerTitleView.setOnPagerTitleChangeListener
                    (new CommonPagerTitleView.OnPagerTitleChangeListener() {
                        @Override
                        public void onSelected(int index, int totalCount) {
                            imgSmile.setVisibility(View.VISIBLE);
                            tvTitle.setTextColor(mContext.getResources().getColor(R.color.c_FF3E3E));
                            tvSubTitle.setTextColor(mContext.getResources().getColor(R.color.c_FD7679));
                            Glide.with(mContext)
                                    .load(R.mipmap.smile_left_to_right)
                                    .listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e
                                                , Object model, Target<Drawable> target
                                                , boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                            if (resource instanceof GifDrawable) {
                                                //加载一次
                                                ((GifDrawable)resource).setLoopCount(1);
                                            }
                                            return false;
                                        }
                                    })
                                    .into(imgSmile);
                        }

                        @Override
                        public void onDeselected(int index, int totalCount) {
                            tvTitle.setTextColor(mContext.getResources().getColor(R.color.c_1B1B1B));
                            tvSubTitle.setTextColor(mContext.getResources().getColor(R.color.c_8F949C));
                            imgSmile.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                            tvTitle.setScaleX(1.0f + (0.8f - 1.0f) * leavePercent);
                            tvTitle.setScaleY(1.0f + (0.8f - 1.0f) * leavePercent);
                            tvSubTitle.setScaleX(1.0f + (0.8f - 1.0f) * leavePercent);
                            tvSubTitle.setScaleY(1.0f + (0.8f - 1.0f) * leavePercent);
                        }

                        @Override
                        public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                            tvTitle.setScaleX(0.8f + (1.0f - 0.8f) * enterPercent);
                            tvTitle.setScaleY(0.8f + (1.0f - 0.8f) * enterPercent);
                            tvSubTitle.setScaleX(0.8f + (1.0f - 0.8f) * enterPercent);
                            tvSubTitle.setScaleY(0.8f + (1.0f - 0.8f) * enterPercent);
                        }
                    });

            commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewpageHome.setCurrentItem(index);
                }
            });

            return commonPagerTitleView;
        }

        @Override
        public IPagerIndicator getIndicator(Context context) {
            return null;
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        bannerHome.pause();
        bannerHomeBg.pause();
    }

    /**
     * 定位信息回调
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LocationResultEvent event){
        tvLocation.setText(Constant.getMyLocation().poiName);
        if(event.isMbResult() && !TextUtils.isEmpty(Constant.getUserToken())){
            getPresenter().getHomeDate(Constant.getMyLocation().adCode,HOME_BANNER
                    + "," + HOME_CATEGORIES + "," + HOME_RECOM + "," + HOME_NEW + "," +HOME_GROUPON
                    + "," + HOME_SUBJECTS);
        }
    }

    /**
     * 获取首页banners成功
     * @param banners
     */
    @Override
    public void getBannerSuccess(List<BannersBean> banners) {
        bannerImage.addAll(banners);
        // 设置数据
        bannerHome.setPages(banners, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder(mContext);
            }
        });
        bannerHomeBg.setPages(banners, new MZHolderCreator<BannerBgViewHolder>() {
            @Override
            public BannerBgViewHolder createViewHolder() {
                return new BannerBgViewHolder(mContext);
            }
        });
        bannerHome.start();
        bannerHomeBg.start();
    }

    /**
     * 获取分类成功
     * @param categories
     */
    @Override
    public void getCategoriesSuccess(List<CategoriesBean> categories) {
        categoriesBeans.clear();
        categoriesBeans.addAll(categories);
        categoriesAdapter.notifyDataSetChanged();
    }

    /**
     * 获取模块成功
     * @param recom
     */
    @Override
    public void getRecomSuccess(RecomBean recom) {
        goodsBeanXXList.clear();
        goodsBeanXXList.addAll(recom.getDorm_shop().getGoods());
        homeDormitoryAdapter.notifyDataSetChanged();

        goodsBeanXList.clear();
        goodsBeanXList.addAll(recom.getCity_shop().getGoods());
        homeMarketAdapter.notifyDataSetChanged();

        goodsBeanList.clear();
        goodsBeanList.addAll(recom.getRecom_shop().getGoods());
        homeRecomendAdapter.notifyDataSetChanged();
    }

    /**
     * 获取最新推荐成功
     * @param newX
     */
    @Override
    public void getNewRecommendSuccess(NewRecommendBean newX) {
        newRecommendGoods.clear();
        newRecommendGoods.addAll(newX.getGoods());
        homeNewRecommendAdapter.notifyDataSetChanged();
    }

    /**
     * 获取拼团成功
     * @param groupon
     */
    @Override
    public void getGrouponSuccess(GrouponBean groupon) {
        grouponGoods.clear();
        grouponGoods.addAll(groupon.getGoods());
        homeGrouponAdapter.notifyDataSetChanged();
    }

    /**
     * 获取专题详情成功
     * @param subjects
     */
    @Override
    public void getSubjectsSuccess(List<SubjectsBean> subjects) {
        subjectsBeans.clear();
        subjectsBeans.addAll(subjects);
        homeSubjectsAdapter.notifyDataSetChanged();
    }

    /**
     * 加载数据完成
     */
    @Override
    public void onComplete() {
        smartHome.finishRefresh();
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
        EventBus.getDefault().post(new AddToShopCarEvent(true,addCar));
    }

    @Override
    public void marketAddCarSuccess(View addCar) {
        EventBus.getDefault().post(new AddToShopCarEvent(true,addCar));
    }

    /**
     * banner点击事件监听
     * @param view
     * @param i
     */
    @Override
    public void onPageClick(View view, int i) {
        Intent intent = new Intent(mContext, CategoriesActivity.class);
        mContext.startActivity(intent);
//        BannersBean bean = bannerImage.get(i);
//        if(!Constant.hasLogin()){    //未登录去登录
//            Intent intent = new Intent(mContext, LoginActivity.class);
//            mContext.startActivity(intent);
//            return;
//        }
//
//        //已登录情况
//        switch (bean.getParams().getType()){
//            case 1 :    //商品,跳转到商品详情(需要相应的参数)
//                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
//                intent.putExtra("goods_type",bean.getParams().getParam().getGoods_type());
//                intent.putExtra("warehouse_id"
//                        ,bean.getParams().getParam().getWarehouse_id());
//                intent.putExtra("goods_id",bean.getParams().getParam().getGoods_id());
//                intent.putExtra("inlet",bean.getParams().getParam().getInlet());
//                mContext.startActivity(intent);
//                break;
//
//            case 2 :    //链接，区分链接进行跳转
//                UrlUtils.UrlEntity entity = UrlUtils.parse(bean.getParams().getUrl());
//                if(entity.baseUrl.endsWith("aroundSupermarket")){   //窝边超市或者推荐商城
//
//                    switch (entity.params.get("goods_type")){
//                        case "1" :      //推荐商城
//                            Intent typeOneIntent = new Intent(mContext, RecommendActivity.class);
//                            typeOneIntent.putExtra("goods_type"
//                                    ,entity.params.get("goods_type"));
//                            typeOneIntent.putExtra("area_id",entity.params.get("area_id"));
//                            typeOneIntent.putExtra("from",entity.params.get("from"));
//                            typeOneIntent.putExtra("isappinstalled"
//                                    ,entity.params.get("isappinstalled"));
//                            mContext.startActivity(typeOneIntent);
//                            break;
//
//                        case "2" :      //窝边超市
//                            Intent typeTwoIntent = new Intent(mContext, MarketActivity.class);
//                            typeTwoIntent.putExtra("goods_type"
//                                    ,entity.params.get("goods_type"));
//                            typeTwoIntent.putExtra("area_id",entity.params.get("area_id"));
//                            typeTwoIntent.putExtra("from",entity.params.get("from"));
//                            typeTwoIntent.putExtra("isappinstalled"
//                                    ,entity.params.get("isappinstalled"));
//                            mContext.startActivity(typeTwoIntent);
//                            break;
//
//                    }
//                }else if(entity.baseUrl.endsWith("brand")) {      //专题活动
//
//                    Intent subjectsIntent = new Intent(mContext, SubjectsActivity.class);
//                    subjectsIntent.putExtra("activity_id",entity.params.get("activity_id"));
//                    mContext.startActivity(subjectsIntent);
//
//                }else if(entity.baseUrl.endsWith("classify")){      //分类商品
//
//                    Intent categoriesIntent = new Intent(mContext, CategoriesActivity.class);
//                    categoriesIntent.putExtra("cate_id",entity.params.get("param"));
//                    mContext.startActivity(categoriesIntent);
//
//                }else if(entity.baseUrl.endsWith("groupBooking")){      //拼团
//
//                    Intent groupIntent = new Intent(mContext, GroupBookingActivity.class);
//                    groupIntent.putExtra("url",entity.baseUrl);
//                    mContext.startActivity(groupIntent);
//
//                }else if(entity.baseUrl.endsWith("appshophome")){   //宿舍小店
//
//                    Intent dormitoryIntent = new Intent(mContext, DormitoryActivity.class);
//                    dormitoryIntent.putExtra("from",entity.params.get("from"));
//                    dormitoryIntent.putExtra("isappinstalled"
//                            ,entity.params.get("isappinstalled"));
//                    mContext.startActivity(dormitoryIntent);
//
//                }else if(entity.baseUrl.endsWith("detail")){    //商品详情
//
//                    Intent detailIntent = new Intent(mContext,GoodsDetailActivity.class);
//                    String obj = new Gson().toJson(entity.params.get("proObj")) ;
//                    Map<String,String> map = new Gson().fromJson(obj.substring(1,obj.length() - 1)
//                            .replaceAll("\\\\",""),Map.class);
//                    for(String key:map.keySet()){
//                        detailIntent.putExtra(key,map.get(key));
//                    }
//                    mContext.startActivity(detailIntent);
//
//                }else if(entity.baseUrl.endsWith("share_vip")){    //分享有礼
//
//                    Intent shareIntent = new Intent(mContext, SharePoliteActivity.class);
//                    mContext.startActivity(shareIntent);
//
//                }else{  //其余的网页链接跳转
//
//                    Intent otherWebIntent = new Intent(mContext, OtherWebActivity.class);
//                    otherWebIntent.putExtra("url",entity.baseUrl);
//                    mContext.startActivity(otherWebIntent);
//
//                }
//                break;
//        }
    }

    /**
     * 顶部点击
     */
    @OnClick({R.id.imgBt_service,R.id.tv_search,R.id.relative_market,R.id.relative_dormitory
            ,R.id.relative_recommended})
    public void OnClick(View view){
        PLog.e(view.getId() + "");
        switch (view.getId()){
            case R.id.tv_search :   //搜索
                Intent searchIntent = new Intent(mContext, SearchActivity.class);
                mContext.startActivity(searchIntent);
                break;

            case R.id.imgBt_service :   //客服
                Intent serviceIntent = new Intent(mContext, CustomerActivity.class);
                mContext.startActivity(serviceIntent);
                break;

            case R.id.relative_market : //窝边超市
                    Intent marketIntent = new Intent(mContext,MarketActivity.class);
                    mContext.startActivity(marketIntent);
                break;

            case R.id.relative_dormitory :  //宿舍小店
                    Intent dormitoryIntent = new Intent(mContext,DormitoryActivity.class);
                    mContext.startActivity(dormitoryIntent);
                break;

            case R.id.relative_recommended :   //窝边推荐
                    Intent recommendedIntent = new Intent(mContext,RecommendActivity.class);
                    mContext.startActivity(recommendedIntent);
                break;
        }
    }

    /**
     * 新品推荐加入购物车
     * @param addCar
     * @param position
     */
    @Override
    public void OnNewRecommendAddToShopCar(View addCar, int position) {
        getGoodDetailsMap.clear();
        getGoodDetailsMap.put("goods_type",newRecommendGoods.get(position).getGoods_type());
        getGoodDetailsMap.put("goods_id",newRecommendGoods.get(position).getGoods_id());
        getGoodDetailsMap.put("warehouse_id",newRecommendGoods.get(position).getWarehouse_id());
        getGoodDetailsMap.put("inlet","common");
        getPresenter().getGoodsDetail(addCar,getGoodDetailsMap);
    }

    /**
     * 专题详情加入购物车
     * @param addCar
     * @param adapterPosition
     * @param position
     */
    @Override
    public void OnAddToShopCar(View addCar, int adapterPosition, int position) {
        getGoodDetailsMap.clear();
        getGoodDetailsMap.put("goods_type",subjectsBeans.get(adapterPosition).getGoods().get(position).getGoods_type());
        getGoodDetailsMap.put("goods_id",subjectsBeans.get(adapterPosition).getGoods().get(position).getGoods_id());
        getGoodDetailsMap.put("warehouse_id",subjectsBeans.get(adapterPosition).getGoods().get(position).getWarehouse_id());
        getGoodDetailsMap.put("inlet","common");
        getPresenter().getGoodsDetail(addCar,getGoodDetailsMap);
    }
}
