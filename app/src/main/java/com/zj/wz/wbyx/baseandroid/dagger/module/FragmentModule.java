package com.zj.wz.wbyx.baseandroid.dagger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.zj.wz.wbyx.baseandroid.dagger.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * FileName: FragmentModule
 * Author: 曹伟
 * Date: 2019/9/16 18:51
 * Description:
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
