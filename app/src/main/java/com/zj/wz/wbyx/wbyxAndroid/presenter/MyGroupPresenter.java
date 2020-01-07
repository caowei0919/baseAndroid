package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GroupBeans;
import com.zj.wz.wbyx.wbyxAndroid.view.MyGroupView;

import javax.inject.Inject;


/**
 * FileName: MyGroupPresenter
 * Author: 曹伟
 * Date: 2019/10/9 14:09
 * Description:我的拼团P层
 */

public class MyGroupPresenter extends BasePresenter<MyGroupView> {
    MyApi api ;

    @Inject
    public MyGroupPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取拼团订单
     * @param area_id  区域id
     */
    public void getGroupOrder(String area_id) {
        PLog.e(area_id);
        api.getGroupOrder(area_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<GroupBeans>>(){

                    @Override
                    public void onNext(BaseBean<GroupBeans> baseBean) {
                        getView().getGroupSuccess(baseBean.getResponse());
                    }
                });
    }
}
