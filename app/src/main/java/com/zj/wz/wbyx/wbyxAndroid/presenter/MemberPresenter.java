package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MemberCenterBean;
import com.zj.wz.wbyx.wbyxAndroid.view.MemberView;

import javax.inject.Inject;


/**
 * FileName: MemberPresenter
 * Author: 曹伟
 * Date: 2019/9/20 21:20
 * Description: 会员P层
 */

public class MemberPresenter extends BasePresenter<MemberView>{
    MyApi myApi ;

    @Inject
    public MemberPresenter(MyApi myApi) {
        this.myApi = myApi;
    }

    /**
     * 获取会员中心信息
     */
    public void getMemberCenter() {
        myApi.getMemberCenter()
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<MemberCenterBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<MemberCenterBean> baseBean) {
                        getView().getMemberBeanSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
