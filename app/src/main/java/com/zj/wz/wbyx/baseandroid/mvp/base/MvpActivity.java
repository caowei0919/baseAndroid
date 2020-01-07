package com.zj.wz.wbyx.baseandroid.mvp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

/**
 * FileName: MvpActivity
 * Author: 曹伟
 * Date: 2019/9/12 17:14
 * Description:
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends RxAppCompatActivity implements MvpView, MvpDelegateCallback<V, P> {
    protected ActivityMvpDelegate mvpDelegate;
    @Inject
    protected P presenter;

    @NonNull
    public abstract P createPresenter();

    @NonNull
    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(@NonNull P presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public V getMvpView() {
        return (V)this;
    }

    @NonNull
    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (this.mvpDelegate == null) {
            this.mvpDelegate = new ActivityMvpDelegateImpl(this, this);
        }
        return mvpDelegate;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpDelegate().onPostCreate(savedInstanceState);
    }

    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }
}
