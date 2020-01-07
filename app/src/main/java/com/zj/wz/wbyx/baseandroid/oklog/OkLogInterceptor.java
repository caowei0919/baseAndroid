package com.zj.wz.wbyx.baseandroid.oklog;



import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.room.DebugInfo;
import com.zj.wz.wbyx.baseandroid.room.DebugInfoDataSource;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.utils.TimeUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * FileName: OkLogInterceptor
 * Author: 曹伟
 * Date: 2019/9/16 16:16
 * Description:
 */

public class OkLogInterceptor implements Interceptor {

    private final LogDataInterceptor logDataInterceptor;

    public OkLogInterceptor() {
        this.logDataInterceptor = new LogDataInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        BaseLogDataInterceptor.RequestLogData<Request> requestLogData = logDataInterceptor.processRequest(chain);
        LogDataBuilder logDataBuilder = requestLogData.getLogData();
        DebugInfo debugInfo = new DebugInfo();
        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(requestLogData.getRequest());
        } catch (Exception e) {
            logDataBuilder.requestFailed();
            /*------respone error--------*/
            debugInfo.setDebugtype("network");
            logDataBuilder.responseBody("<-- HTTP FAILED: " + e.getMessage());
            debugInfo.setDebuginfo(logDataBuilder.toString());
            debugInfo.setDebugtime(TimeUtils.getNowDate(Constant.DATE_YYYY_MM_DD_HH_MM_SS,0));
            DebugInfoDataSource.getInstance(AndroidUtils.getContext())
                    .insertOrUpdateDebugInfo(debugInfo);
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        logDataBuilder.responseDurationMs(tookMs);

        BaseLogDataInterceptor.ResponseLogData<Response> responseLogData = logDataInterceptor.processResponse(logDataBuilder, response);
        /*------respone success--------*/
        debugInfo.setDebugtype("network");
        debugInfo.setDebuginfo(logDataBuilder.toString());
        debugInfo.setDebugtime(TimeUtils.getNowDate(Constant.DATE_YYYY_MM_DD_HH_MM_SS,0));
        DebugInfoDataSource.getInstance(AndroidUtils.getContext())
                .insertOrUpdateDebugInfo(debugInfo);
        return responseLogData.getResponse();
    }

}
