package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.BuildListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryAddress;

/**
 * FileName: AddDormitoryView
 * Author: 曹伟
 * Date: 2019/10/16 10:52
 * Description:新增修改宿舍地址V层
 */

public interface AddDormitoryView extends MvpView {
    /**
     * 获取学校楼栋成功
     * @param response
     */
    void getBuildSuccess(BuildListBean response);

    /**
     * 添加宿舍地址成功
     * @param response
     */
    void addAddress(DormitoryAddress response);

    /**
     * 修改宿舍地址成功
     */
    void upDateSuccess();
}
