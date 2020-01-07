package com.zj.wz.wbyx.wbyxAndroid.adapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemBean;
import com.zj.wz.wbyx.wbyxAndroid.fragment.MarketFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * FileName: MarketMenuPageAdapter
 * Author: 曹伟
 * Date: 2019/11/12 10:21
 * Description:
 */

public class RecommendMenuPageAdapter extends PagerAdapter{
    private List<MenuItemBean> menuItemBeans = new ArrayList<>();
    private List<MarketFragment> fragments = new ArrayList<>();
    private FragmentManager fragmentManager;
    private MarketFragment.OnAddToShopInFragmentClick click ;

    public RecommendMenuPageAdapter(FragmentManager fragmentManager
            , List<MenuItemBean> menuItemBeans
            , List<MarketFragment> fragments, MarketFragment.OnAddToShopInFragmentClick click) {
        this.menuItemBeans = menuItemBeans;
        this.fragmentManager = fragmentManager;
        this.click = click;
        this.fragments = fragments ;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView(fragments.get(position).getView()); // 移出viewpager两边之外的page布局
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MarketFragment fragment = MarketFragment.newInstance(menuItemBeans.get(position), Constant.TYPE_GOODS_RECOMMEND);
        if(!fragment.isAdded()){ // 如果fragment还没有added
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(fragment,menuItemBeans.get(position).getId());
            ft.commit();
            /**
             * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
             * 会在进程的主线程中，用异步的方式来执行。
             * 如果想要立即执行这个等待中的操作，就要调用这个方法（只能在主线程中调用）。
             * 要注意的是，所有的回调和相关的行为都会在这个调用中被执行完成，因此要仔细确认这个方法的调用位置。
             */
            fragmentManager.executePendingTransactions();
        }

        if(fragment.getView().getParent() == null){
            container.addView(fragment.getView()); // 为viewpager增加布局
        }
        fragment.setClick(click);
        return fragment.getView();
    }
}
