package com.zj.wz.wbyx.baseandroid.mvp.base;

import android.support.annotation.UiThread;


/**
 * FileName: MvpPresenter
 * Author: 曹伟
 * Date: 2019/9/12 17:16
 * Description:
 */

public interface MvpPresenter<V extends MvpView> {
    /**
     * Set or attach the view to this presenter
     */
    @UiThread
    void attachView(V view);

    /**
     * Will be called if the view has been destroyed. Typically this method will be
     * invoked from <code>Activity.detachView()</code> or <code>Fragment.onDestroyView()</code>
     */
    @UiThread
    void detachView();
}

