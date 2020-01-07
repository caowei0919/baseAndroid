package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.AdvListBean;

/**
 * FileName: SharePoliteView
 * Author: 曹伟
 * Date: 2019/10/15 10:35
 * Description:分享有礼V层
 */

public interface SharePoliteView extends MvpView {
    /**
     * 获取广告成功
     * @param response
     */
    void getAdvSuccess(AdvListBean response);
}
