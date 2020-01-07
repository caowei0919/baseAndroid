package com.zj.wz.wbyx.wbyxAndroid.presenter;

import android.text.TextUtils;
import android.view.View;

import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.AddShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SubjectBean;
import com.zj.wz.wbyx.wbyxAndroid.event.UpDateCarFootEvent;
import com.zj.wz.wbyx.wbyxAndroid.view.SubjectsView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


/**
 * FileName: SubjectsPresenter
 * Author: 曹伟
 * Date: 2019/11/9 10:16
 * Description:
 */

public class SubjectsPresenter extends BasePresenter<SubjectsView> {

    MyApi api ;

    @Inject
    public SubjectsPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取商品列表
     * @param activityId    活动id
     * @param rule  排序规则
     * @param page  页码
     * @param pageSize
     * @param field 商品类型
     */
    public void getSpecialList(String activityId, String rule, int page, int pageSize, String field) {
        Map<String, String> param = new HashMap<>();
        param.put("activity_id", activityId);
        param.put("rule", rule);
        param.put("page", page+"");
        param.put("pagesize", pageSize + "");
        if (!TextUtils.isEmpty(field))
            param.put("field", field);
        api.getSpecialList(param)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<SubjectBean>>() {
                    @Override
                    public void onNext(BaseBean<SubjectBean> stringBaseBean) {
                        getView().getSpecialListSuccess(stringBaseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取商品详情信息
     * @param map
     */
    public void getGoodsDetail(Map<String, String> map) {
        api.getGoodsDetail(map)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<GoodDetailBean>(){
                    @Override
                    public void onNext(GoodDetailBean baseBean) {
                        if(baseBean.getCode().equals(Api.CODE_SUCCESS)){
                            getView().getGoodDetailSucccess(baseBean);
                        }
                    }
                });
    }

    /**
     * 推荐商品加入购物车
     * @param params
     */
    public void recommendAddCar(Map<String, Object> params) {
        api.recommendAddCar(params)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<AddShopCarBean>>(){
                    @Override
                    public void onNext(BaseBean<AddShopCarBean> baseBean) {
                        if(checkJsonCode(baseBean)){
                            ToastUtils.showLongToast(baseBean.getMsg());
                            EventBus.getDefault().post(new UpDateCarFootEvent());
                        }
                    }
                });
    }

    /**
     * 商城加入购物车
     * @param marketparams
     */
    public void marketAddCar(Map<String, Object> marketparams) {
        api.marketAddCar(marketparams)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<AddShopCarBean>>(){
                    @Override
                    public void onNext(BaseBean<AddShopCarBean> baseBean) {
                        if(checkJsonCode(baseBean)){
                            ToastUtils.showLongToast(baseBean.getMsg());
                            EventBus.getDefault().post(new UpDateCarFootEvent());
                        }
                    }
                });
    }
}
