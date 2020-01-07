package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.CarFootBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.UserBean;

/**
 * FileName: MainView
 * Author: 曹伟
 * Date: 2019/9/12 13:47
 * Description:
 */

public interface MainView extends MvpView {
    /**
     * 获取用户信息成功
     * @param response
     */
    void getUserSuccess(UserBean response);

    /**
     * 获取购物车角标成功
     * @param response
     */
    void getCarFootSuccess(CarFootBean response);
}
