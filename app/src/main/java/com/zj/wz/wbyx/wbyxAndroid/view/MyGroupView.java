package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.GroupBeans;

/**
 * FileName: MyGroupView
 * Author: 曹伟
 * Date: 2019/10/9 14:08
 * Description:我的拼团V层
 */

public interface MyGroupView extends MvpView {
    /**
     * 获取我的拼团列表成功
     * @param response
     */
    void getGroupSuccess(GroupBeans response);
}
