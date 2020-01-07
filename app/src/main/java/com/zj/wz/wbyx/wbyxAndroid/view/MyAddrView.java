package com.zj.wz.wbyx.wbyxAndroid.view;

import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.AddressBean;

/**
 * FileName: MyAddrView
 * Author: 曹伟
 * Date: 2019/10/15 11:47
 * Description:我的地址V层
 */

public interface MyAddrView extends MvpView {
    /**
     * 获取所有地址成功
     * @param response
     */
    void getAllAddressSuccess(AddressBean response);
}
