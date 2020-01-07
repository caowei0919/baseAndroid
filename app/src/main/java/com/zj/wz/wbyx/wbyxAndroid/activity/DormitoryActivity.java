package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.enlogy.statusview.StatusRelativeLayout;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.mvp.base.BaseApplication;
import com.zj.wz.wbyx.baseandroid.view.MarqueeTextView;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.baseandroid.view.StickyScrollView;
import com.zj.wz.wbyx.baseandroid.view.WrapContentHeightViewPager;
import com.zj.wz.wbyx.baseandroid.view.dialog.BaseWindowDialog;
import com.zj.wz.wbyx.baseandroid.view.dialog.DialogViewHolder;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryBean;
import com.zj.wz.wbyx.wbyxAndroid.event.AddressEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.DormitoryPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.DormitoryView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import com.zj.wz.wbyx.wbyxAndroid.adapter.BannerDormitoryViewHolder;
import com.zj.wz.wbyx.wbyxAndroid.adapter.ChooseShopAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.CommonFragmentAdapter;
import com.zj.wz.wbyx.wbyxAndroid.fragment.DormitoryGoodsFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.DormitoryShopInfoFragment;

/**
 * FileName: DormitoryActivity
 * Author: 曹伟
 * Date: 2019/11/9 10:41
 * Description:宿舍小店
 */

