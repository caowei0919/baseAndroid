package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.ServiceBean;

/**
 * FileName: ServiceView
 * Author: 曹伟
 * Date: 2019/10/11 14:23
 * Description:服务条款V层
 */

public interface ServiceView extends MvpView {

    void getServiceWriteSuccess(ServiceBean baseBean);
}
