package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.google.gson.Gson;
import com.zj.wz.wbyx.baseandroid.config.Api;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.view.GoodsDetailView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * FileName: GoodsDetailPresenter
 * Author: 曹伟
 * Date: 2019/11/8 21:36
 * Description:商品详情P层
 */

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailView> {
    MyApi api ;

    @Inject
    public GoodsDetailPresenter(MyApi api) {
        this.api = api;
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

    public void setCollect(String goods_id, String goods_type, String collectStatus, String warehouse_id) {
        HashMap<String ,String> map = new HashMap<>();
        map.put("goods_type",goods_type);
        map.put("goods_id",goods_id);
        map.put("change_type",collectStatus);
        map.put("warehouse_id",warehouse_id);
        String strEntity = new Gson().toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), strEntity);
        api.setCollect(requestBody)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onNext(BaseBean baseBean) {
                        getView().setCollectSuccess();
                    }
                });
    }
}
