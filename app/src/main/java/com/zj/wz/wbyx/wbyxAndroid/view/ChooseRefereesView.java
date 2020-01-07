package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefereesBean;

/**
 * FileName: ChooseRefereesView
 * Author: 曹伟
 * Date: 2019/10/8 14:12
 * Description:选择联系人v层
 */

public interface ChooseRefereesView extends MvpView {
    //获取推荐人列表成功
    void applyBuildSuccess(RefereesBean response);
}