public class DormitoryActivity extends BaseMvpActivity<DormitoryView, DormitoryPresenter>
        implements DormitoryView{

    @BindView(R.id.stick_dormitory)
    StickyScrollView stickDormitory ;
    @BindView(R.id.status_dormitory)
    StatusRelativeLayout statusDormitory ;
    @BindView(R.id.mzbanner_dormitory)
    MZBannerView mzbannerDormitory ;
    @BindView(R.id.layout_title)
    RelativeLayout layoutTitle ;       //标题栏
    @BindView(R.id.tv_location)
    TextView tvLocation ;   //地址
    @BindView(R.id.img_location)
    ImageView imgLocation ; //地址图标
    @BindView(R.id.img_locationRight)
    ImageView imgLocationRight ; //下拉选择图标
    @BindView(R.id.img_back)
    ImageView imgBack ; //返回
    @BindView(R.id.img_shopAvatar)
    ImageView imgShopAvatar ;       //店铺图片
    @BindView(R.id.tv_shopName)
    TextView tvShopName ;   //店铺名称
    @BindView(R.id.img_chooseShop)
    ImageView imgChooseShop ;   //选择店铺
    @BindView(R.id.tv_shopNotice)
    MarqueeTextView tvShopNotice ;  //店铺公告
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator ; //指示器
    @BindView(R.id.magic_indicatorTop)
    MagicIndicator magicIndicatorTop ;  //顶部指示器
    @BindView(R.id.viewpager_dormitory)
    WrapContentHeightViewPager viewpagerDormitory ;

    private int shopIndex = 0 ; //当前展示的店铺position
    private int magicIndicatorBottom = 0 ;  //指示器底部
    private int magicIndicatorTopBottom = 0 ;   //指示器顶部隐藏底部
    private DormitoryBean dormitoryBean ;  //宿舍小店
    private String[] items ;
    private List<Fragment> fragments = new ArrayList<>();
    private static final int START_ALPHA = 0;
    private static final int END_ALPHA = 255;
    private CommonFragmentAdapter commonFragmentAdapter ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dormitory;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mzbannerDormitory.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mzbannerDormitory.pause();
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);

        /**
         * 指示器底部获取
         */
        ViewTreeObserver magicIndicatorViewTree = magicIndicator.getViewTreeObserver();
        magicIndicatorViewTree.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                magicIndicatorBottom = magicIndicator.getBottom() ;
            }
        });

        /**
         * 顶部指示器底部获取
         */
        ViewTreeObserver magicIndicatorTopViewTree = magicIndicatorTop.getViewTreeObserver();
        magicIndicatorTopViewTree.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                magicIndicatorTopBottom = magicIndicatorTop.getBottom() ;
            }
        });

        /**
         * 滚动监听
         */
        stickDormitory.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int alpha ;
                if(scrollY > magicIndicatorBottom - magicIndicatorTopBottom){
                    alpha = 255 ;
                }else{
                    alpha = scrollY * (END_ALPHA - START_ALPHA)
                            / (magicIndicatorBottom - magicIndicatorTopBottom) + START_ALPHA ;
                }
                if (alpha > 150){
                    imgBack.setImageResource(R.mipmap.img_back);
                    imgLocation.setImageResource(R.mipmap.icon_address_black);
                    imgLocationRight.setImageResource(R.mipmap.img_drop_down_black);
                    tvLocation.setTextColor(mContext.getResources().getColor(R.color.c_1B1B1B));
                }else{
                    imgBack.setImageResource(R.mipmap.img_back_white);
                    imgLocation.setImageResource(R.mipmap.icon_address);
                    imgLocationRight.setImageResource(R.mipmap.img_drop_down_white);
                    tvLocation.setTextColor(mContext.getResources().getColor(R.color.c_FFFFFF));
                }
                StatusBarUtil.setStatusBarColor(mContext
                        ,Color.argb(alpha,255,255,255));
                layoutTitle.setBackgroundColor(Color.argb(alpha,255,255,255));
                magicIndicatorTop.setVisibility(scrollY > magicIndicatorBottom
                        - magicIndicatorTopBottom ? View.VISIBLE : View.INVISIBLE);
            }
        });
        initMagicIndicator();
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().getDormitor();
    }

    /**
     * 获取宿舍小店商品成功
     * @param response
     */
    @Override
    public void getDormitorySuccess(DormitoryBean response) {
        dormitoryBean = response ;
        StatusBarUtil.setRootViewFitsSystemWindows(mContext,false);
        StatusBarUtil.setStatusBarColor(mContext,mContext
                .getResources().getColor(R.color.c_00000000));
        layoutTitle.setBackgroundColor(Color.argb(0,255,255,255));
        mzbannerDormitory.setPages(response.getBanners(), new MZHolderCreator<BannerDormitoryViewHolder>() {
            @Override
            public BannerDormitoryViewHolder createViewHolder() {
                return new BannerDormitoryViewHolder(mContext);
            }
        });
        mzbannerDormitory.start();
        tvLocation.setText(response.getDorm());
        showShop(dormitoryBean.getShoper().get(0));
        showShopList(response,false);
    }

    /**
     * 初始化指示器
     */
    private void initMagicIndicator() {
        items = mContext.getResources().getStringArray(R.array.dormitory_title);
        fragments.add(DormitoryGoodsFragment.newInstance());
        fragments.add(DormitoryShopInfoFragment.newInstance());
        commonFragmentAdapter = new CommonFragmentAdapter(getSupportFragmentManager()
                ,viewpagerDormitory,fragments);
        viewpagerDormitory.setAdapter(commonFragmentAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        CommonNavigator commonNavigatorTop = new CommonNavigator(mContext);
        commonNavigator.setAdjustMode(true);
        magicIndicator.setNavigator(commonNavigator);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return items == null ? 0 : items.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(items[index]);
                simplePagerTitleView
                        .setNormalColor(mContext.getResources().getColor(R.color.c_1B1B1B));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView
                        .setSelectedColor(mContext.getResources().getColor(R.color.c_1B1B1B));
                simplePagerTitleView.setGravity(Gravity.CENTER);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commonNavigator.onPageSelected(index);
                        commonNavigator.notifyDataSetChanged();
                        commonNavigatorTop.onPageSelected(index);
                        commonNavigatorTop.notifyDataSetChanged();
                        viewpagerDormitory.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(mContext.getResources().getColor(R.color.c_FF3333));
                indicator.setLineHeight(mContext.getResources().getDimension(R.dimen.H4));
                return indicator;
            }
        });

        commonNavigatorTop.setAdjustMode(true);
        magicIndicatorTop.setNavigator(commonNavigatorTop);
        commonNavigatorTop.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return items == null ? 0 : items.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(items[index]);
                simplePagerTitleView
                        .setNormalColor(mContext.getResources().getColor(R.color.c_1B1B1B));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView
                        .setSelectedColor(mContext.getResources().getColor(R.color.c_1B1B1B));
                simplePagerTitleView.setGravity(Gravity.CENTER);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commonNavigator.onPageSelected(index);
                        commonNavigator.notifyDataSetChanged();
                        commonNavigatorTop.onPageSelected(index);
                        commonNavigatorTop.notifyDataSetChanged();
                        viewpagerDormitory.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(mContext.getResources().getColor(R.color.c_FF3333));
                indicator.setLineHeight(mContext.getResources().getDimension(R.dimen.H4));
                return indicator;
            }
        });

        viewpagerDormitory.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
                magicIndicatorTop.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
                magicIndicatorTop.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
                magicIndicatorTop.onPageScrollStateChanged(state);
            }
        });
        ViewPagerHelper.bind(magicIndicator,viewpagerDormitory);
        ViewPagerHelper.bind(magicIndicatorTop,viewpagerDormitory);
    }

    /**
     * 展示选择的店铺信息
     * @param shoperBean
     */
    private void showShop(DormitoryBean.ShoperBean shoperBean) {
        //选择店铺
        imgChooseShop.setVisibility(dormitoryBean.getShoper().size() > 1 ? View.VISIBLE : View.GONE);
        tvShopName.setText(shoperBean.getName());
        EventBus.getDefault().post(shoperBean);
        Glide.with(mContext)
                .load(shoperBean.getAvatar())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(imgShopAvatar);
        tvShopNotice.setText(shoperBean.getNotice() + "/n" + shoperBean.getNotice());
    }

    /**
     * 店铺选择
     * @param dorm  宿舍默认地址
     * @param isChoose  是否为主动选择切换店铺
     */
    private void showShopList(DormitoryBean dorm,boolean isChoose) {
        //主动选择店铺，或者进入宿舍小店根据地址是否切换自主弹出
        if((!dorm.getDorm().equals(BaseApplication.getDormitory()) && dorm.getShoper().size() > 1)
                || isChoose){
            BaseApplication.setDormitory(dorm.getDorm());
            new BaseWindowDialog(mContext, R.layout.choose_shop) {
                @Override
                public void convert(DialogViewHolder holder) {
                    RecyclerView recycleShop = holder.getView(R.id.recycle_shop);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                    recycleShop.setLayoutManager(layoutManager);
                    ChooseShopAdapter chooseShopAdapter
                            = new ChooseShopAdapter(mContext, dorm.getShoper(),shopIndex);
                    chooseShopAdapter.setClick(new ChooseShopAdapter.OnShopSelectClick() {
                        @Override
                        public void onChoose(int position) {
                            chooseShopAdapter.setPosition(position);
                            chooseShopAdapter.notifyDataSetChanged();
                        }
                    });
                    recycleShop.setAdapter(chooseShopAdapter);
                    //确认
                    holder.getView(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateShopDate(chooseShopAdapter.getPosition());
                            dismiss();
                        }
                    });

                    //取消
                    holder.getView(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dismiss();
                        }
                    });
                }
            }.backgroundLight(0.2)
                    .fromBottom()
                    .fullWidth()
                    .showDialog()
                    .setCancelAble(true)
                    .setCanceledOnTouchOutside(true);
        }else{
            return;
        }
    }

    /**
     * 选择更换了店铺更新店铺数据
     * @param position
     */
    private void updateShopDate(int position) {
        if(position == shopIndex){
            return;
        }
        EventBus.getDefault().post(dormitoryBean.getShoper().get(position));
        shopIndex = position ;
        showShop(dormitoryBean.getShoper().get(position));
    }

    @OnClick({R.id.img_back,R.id.tv_location,R.id.img_chooseShop,R.id.tv_shopName})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.img_back :    //返回
                    finish();
                break;

            case R.id.tv_location :     //地址选择
                    Intent addrIntent = new Intent(mContext, MyAddrActivity.class);
                    startActivity(addrIntent);
                break;

            case R.id.img_chooseShop :  //选择店铺
                    showShopList(dormitoryBean,true);
                break;

            case R.id.tv_shopName :

                break;
        }
    }

    /**
     * 无宿舍地址
     */
    @Override
    public void hasNoDormitory() {
        StatusBarUtil.setStatusBarColor(mContext,mContext
                .getResources().getColor(R.color.c_FFFFFF));
        statusDormitory.showEmptyContent();

        //选择宿舍地址
        statusDormitory.findViewById(R.id.tv_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buildIntent = new Intent(mContext,MyAddrActivity.class);
                startActivity(buildIntent);
            }
        });


        //创建宿舍地址
        statusDormitory.findViewById(R.id.tv_goBuild).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buildIntent = new Intent(mContext, AddDormitoryActivity.class);
                buildIntent.putExtra("isDefalut",true);
                startActivity(buildIntent);
            }
        });

        //返回
        statusDormitory.findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 无宿舍小店
     * @param dorm
     */
    @Override
    public void hasNoShop(String dorm) {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_FFFFFF));
        ((TextView)statusDormitory.findViewById(R.id.tv_locationNoShop)).setText("dorm");
        //返回
        statusDormitory.findViewById(R.id.img_backNoShop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //申请
        statusDormitory.findViewById(R.id.tv_goBuild).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buildIntent = new Intent(mContext, ApplyForOwnerActivity.class);
                startActivity(buildIntent);
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(AddressEvent event){
        getPresenter().getDormitor();
    }
}
