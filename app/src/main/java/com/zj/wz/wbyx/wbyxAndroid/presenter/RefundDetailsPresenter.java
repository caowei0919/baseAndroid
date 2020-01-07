package com.zj.wz.wbyx.wbyxAndroid.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ReasonBean;
import com.zj.wz.wbyx.wbyxAndroid.view.RefundDetailsView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * FileName: RefundDetailsPresenter
 * Author: 曹伟
 * Date: 2019/11/20 17:17
 * Description:申请退款P层
 */

public class RefundDetailsPresenter extends BasePresenter<RefundDetailsView> {
    MyApi api ;

    @Inject
    public RefundDetailsPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取订单详情
     * @param orderId
     */
    public void getOrderDetail(String orderId) {
        api.getOrderDetail(orderId)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<OrderDetailBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<OrderDetailBean> baseBean) {
                        getView().getOrderDetailSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取退款原因
     * @param orderId
     */
    public void getDetailForReason(String orderId) {
        api.getDetailForReason(orderId)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<String>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(String baseBean) {
                        if (!TextUtils.isEmpty(baseBean)) {
                            List<ReasonBean> reasonBeans = new ArrayList<>();
                            try {
                                JSONObject jsonObject = new JSONObject(baseBean);
                                JSONObject reason = ((JSONObject) jsonObject.get("response")).getJSONObject("refund_reason");
                                PLog.e(reason + "");
                                Map maps = new Gson().fromJson(reason.toString(),Map.class);
                                for (Object map : maps.entrySet()) {
                                    ReasonBean bean = new ReasonBean(((Map.Entry) map).getKey()+"",((Map.Entry) map).getValue()+"");
                                    reasonBeans.add(bean);
                                }
                                getView().getReasonSuccess(reasonBeans);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    public void getRefund(String orderId, Map<String, Object> parms) {
        String strEntity = new Gson().toJson(parms);
        RequestBody requestBody = RequestBody
                .create(MediaType.parse("application/json;charset=UTF-8"), strEntity);
        api.getRefund(orderId,requestBody)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        getView().getRefundSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
