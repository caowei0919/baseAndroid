package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;

import java.util.List;

/**
 * FileName: AppraiseGoodView
 * Author: 曹伟
 * Date: 2019/11/20 19:27
 * Description:商品评价V层
 */

public interface AppraiseGoodView extends MvpView {
    void getOrderGoodSuccess(List<OrderDetailBean.OrderBean.GoodsBean> goods);
}
