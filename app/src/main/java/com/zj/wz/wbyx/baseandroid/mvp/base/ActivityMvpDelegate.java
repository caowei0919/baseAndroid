package com.zj.wz.wbyx.baseandroid.mvp.base;

import android.os.Bundle;


/**
 * FileName: ActivityMvpDelegate
 * Author: 曹伟
 * Date: 2019/9/12 17:17
 * Description:
 */

public interface ActivityMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    /**
     * This method must be called from {@link Activity#onCreate(Bundle)}.
     * This method internally creates the presenter and attaches the view to it.
     */
    void onCreate(Bundle bundle);

    /**
     * This method must be called from {@link Activity#onContentChanged()}
     */
    void onContentChanged();

    /**
     * This method must be called from {@link Activity#onStart()}
     */
    void onStart();

    /**
     * This method must be called from {@link Activity#onPostCreate(Bundle)}
     */
    void onPostCreate(Bundle savedInstanceState);

    /**
     * This method must be called from {@link Activity#onResume()}
     */
    void onResume();

    /**
     * This method must be called from {@link Activity#onPause()}
     */
    void onPause();

    /**
     * This method must be called from {@link Activity#onSaveInstanceState(Bundle)}
     */
    void onSaveInstanceState(Bundle outState);

    /**
     * This method must be called from {@link Activity#onStop()}
     */
    void onStop();

    /**
     * This method must be called from {@link Activity#onRestart()}
     */
    void onRestart();

    /**
     * This method must be called from {@link Activity#onDestroy()}}.
     * This method internally detaches the view from presenter
     */
    void onDestroy();

}
