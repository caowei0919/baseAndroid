package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.baseandroid.view.TitleNavigator;
import com.zj.wz.wbyx.wbyxAndroid.adapter.CommonFragmentAdapter;
import com.zj.wz.wbyx.wbyxAndroid.fragment.AfterSalesOrderFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.AllOrderFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.EvaluateOrderFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.PaymentOrderFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.ReceiveOrderFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.SendOrderFragment;
import com.zj.wz.wbyx.wbyxAndroid.presenter.OrderPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.OrderView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: OrderActivity
 * Author: 曹伟
 * Date: 2019/10/22 11:04
 * Description:订单页面
 */

public class OrderActivity extends BaseMvpActivity<OrderView, OrderPresenter>
        implements TitleNavigator.OnTabSelectedListener{
    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator ;         //指示器
    @BindView(R.id.viewPage_order)
    ViewPager viewPageOrder ;

    private TitleNavigator titleNavigator ;
    private String[] items ;
    private List<Fragment> fragments = new ArrayList<>();
    private CommonFragmentAdapter adapter ;

    private int index ;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        index = getIntent().getIntExtra("index",0);
        items = mContext.getResources().getStringArray(R.array.order_title);
        titleNavigator = new TitleNavigator(mContext);
        titleNavigator.setOnTabSelectedListener(this);
        magicIndicator.setNavigator(titleNavigator);
        fragments.add(new AllOrderFragment());
        fragments.add(new PaymentOrderFragment());
        fragments.add(new SendOrderFragment());
        fragments.add(new ReceiveOrderFragment());
        fragments.add(new EvaluateOrderFragment());
        fragments.add(new AfterSalesOrderFragment());
        adapter = new CommonFragmentAdapter(getSupportFragmentManager(),viewPageOrder,fragments);
        viewPageOrder.setAdapter(adapter);
        adapter.setOnExtraPageChangeListener(new CommonFragmentAdapter.OnExtraPageChangeListener() {
            @Override
            public void onExtraPageSelected(int i) {

            }
        });
        viewPageOrder.setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);
        viewPageOrder.setCurrentItem(index);
        titleNavigator.onPageSelected(index);
        tvTitle.setText(items[index]);
        viewPageOrder.setOffscreenPageLimit(4);
        viewPageOrder.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                titleNavigator.onPageSelected(arg0);
                tvTitle.setText(items[arg0]);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                titleNavigator.onPageScrolled(arg0, arg1, arg2);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                titleNavigator.onPageScrollStateChanged(arg0);
            }
        });
        ViewPagerHelper.bind(magicIndicator,viewPageOrder);
    }

    @OnClick({R.id.linear_back})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back :     //返回
                    finish();
                break;
        }
    }

    /**
     * 标题选中
     * @param position
     */
    @Override
    public void onTabSelected(int position) {
        viewPageOrder.setCurrentItem(position);
        tvTitle.setText(items[position]);
    }
}
