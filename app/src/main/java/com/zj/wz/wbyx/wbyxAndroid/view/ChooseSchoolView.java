package com.zj.wz.wbyx.wbyxAndroid.view;

import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.ChooseSchool;
import com.zj.wz.wbyx.wbyxAndroid.bean.SelectSchoolBean;


/**
 * FileName: ChooseSchoolView
 * Author: 曹伟
 * Date: 2019/10/7 14:39
 * Description:选择学校v层
 */

public interface ChooseSchoolView extends MvpView {
    //获取附近学校信息成功
    void getNearSchool(ChooseSchool.SchoolBean school);

    //检索学校信息成功
    void searchSchoolSuccess(SelectSchoolBean baseBean);
}
