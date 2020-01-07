package com.zj.wz.wbyx.baseandroid.dagger.component;


import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.config.UploadApi;
import com.zj.wz.wbyx.baseandroid.dagger.module.MyAppModule;
import com.zj.wz.wbyx.baseandroid.dagger.scope.MyAppScope;

import dagger.Component;

@MyAppScope
@Component(dependencies = AppComponent.class, modules = MyAppModule.class
        /*modules = {MyAppModule.class, AppModule.class}*/)
public interface MyAppComponent {

    MyApi retrofitMyApiHelper();


    UploadApi retrofitUploadApiHelper();

}
