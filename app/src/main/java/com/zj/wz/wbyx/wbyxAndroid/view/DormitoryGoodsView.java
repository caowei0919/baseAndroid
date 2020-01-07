package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryGoodsListBean;

/**
 * FileName: DormitoryGoodsView
 * Author: 曹伟
 * Date: 2019/11/14 15:21
 * Description:宿舍小店商品V层
 */

public interface DormitoryGoodsView extends MvpView {
    /**
     * 获取宿舍小店商品成功
     * @param response
     */
    void getGoodsSuccess(DormitoryGoodsListBean response);
}
