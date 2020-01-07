package com.zj.wz.wbyx.baseandroid.mvp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;


import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.dagger.component.ActivityComponent;
import com.zj.wz.wbyx.baseandroid.dagger.component.DaggerActivityComponent;
import com.zj.wz.wbyx.baseandroid.dagger.component.MyAppComponent;
import com.zj.wz.wbyx.baseandroid.dagger.module.ActivityModule;
import com.zj.wz.wbyx.baseandroid.mvp.base.BaseApplication;
import com.zj.wz.wbyx.baseandroid.mvp.base.BaseDoubleClickExitHelper;
import com.zj.wz.wbyx.baseandroid.mvp.base.MvpActivity;
import com.zj.wz.wbyx.baseandroid.mvp.base.MvpPresenter;
import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.Application.MyApplication;
import com.zj.wz.wbyx.wbyxAndroid.activity.LoginActivity;
import com.zj.wz.wbyx.wbyxAndroid.dialog.LoadingDialog;
import com.zj.wz.wbyx.wbyxAndroid.event.LoginOutEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.TokenEvent;
import com.zj.wz.wbyx.wbyxAndroid.utils.DialogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.zj.wz.wbyx.baseandroid.config.Constant.APP_ID;


public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpActivity<V, P> implements MvpView{

    private final String TAG = this.getClass().getSimpleName() ;
    private Unbinder mUnbinder;
    public static BaseDoubleClickExitHelper mDoubleClickExitHelper;
    private ActivityComponent mActivityComponent;
    private WeakReference<Activity> softActivity ;
    protected BaseMvpActivity mContext ;
    protected LoadingDialog mLoadingDialog ;

    /**
     * Instantiate a presenter instance
     * @return The {@link MvpPresenter} for this view
     */
    @NonNull
    public P createPresenter() {
        setupComponent(MyApplication.getMyAppComponent());
        initInject();
        return presenter;
    }

    protected void setupComponent(MyAppComponent applicationComponent) {
        mActivityComponent = DaggerActivityComponent.builder()
                .myAppComponent(applicationComponent)
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        setup(savedInstanceState);
        softActivity = new WeakReference<Activity>(this);
        BaseApplication.getAppManager().addActivity(softActivity);
        mDoubleClickExitHelper = new BaseDoubleClickExitHelper(this);
        PLog.e(TAG);
    }

    private void setup(Bundle savedInstanceState) {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this,mContext.getResources()
                    .getColor(R.color.c_550000));
        }
        setupView();
        setupData(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void initInject();

    protected abstract void setupView();

    protected abstract void setupData(Bundle savedInstanceState);

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
        BaseApplication.getAppManager().removeActivity(softActivity);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode,event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LoginOutEvent event){
        BaseApplication.getAppManager().finishAllActivity();
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        Constant.louginOut();
    }

    @Override
    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog();
        }

        if (!mLoadingDialog.isVisible()) {
            mLoadingDialog.show(getSupportFragmentManager(), "loadingdialog");
        }
    }

    @Override
    public void closeLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismissAllowingStateLoss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(TokenEvent event){
        PLog.e("TokenEvent");
        DialogUtils.ShouSureDialog(mContext, mContext.getResources().getString(R.string.login_again)
                , mContext.getResources()
                        .getString(R.string.your_account_is_login_other_phone_and_please_to_login)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BaseApplication.getAppManager().finishAllActivity();
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        Constant.louginOut();
                    }
                });
    }
}
