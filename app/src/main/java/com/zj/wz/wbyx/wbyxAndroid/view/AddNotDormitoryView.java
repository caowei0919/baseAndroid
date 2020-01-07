package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.NotDormitoryAddress;

/**
 * FileName: AddNotDormitoryView
 * Author: 曹伟
 * Date: 2019/10/18 9:31
 * Description:新增修改非宿舍地址V层
 */

public interface AddNotDormitoryView extends MvpView {
    /**
     * 添加非宿舍地址成功
     * @param response
     */
    void addNotDormitoryAddress(NotDormitoryAddress response);

    /**
     * 修改非宿舍地址成功
     */
    void upDateSuccess();
}
