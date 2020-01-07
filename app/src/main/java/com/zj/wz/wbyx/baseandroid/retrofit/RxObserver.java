package com.zj.wz.wbyx.baseandroid.retrofit;

import android.net.ParseException;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.event.TokenEvent;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public class RxObserver<T> implements Observer<T>{
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        PLog.e("onError");
        String msg = "";
        if (e instanceof ConnectException) {
            msg = "网络不可用";
        } else if (e instanceof UnknownHostException) {
            msg = "网络不可用";
        } else if (e instanceof SocketTimeoutException) {
            msg = "请求网络超时";
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            msg = convertStatusCode(httpException);
        } else if (e instanceof JsonParseException || e instanceof ParseException || e instanceof JSONException || e instanceof JsonIOException) {
            msg = "数据解析错误";
        }

        if (!msg.isEmpty()) {
            ToastUtils.showShortToastSafe(msg);
        }
    }

    @Override
    public void onComplete() {

    }

    private String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() == 500) {
            msg = "服务器发生错误";
        } else if (httpException.code() == 404) {
            msg = "请求地址不存在";
        } else if (httpException.code() == 403) {
            msg = "请求被服务器拒绝";
        } else if (httpException.code() == 307) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }

    public static boolean checkJsonCode(BaseBean t){
        if (t instanceof BaseBean) {
            switch (t.getCode()) {
                case Api.CODE_FAILED_TOAST:    //请求码100，弹出toast
                    PLog.e("Api.CODE_FAILED_TOAST");
                    ToastUtils.showLongToast(((BaseBean) t).getMsg());
                    return false;

                case Api.CODE_TOKEN_FAILURE:   //token失效，重新登录
                    PLog.e("CODE_TOKEN_FAILURE");
                    EventBus.getDefault().post(new TokenEvent());
                    return false;

                case Api.CODE_SUCCESS:     //200请求成功
                    PLog.e("Api.CODE_SUCCESS");
                    return true;
            }
        }
        return true;
    }
}
