package com.zj.wz.wbyx.baseandroid.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.dagger.component.DaggerFragmentComponent;
import com.zj.wz.wbyx.baseandroid.dagger.component.FragmentComponent;
import com.zj.wz.wbyx.baseandroid.dagger.component.MyAppComponent;
import com.zj.wz.wbyx.baseandroid.dagger.module.FragmentModule;
import com.zj.wz.wbyx.baseandroid.mvp.base.BaseApplication;
import com.zj.wz.wbyx.baseandroid.mvp.base.MvpFragment;
import com.zj.wz.wbyx.baseandroid.mvp.base.MvpPresenter;
import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.Application.MyApplication;
import com.zj.wz.wbyx.wbyxAndroid.activity.LoginActivity;
import com.zj.wz.wbyx.wbyxAndroid.dialog.LoadingDialog;
import com.zj.wz.wbyx.wbyxAndroid.event.LoginOutEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpFragment<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpFragment<V, P> implements MvpView{

    protected Unbinder mUnbinder;
    protected Context mContext ;
    private FragmentComponent mFragmentComponent;
    protected LoadingDialog mLoadingDialog ;
    private boolean isDoOnCreat = false ;


    /**
     * Creates a new presenter instance,This method will be
     * called from {@link #onViewCreated(View, Bundle)}
     */
    public P createPresenter() {
        setupComponent(MyApplication.getMyAppComponent());
        initInject();
        return presenter;
    }

    protected void setupComponent(MyAppComponent applicationComponent) {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .myAppComponent(applicationComponent)
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container,false);
        mContext = getActivity() ;
        mUnbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup(savedInstanceState, view);
    }

    private void setup(Bundle savedInstanceState, View rootView) {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(getActivity(),false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(getActivity());
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(getActivity(), true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(getActivity(),mContext.getResources()
                    .getColor(R.color.c_550000));
        }
        setupView(rootView);
        setupData(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void initInject();

    protected abstract void setupView(View rootView);

    protected abstract void setupData(Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        PLog.e("onDestroy");
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
        if (BaseApplication.DEBUGMODE) {
            // use the RefWatcher to watch for fragment leaks:
            RefWatcher refWatcher = BaseApplication.getRefWatcher(getActivity());
            refWatcher.watch(this);
        }
    }

    @Override
    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog();
        }

        if (!mLoadingDialog.isVisible()) {
            mLoadingDialog.show(getChildFragmentManager(), "loadingdialog");
        }
    }

    @Override
    public void closeLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismissAllowingStateLoss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LoginOutEvent event){
        BaseApplication.getAppManager().finishAllActivity();
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        Constant.louginOut();
    }

}
