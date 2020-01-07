package com.zj.wz.wbyx.baseandroid.dagger.component;

import android.app.Activity;


import com.zj.wz.wbyx.baseandroid.dagger.module.FragmentModule;
import com.zj.wz.wbyx.baseandroid.dagger.scope.FragmentScope;
import com.zj.wz.wbyx.wbyxAndroid.fragment.AfterSalesOrderFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.AllOrderFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.DormitoryGoodsFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.DormitoryShopInfoFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.EvaluateOrderFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.HomeFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.HomeOfAllFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.HomeOfMarketFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.HomeOfRecommendFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.MarketFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.MemberFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.MineFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.PaymentOrderFragment;

import dagger.Component;

import com.zj.wz.wbyx.wbyxAndroid.fragment.ReceiveOrderFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.SendOrderFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.ShopCarFragment;

@FragmentScope
@Component(dependencies = MyAppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(HomeFragment homeFragment);

    void inject(MemberFragment memberFragment);

    void inject(ShopCarFragment shopCarFragment);

    void inject(MineFragment mineFragment);

    void inject(AllOrderFragment allOrderFragment);

    void inject(PaymentOrderFragment paymentOrderFragment);

    void inject(SendOrderFragment sendOrderFragment);

    void inject(ReceiveOrderFragment receiveOrderFragment);

    void inject(EvaluateOrderFragment evaluateOrderFragment);

    void inject(AfterSalesOrderFragment afterSalesOrderFragment);

    void inject(HomeOfMarketFragment homeOfMarketFragment);

    void inject(HomeOfAllFragment homeOfAllFragment);

    void inject(HomeOfRecommendFragment homeOfRecommendFragment);

    void inject(MarketFragment marketFragment);

    void inject(DormitoryGoodsFragment dormitoryGoodsFragment);

    void inject(DormitoryShopInfoFragment dormitoryShopInfoFragment);
}
