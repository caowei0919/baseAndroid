package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;

/**
 * FileName: GoodsDetailView
 * Author: 曹伟
 * Date: 2019/11/8 21:36
 * Description:商品详情V层
 */

public interface GoodsDetailView extends MvpView {
    /**
     * 获取商品详情成功
     * @param baseBean
     */
    void getGoodDetailSucccess(GoodDetailBean baseBean);

    /**
     * 收藏、取消收藏成功
     */
    void setCollectSuccess();
}
