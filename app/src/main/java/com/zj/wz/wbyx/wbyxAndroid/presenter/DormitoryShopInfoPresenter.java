package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.wbyxAndroid.view.DormitoryShopInfoView;

import javax.inject.Inject;


/**
 * FileName: DormitoryShopInfoPresenter
 * Author: 曹伟
 * Date: 2019/11/14 15:27
 * Description:宿舍小店店铺信息P层
 */

public class DormitoryShopInfoPresenter extends BasePresenter<DormitoryShopInfoView> {
    MyApi api ;

    @Inject
    public DormitoryShopInfoPresenter(MyApi api) {
        this.api = api;
    }
}
