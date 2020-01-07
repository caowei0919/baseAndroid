package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SearchGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SearchHistoryListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.WareHouseBean;

/**
 * FileName: SearchView
 * Author: 曹伟
 * Date: 2019/11/13 10:31
 * Description:搜索页面V层
 */

public interface SearchView extends MvpView {
    /**
     * 获取搜索历史成功
     * @param response
     */
    void getSearchHistorySuccess(SearchHistoryListBean response);

    void deleteHistorySuccess();

    void getSearchGoodsSuccess(SearchGoodsListBean response);

    void getDayLocationSuccess(WareHouseBean wareHouseBean);

    void getGoodDetailSucccess(GoodDetailBean baseBean);
}
