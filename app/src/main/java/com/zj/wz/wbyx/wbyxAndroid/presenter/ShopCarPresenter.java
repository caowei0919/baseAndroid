package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.event.UpDateCarFootEvent;
import com.zj.wz.wbyx.wbyxAndroid.view.ShopCarView;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import javax.inject.Inject;


/**
 * FileName: ShopCarPresenter
 * Author: 曹伟
 * Date: 2019/9/20 21:23
 * Description:购物车P层
 */

public class ShopCarPresenter extends BasePresenter<ShopCarView>{
    MyApi myApi ;

    @Inject
    public ShopCarPresenter(MyApi myApi) {
        this.myApi = myApi;
    }

    /**
     * 获取购物车列表
     * @param area_id
     */
    public void getShopCarGoods(String area_id) {
        myApi.getShopCarGoods(area_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<ShopCarBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<ShopCarBean> baseBean) {
                        getView().getShopCartShopSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 批量删除购物车
     * @param carMap
     */
    public void deleteAllSelected(Map<String,Object> carMap) {
        myApi.deleteAllSelected(carMap)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        getView().deleteSelectedSuccess();
                        EventBus.getDefault().post(new UpDateCarFootEvent());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 上传用户选中和未选中状态
     * @param carMap
     */
    public void putUserSelected(Map<String,Object> carMap) {
        myApi.putUserSelected(carMap)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
