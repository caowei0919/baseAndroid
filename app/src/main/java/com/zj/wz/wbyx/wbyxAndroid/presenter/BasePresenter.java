package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.mvp.base.MvpBasePresenter;
import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * FileName: BasePresenter
 * Author: 曹伟
 * Date: 2019/9/16 17:05
 * Description:
 */
public class BasePresenter<V extends MvpView> extends MvpBasePresenter<V> {

    protected CompositeDisposable mCompositeDisposable;
    protected Completable retryCompletable;

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        unSubscribe();
    }

    public void errorRetry() {
        if (retryCompletable != null) {
            addSubscribe(retryCompletable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());

        }
    }

    public void setErrorRetry(final Action action) {
        if (action == null) {
            this.retryCompletable = null;
        } else {
            this.retryCompletable = Completable.fromAction(action);
        }
    }

}
