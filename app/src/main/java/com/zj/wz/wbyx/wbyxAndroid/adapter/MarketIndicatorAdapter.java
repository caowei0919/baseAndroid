package com.zj.wz.wbyx.wbyxAndroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemBean;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: MarketIndicatorAdapter
 * Author: 曹伟
 * Date: 2019/11/11 21:30
 * Description:窝边超市菜单指示器
 */

public class MarketIndicatorAdapter extends CommonNavigatorAdapter{
    private List<MenuItemBean> menuItemBeans = new ArrayList<>();
    private Context mContext ;
    private onIndicatorClick click ;

    public MarketIndicatorAdapter(List<MenuItemBean> menuItemBeans, Context mContext
            ,onIndicatorClick click) {
        this.menuItemBeans = menuItemBeans;
        this.mContext = mContext;
        this.click = click ;
    }

    @Override
    public int getCount() {
        return menuItemBeans!= null ? menuItemBeans.size() : 0;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int index) {
        CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_market_indicator,null);
        ImageView imgMenuPicture = view.findViewById(R.id.img_menuPicture);
        Glide.with(mContext)
                .load(menuItemBeans.get(index).getImage())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(imgMenuPicture);
        TextView tvMenuName = view.findViewById(R.id.tv_menuName);
        tvMenuName.setText(menuItemBeans.get(index).getName());
        commonPagerTitleView.setContentView(view);

        commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

            @Override
            public void onSelected(int index, int totalCount) {

            }

            @Override
            public void onDeselected(int index, int totalCount) {

            }

            @Override
            public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                tvMenuName.setScaleX(1.0f + (0.8f - 1.0f) * leavePercent);
                tvMenuName.setScaleY(1.0f + (0.8f - 1.0f) * leavePercent);
            }

            @Override
            public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                tvMenuName.setScaleX(0.8f + (1.0f - 0.8f) * enterPercent);
                tvMenuName.setScaleY(0.8f + (1.0f - 0.8f) * enterPercent);
            }
        });

        commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onIndicatorClick(index);
            }
        });
        return commonPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setColors(mContext.getResources().getColor(R.color.c_FF3333));
        indicator.setLineHeight(mContext.getResources().getDimension(R.dimen.H6));
        return indicator;
    }

    /**
     * 对外暴露的点击事件
     */
    public interface onIndicatorClick{
        void onIndicatorClick(int index);
    }
}
