package com.zj.wz.wbyx.baseandroid.dagger.component;

import android.content.Context;

import com.zj.wz.wbyx.baseandroid.dagger.module.AppModule;
import com.zj.wz.wbyx.baseandroid.dagger.qualifier.ApplicationContext;
import com.zj.wz.wbyx.baseandroid.dagger.qualifier.GlideCache;
import com.zj.wz.wbyx.baseandroid.dagger.qualifier.OkhttpCache;
import com.zj.wz.wbyx.baseandroid.retrofit.helper.OkhttpHelper;
import com.zj.wz.wbyx.baseandroid.retrofit.helper.PreferencesHelper;
import com.zj.wz.wbyx.baseandroid.retrofit.helper.RetrofitHelper;

import java.io.File;

import javax.inject.Singleton;

import dagger.Component;

/**
 * FileName: AppComponent
 * Author: 曹伟
 * Date: 2019/9/16 16:03
 * Description:
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    @ApplicationContext
    Context getContext();

    File getCacheDir();

    @OkhttpCache
    File getOkhttpCacheDir();

    @GlideCache
    File getGlideCacheDir();

    OkhttpHelper okhttpHelper();

    RetrofitHelper RetrofitHelper();

    PreferencesHelper preferencesHelper();

}