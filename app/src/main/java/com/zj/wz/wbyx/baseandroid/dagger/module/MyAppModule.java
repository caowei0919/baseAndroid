package com.zj.wz.wbyx.baseandroid.dagger.module;


import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.config.UploadApi;
import com.zj.wz.wbyx.baseandroid.dagger.qualifier.MyUrl;
import com.zj.wz.wbyx.baseandroid.dagger.qualifier.UploadUrl;
import com.zj.wz.wbyx.baseandroid.dagger.scope.MyAppScope;
import com.zj.wz.wbyx.baseandroid.retrofit.helper.MyOkhttpHelper;
import com.zj.wz.wbyx.baseandroid.retrofit.helper.RetrofitHelper;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class MyAppModule {

    @MyAppScope
    @Provides
    @MyUrl
    Retrofit provideMyUrlRetrofit(MyOkhttpHelper okhttpHelper, RetrofitHelper retrofitHelper) {
        return createMyApiRetrofit(okhttpHelper, retrofitHelper, Api.PHP_BASE_URL);
    }

    @MyAppScope
    @Provides
    @UploadUrl
    Retrofit provideUploadUrlRetrofit(MyOkhttpHelper okhttpHelper, RetrofitHelper retrofitHelper) {
        return createUploadApiRetrofit(okhttpHelper, retrofitHelper, Api.PHP_BASE_URL);
    }

    private Retrofit createMyApiRetrofit(MyOkhttpHelper okhttpHelper, RetrofitHelper retrofitHelper, String url) {
        Retrofit.Builder builder = retrofitHelper.getRetrofit().newBuilder();
        OkHttpClient.Builder client = okhttpHelper.getHttpClient().newBuilder();
        return builder.baseUrl(url).client(client.build()).build();
    }


    private Retrofit createUploadApiRetrofit(MyOkhttpHelper okhttpHelper, RetrofitHelper retrofitHelper, String upLoadingUrl) {
        Retrofit.Builder builder = retrofitHelper.getRetrofit().newBuilder();
        OkHttpClient.Builder client = okhttpHelper.getHttpClient().newBuilder();
        return builder.baseUrl(upLoadingUrl).client(client.build()).build();
    }

    @MyAppScope
    @Provides
    MyApi provideMyApi(@MyUrl Retrofit retrofit) {
        return retrofit.create(MyApi.class);
    }


    @MyAppScope
    @Provides
    UploadApi provideUploadApi(@UploadUrl Retrofit retrofit) {
        return retrofit.create(UploadApi.class);
    }
}
