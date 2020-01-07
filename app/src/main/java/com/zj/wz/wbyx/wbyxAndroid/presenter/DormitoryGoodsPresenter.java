package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.view.DormitoryGoodsView;

import javax.inject.Inject;


/**
 * FileName: DormitoryGoodsPresenter
 * Author: 曹伟
 * Date: 2019/11/14 15:22
 * Description:宿舍小店商品P层
 */

public class DormitoryGoodsPresenter extends BasePresenter<DormitoryGoodsView> {
    MyApi api ;

    @Inject
    public DormitoryGoodsPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取商品
     * @param shopId    宿舍小店id
     */
    public void getGoodsForShop(String shopId) {
        api.getGoodsForShop(shopId)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<DormitoryGoodsListBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<DormitoryGoodsListBean> baseBean) {
                        getView().getGoodsSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
