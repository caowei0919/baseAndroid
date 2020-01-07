package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.MemberCenterBean;

/**
 * FileName: MineView
 * Author: 曹伟
 * Date: 2019/9/20 21:24
 * Description:我的View层
 */

public interface MineView extends MvpView {
    /**
     * 获取会员中心信息
     * @param response
     */
    void getMemberBeanSuccess(MemberCenterBean response);
}
