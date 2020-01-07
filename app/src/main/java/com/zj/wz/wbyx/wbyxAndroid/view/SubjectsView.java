package com.zj.wz.wbyx.wbyxAndroid.view;


import android.view.View;

import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SubjectBean;

/**
 * FileName: SubjectsView
 * Author: 曹伟
 * Date: 2019/11/9 10:16
 * Description:专题活动P层
 */

public interface SubjectsView extends MvpView {
    /**
     * 获取专题详情成功
     * @param response
     */
    void getSpecialListSuccess(SubjectBean response);

    void getGoodDetailSucccess(GoodDetailBean baseBean);
}
