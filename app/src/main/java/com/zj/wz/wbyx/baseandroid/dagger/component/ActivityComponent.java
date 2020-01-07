package com.zj.wz.wbyx.baseandroid.dagger.component;

import android.app.Activity;


import com.zj.wz.wbyx.baseandroid.dagger.module.ActivityModule;
import com.zj.wz.wbyx.baseandroid.dagger.scope.ActivityScope;
import com.zj.wz.wbyx.wbyxAndroid.activity.AddDormitoryActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.AddNotDormitoryActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.ApplyForOwnerActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.AppraiseGoodActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.AppraiseShopActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.BindPhoneActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.BuyMembershipActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.CategoriesActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.ChooseRefereesActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.ChooseSchoolActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.ConfirmRefundDetailsActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.CustomerActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.DormitoryActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.FeedBackActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.GoodsDetailActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.GroupBookingActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.InviteActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.LoginActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.MainActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.MarketActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.MyAddrActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.MyGroupActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.NewsSettingActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.OrderActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.OrderDetailActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.OtherWebActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.RecommendActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.RefundDetailsActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.SearchActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.ServiceActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.SettingActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.SharePoliteActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.StartActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.SubjectsActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.TestActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.UpdateVersionActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.UserInfoActivity;
import com.zj.wz.wbyx.wxapi.WXEntryActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = MyAppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(StartActivity startActivity);

    void inject(LoginActivity loginActivity);

    void inject(UserInfoActivity userInfoActivity);

    void inject(ApplyForOwnerActivity applyForOwnerActivity);

    void inject(InviteActivity inviteActivity);

    void inject(ChooseSchoolActivity chooseSchoolActivity);

    void inject(ChooseRefereesActivity chooseRefereesActivity);

    void inject(MyGroupActivity myGroupActivity);

    void inject(SettingActivity settingActivity);

    void inject(UpdateVersionActivity updateVersionActivity);

    void inject(NewsSettingActivity newsSettingActivity);

    void inject(ServiceActivity serviceActivity);

    void inject(FeedBackActivity feedBackActivity);

    void inject(SharePoliteActivity sharePoliteActivity);

    void inject(MyAddrActivity myAddrActivity);

    void inject(AddDormitoryActivity addDormitoryActivity);

    void inject(AddNotDormitoryActivity addNotDormitoryActivity);

    void inject(OrderActivity orderActivity);

    void inject(TestActivity testActivity);

    void inject(CategoriesActivity categoriesActivity);

    void inject(GoodsDetailActivity goodsDetailActivity);

    void inject(MarketActivity marketActivity);

    void inject(RecommendActivity recommendActivity);

    void inject(SubjectsActivity subjectsActivity);

    void inject(DormitoryActivity dormitoryActivity);

    void inject(GroupBookingActivity groupBookingActivity);

    void inject(OtherWebActivity otherWebActivity);

    void inject(CustomerActivity customerActivity);

    void inject(SearchActivity searchActivity);

    void inject(BuyMembershipActivity buyMembershipActivity);

    void inject(OrderDetailActivity orderDetailActivity);

    void inject(RefundDetailsActivity refundDetailsActivity);

    void inject(ConfirmRefundDetailsActivity confirmRefundDetailsActivity);

    void inject(AppraiseShopActivity appraiseShopActivity);

    void inject(AppraiseGoodActivity appraiseGoodActivity);

    void inject(BindPhoneActivity bindPhoneActivity);
}
