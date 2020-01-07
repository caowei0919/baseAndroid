package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.readystatesoftware.viewbadger.BadgeView;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.wbyxAndroid.Gaode.GaoDeMap;
import com.zj.wz.wbyx.wbyxAndroid.bean.CarFootBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.UserBean;
import com.zj.wz.wbyx.wbyxAndroid.event.AddToShopCarEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.LocationResultEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.ShopCarEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.UpDateCarFootEvent;
import com.zj.wz.wbyx.wbyxAndroid.fragment.HomeFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.MemberFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.MineFragment;
import com.zj.wz.wbyx.wbyxAndroid.fragment.ShopCarFragment;
import com.zj.wz.wbyx.wbyxAndroid.presenter.MainPresenter;
import com.zj.wz.wbyx.wbyxAndroid.utils.GoodsAnimUtil;
import com.zj.wz.wbyx.wbyxAndroid.view.MainView;

/**
 * FileName: MainActivity
 * Author: 曹伟
 * Date: 2019/9/17 17:50
 * Description: 首页
 */
public class MainActivity extends BaseMvpActivity<MainView,MainPresenter> implements MainView{

    @BindView(R.id.rg_tab)
    RadioGroup rgTab ;  // 包裹底部tab
    @BindView(R.id.rb_home)
    RadioButton rbHome ;    //首页
    @BindView(R.id.rb_members)
    RadioButton rbMembers ; //会员
    @BindView(R.id.rb_shopCar)
    RadioButton rbShopCar ; //购物车
    @BindView(R.id.rb_mine)
    RadioButton rbMine ;    //我的
    @BindView(R.id.tv_foot)
    TextView tvFoot;

    private FragmentTransaction fragmentTransaction ;

    private HomeFragment homeFragment ;
    private MemberFragment memberFragment ;
    private ShopCarFragment shopCarFragment ;
    private MineFragment mineFragment ;

    private BadgeView badge;

    private long firstTime = 0;     //主页做两次连续点击返回退出应用

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        badge = new BadgeView(mContext, tvFoot);
        badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badge.setTextSize(8);
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        //标识选中tab，在页面启动的时候保持上一次选中状态
        int tagId = 0 ;
        if(null != savedInstanceState){
            tagId = savedInstanceState.getInt("rg_checked",0);
        }
        if(tagId == 0){
            showFragment(rbHome.getId());
        }else{
            showFragment(tagId);
        }
        GaoDeMap.getInstance().startMap();
        getPresenter().getUser();
        GaoDeMap.getInstance().startMap();
        getPresenter().getUser();
    }

    /**
     * 根据选中的radioButton的id展示对应的fragment
     * @param tagId
     */
    private void showFragment(int tagId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (tagId){
            case R.id.rb_home :  //首页
                rbHome.setChecked(true);
                if(null != homeFragment){
                    fragmentTransaction.show(homeFragment);
                }else{
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content,homeFragment);
                }
                fragmentTransaction.commitAllowingStateLoss();
                break;

            case R.id.rb_members :  //会员
                if(!Constant.hasLogin()){    //未登录去登录
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                    return;
                }
                rbMembers.setChecked(true);
                if(null != memberFragment){
                    fragmentTransaction.show(memberFragment);
                }else{
                    memberFragment = new MemberFragment();
                    fragmentTransaction.add(R.id.content,memberFragment);
                }
                fragmentTransaction.commitAllowingStateLoss();
                break;

            case R.id.rb_shopCar :  //购物车
                if(!Constant.hasLogin()){    //未登录去登录
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                    return;
                }
                rbShopCar.setChecked(true);
                if(null != shopCarFragment){
                    fragmentTransaction.show(shopCarFragment);
                }else{
                    shopCarFragment = new ShopCarFragment();
                    fragmentTransaction.add(R.id.content,shopCarFragment);
                }
                fragmentTransaction.commitAllowingStateLoss();
                break;

            case R.id.rb_mine :     //我的
                if(!Constant.hasLogin()){    //未登录去登录
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                    return;
                }
                rbMine.setChecked(true);
                if(null != mineFragment){
                    fragmentTransaction.show(mineFragment);
                }else {
                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.content,mineFragment);
                }
                fragmentTransaction.commitAllowingStateLoss();
                break;
        }
    }

    /**
     * 隐藏所有fragment
     * @param fragmentTransaction
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if(null != homeFragment){
            fragmentTransaction.hide(homeFragment);
        }
        if(null != memberFragment){
            fragmentTransaction.hide(memberFragment);
        }
        if(null != shopCarFragment){
            fragmentTransaction.hide(shopCarFragment);
        }
        if(null != mineFragment){
            fragmentTransaction.hide(mineFragment);
        }
    }

    @OnClick({R.id.rb_home,R.id.rb_members,R.id.rb_shopCar,R.id.rb_mine})
    public void onClick(View view){
        ((RadioButton)view).setChecked(true);
        showFragment(view.getId());
    }

    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment == null && fragment instanceof HomeFragment){
            homeFragment = (HomeFragment) fragment;
            getSupportFragmentManager().beginTransaction().hide(homeFragment).commit();
        }else if(fragment == null && fragment instanceof MemberFragment){
            memberFragment = (MemberFragment) fragment;
            getSupportFragmentManager().beginTransaction().hide(memberFragment).commit();
        }else if(fragment == null && fragment instanceof ShopCarFragment){
            shopCarFragment = (ShopCarFragment) fragment;
            getSupportFragmentManager().beginTransaction().hide(shopCarFragment).commit();
        }else if(fragment == null && fragment instanceof MineFragment){
            mineFragment = (MineFragment) fragment;
            getSupportFragmentManager().beginTransaction().hide(mineFragment).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("rg_checked",rgTab.getCheckedRadioButtonId());
        super.onSaveInstanceState(outState);
    }

    /**
     * 在首页做连续点击退出应用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                ToastUtils.showLongToast(mContext.getResources()
                        .getString(R.string.click_again_exit_app));
                firstTime = secondTime;
                return true;
            } else {
                //执行返回桌面
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 定位信息回调
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LocationResultEvent event){
        if(event.isMbResult() && !TextUtils.isEmpty(Constant.getUserToken())){
            getPresenter().getCarFoot(Constant.getMyLocation().adCode);
        }
    }

    /**
     * 获取用户信息成功
     * @param response
     */
    @Override
    public void getUserSuccess(UserBean response) {
        /**
         * 查询定位是否成功
         */
        if(!TextUtils.isEmpty(Constant.getMyLocation().adCode)){
            getPresenter().getCarFoot(Constant.getMyLocation().adCode);
        }
    }

    /**
     * 获取购物车角标成功
     * @param response
     */
    @Override
    public void getCarFootSuccess(CarFootBean response) {
        if (response.getCart_footer() == 0 && badge.isShown()){
            badge.hide();
        }else{
            if(!badge.isShown()){
                badge.show();
            }
            badge.setText(response.getCart_footer()+"");
        }
        EventBus.getDefault().post(new ShopCarEvent());
    }

    /**
     * 当有删除或者添加购物车事件
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(UpDateCarFootEvent event){
        if(Constant.hasLogin()){
            getPresenter().getCarFoot(Constant.getMyLocation().adCode);
        }
    }

    /**
     * 加入购物车成功事件
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(AddToShopCarEvent event){
        if(event.isHasAnim()){
            GoodsAnimUtil.setAnim(mContext,event.getAddShopCar(),rbShopCar);
        }
        getPresenter().getCarFoot(Constant.getMyLocation().adCode);
    }
}
