package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.VipProducBean;

/**
 * FileName: BuyMembershipView
 * Author: 曹伟
 * Date: 2019/11/19 20:46
 * Description:购买会员V层
 */

public interface BuyMembershipView extends MvpView {
    /**
     * 获取会员套餐成功
     * @param response
     */
    void getVipProductSuccess(VipProducBean response);
}
