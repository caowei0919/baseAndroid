package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.AdvListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.view.SharePoliteView;

import javax.inject.Inject;


/**
 * FileName: SharePolitePresenter
 * Author: 曹伟
 * Date: 2019/10/15 10:35
 * Description:分享有礼P层
 */

public class SharePolitePresenter extends BasePresenter<SharePoliteView> {
    MyApi api ;

    @Inject
    public SharePolitePresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 分享有礼广告图
     * @param position_id
     */
    public void getAdv(int position_id) {
        api.getAdv(position_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<AdvListBean>>(){
                    @Override
                    public void onNext(BaseBean<AdvListBean> baseBean) {
                        if(baseBean.getResponse() != null && baseBean.getResponse().size() > 0)
                            getView().getAdvSuccess(baseBean.getResponse());
                    }
                });
    }
}
