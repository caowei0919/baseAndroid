package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryBean;
import com.zj.wz.wbyx.wbyxAndroid.view.DormitoryView;

import javax.inject.Inject;

import static com.zj.wz.wbyx.baseandroid.config.Api.CODE_HAS_NO_DORMITOR;
import static com.zj.wz.wbyx.baseandroid.config.Api.CODE_HAS_NO_SHOP;
import static com.zj.wz.wbyx.baseandroid.config.Api.CODE_SUCCESS;


/**
 * FileName: DormitoryPresenter
 * Author: 曹伟
 * Date: 2019/11/9 10:42
 * Description:宿舍小店P层
 */

public class DormitoryPresenter extends BasePresenter<DormitoryView> {
    MyApi api ;

    @Inject
    public DormitoryPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取宿舍小店信息
     */
    public void getDormitor() {
        api.getDormitor()
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<DormitoryBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<DormitoryBean> baseBean) {
                        switch (baseBean.getCode()){
                            case CODE_HAS_NO_DORMITOR : //601无宿舍地址
                                getView().hasNoDormitory();
                                break;

                            case CODE_HAS_NO_SHOP : //602 无宿舍小店
                                getView().hasNoShop(baseBean.getResponse().getDorm());
                                break;

                            case CODE_SUCCESS : //200
                                getView().getDormitorySuccess(baseBean.getResponse());
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
