package com.zj.wz.wbyx.baseandroid.retrofit.helper;

import android.content.Context;
import android.os.Build;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.zj.wz.wbyx.BuildConfig;
import com.zj.wz.wbyx.baseandroid.dagger.qualifier.ApplicationContext;
import com.zj.wz.wbyx.baseandroid.dagger.qualifier.OkhttpCache;
import com.zj.wz.wbyx.baseandroid.mvp.base.BaseApplication;
import com.zj.wz.wbyx.baseandroid.okhttp.OkhttpCacheUtils;
import com.zj.wz.wbyx.baseandroid.oklog.OkLogInterceptor;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * FileName: OkhttpHelper
 * Author: 曹伟
 * Date: 2019/9/16 17:33
 * Description:
 */

@Singleton
public class OkhttpHelper {

    private static ClearableCookieJar sCookieJar;
    private OkHttpClient mHttpClient = null;

    @Inject
    public OkhttpHelper(@ApplicationContext Context context, @OkhttpCache File cacheFile) {
        String userAgent = formatUserAgent();
        UserAgentInterceptor userAgentInterceptor = new UserAgentInterceptor(userAgent);

        sCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(AndroidUtils
                .getContext()));
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(OkhttpCacheUtils.createCache(context, cacheFile.getAbsolutePath()))
                .cookieJar(sCookieJar)
                .addNetworkInterceptor(userAgentInterceptor);

        if (BaseApplication.DEBUGMODE) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        OkLogInterceptor okLogInterceptor = new OkLogInterceptor();
        builder.addInterceptor(okLogInterceptor);

        mHttpClient = builder.build();
    }

    public class UserAgentInterceptor implements Interceptor {
        private static final String USER_AGENT_HEADER_NAME = "User-Agent";
        private final String userAgentHeaderValue;

        public UserAgentInterceptor(String userAgentHeaderValue) {
            this.userAgentHeaderValue = userAgentHeaderValue;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request originalRequest = chain.request();
            final Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader(USER_AGENT_HEADER_NAME)
                    .addHeader(USER_AGENT_HEADER_NAME, userAgentHeaderValue)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }

    public static String formatUserAgent() {
        StringBuilder result = new StringBuilder();
        result.append("Android");
        result.append(";");
        result.append(Build.VERSION.RELEASE);
        result.append(";");
        result.append(Build.VERSION.SDK_INT);
        result.append(";");
        result.append(BuildConfig.VERSION_NAME);
        result.append(";");
        result.append(BuildConfig.VERSION_CODE);
        result.append(";");
        result.append(NetUtils.getNetworkType().name());
        result.append(";");
        result.append(Build.MODEL);
        return result.toString().replaceAll("\\s*", "");
    }

    public static List<Cookie> getHttpCookieList(String httpUrl) {
        return sCookieJar.loadForRequest(HttpUrl.parse(httpUrl));
    }

    public static String getHttpCookieString(String httpUrl) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Cookie> cookieList = getHttpCookieList(httpUrl);
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
