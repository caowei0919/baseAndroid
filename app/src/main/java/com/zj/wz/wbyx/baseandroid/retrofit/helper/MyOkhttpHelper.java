package com.zj.wz.wbyx.baseandroid.retrofit.helper;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.zj.wz.wbyx.BuildConfig;
import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.dagger.qualifier.ApplicationContext;
import com.zj.wz.wbyx.baseandroid.dagger.qualifier.OkhttpCache;
import com.zj.wz.wbyx.baseandroid.dagger.scope.MyAppScope;
import com.zj.wz.wbyx.baseandroid.okhttp.OkhttpCacheUtils;
import com.zj.wz.wbyx.baseandroid.okhttp.RequestMethod;
import com.zj.wz.wbyx.baseandroid.okhttp.SSLSocketClient;
import com.zj.wz.wbyx.baseandroid.oklog.OkLogInterceptor;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * FileName: MyOkhttpHelper
 * Author: 曹伟
 * Date: 2019/9/16 16:10
 * Description:
 */

@MyAppScope
public class MyOkhttpHelper {

    private static ClearableCookieJar sCookieJar;
    private OkHttpClient mHttpClient = null;

    @Inject
    public MyOkhttpHelper(@ApplicationContext Context context, @OkhttpCache File cacheFile) {

        sCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(AndroidUtils
                .getContext()));
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(OkhttpCacheUtils.createCache(context, cacheFile.getAbsolutePath()))
                .cookieJar(sCookieJar)
                .connectTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//配置
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//配置
//                .addInterceptor(userAgentInterceptor)
                .addInterceptor(headerInterceptor);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        OkLogInterceptor okLogInterceptor = new OkLogInterceptor();
        builder.addInterceptor(okLogInterceptor);

        mHttpClient = builder.build();
    }


    private static Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            Request.Builder newRequestBuilder = originalRequest.newBuilder();
            newRequestBuilder.addHeader(Api.HEADER.HEADER_TOKEN, Constant.getUserToken());
            newRequestBuilder.addHeader(Api.HEADER.HEADER_SIGN, "222");//sign
            newRequestBuilder.addHeader(Api.HEADER.HEADER_OS, Api.HEADER.VALUE_OS);  //设备类型
            newRequestBuilder.addHeader(Api.HEADER.HEADER_VERSION, BuildConfig.VERSION_NAME);//app版本
            newRequestBuilder.addHeader(Api.HEADER.SYSTEMVERSION, String.valueOf(android.os.Build.VERSION.RELEASE));//设备系统版本
            newRequestBuilder.addHeader(Api.HEADER.HEADER_DEVICEID, AndroidUtils.getDeviceId());//设备唯一编号

            StringBuilder postString = new StringBuilder();
            if (RequestMethod.supportBody(originalRequest.method())) {
                if (originalRequest.body() instanceof FormBody) {
                    FormBody formBody = (FormBody) originalRequest.body();
                    for (int i = 0; i < formBody.size(); i++) {
                        postString.append(postString.length() > 0 ? "&" : "")
                                .append(formBody.encodedName(i))
                                .append("=")
                                .append(formBody.encodedValue(i) == null ? "" : formBody.encodedValue(i));
                    }
                }
            }


            Response response = chain.proceed(newRequestBuilder.build());
                MediaType mediaType = response.body().contentType();
                try {
                    String responseContent = response.body().string();
                    return response.newBuilder()
                            .body(ResponseBody.create(mediaType, responseContent))
                            .build();
                } catch (Exception e) {
                    return response.newBuilder()
                            .body(ResponseBody.create(mediaType, ""))
                            .build();
                }
//            }
        }
    };

    public static List<Cookie> getHttpCookieList() {
        return sCookieJar.loadForRequest(HttpUrl.parse(Api.PHP_BASE_URL));
    }

    public static String getHttpCookieString() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Cookie> cookieList = getHttpCookieList();
        for (Cookie cookie : cookieList) {
            stringBuilder.append(cookie.toString()).append(";");
        }
        if (stringBuilder.length() > 0) {
            int last = stringBuilder.lastIndexOf(";");
            if (stringBuilder.length() - 1 == last) {
                stringBuilder.deleteCharAt(last);
            }
        }

        return stringBuilder.toString();
    }

    public static void clearCookie() {
        sCookieJar.clear();
    }

    public OkHttpClient getHttpClient() {
        return mHttpClient;
    }

}

