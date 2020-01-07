package com.zj.wz.wbyx.wbyxAndroid.Application;

import android.content.Context;
import com.zj.wz.wbyx.BuildConfig;
import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.dagger.component.DaggerMyAppComponent;
import com.zj.wz.wbyx.baseandroid.dagger.component.MyAppComponent;
import com.zj.wz.wbyx.baseandroid.dagger.module.MyAppModule;
import com.zj.wz.wbyx.baseandroid.mvp.base.BaseApplication;
import com.zj.wz.wbyx.baseandroid.utils.addr.CountyOptionsUtils;
import com.zj.wz.wbyx.wbyxAndroid.Gaode.GaoDeMap;


public class MyApplication extends BaseApplication {

    private static MyAppComponent mMyAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setDebugMode(BuildConfig.DEBUG);
        initMyAppInject();
        Api.setEnviroment(BuildConfig.APP_ENV);
        GaoDeMap.getInstance().init(this);
        CountyOptionsUtils.getInstance().init(this);
    }


    public void initMyAppInject() {
        mMyAppComponent = DaggerMyAppComponent.builder().
                appComponent(getAppComponent()).myAppModule(new MyAppModule()).build();
    }

    public static MyAppComponent getMyAppComponent() {
        return mMyAppComponent;
    }

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
