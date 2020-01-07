package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.MemberCenterBean;

/**
 * FileName: MemberView
 * Author: 曹伟
 * Date: 2019/9/20 21:20
 * Description: 会员View层
 */

public interface MemberView extends MvpView {
    /**
     * 获取会员信息成功
     */
    void getMemberBeanSuccess(MemberCenterBean memberCenterBean);
}
