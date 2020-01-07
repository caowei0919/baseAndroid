package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryBean;

/**
 * FileName: DormitoryView
 * Author: 曹伟
 * Date: 2019/11/9 10:41
 * Description:宿舍小店P层
 */

public interface DormitoryView extends MvpView {
    /**
     * 默认地址下宿舍小店信息
     * @param response
     */
    void getDormitorySuccess(DormitoryBean response);

    /**
     * 无宿舍地址
     */
    void hasNoDormitory();

    /**
     * 无宿舍小店
     * @param dorm
     */
    void hasNoShop(String dorm);
}
