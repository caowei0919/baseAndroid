package com.zj.wz.wbyx.baseandroid.mvp.base;

/**
 * FileName: MvpView
 * Author: 曹伟
 * Date: 2019/9/12 17:15
 * Description:
 */

public interface MvpView {
    //加载开始
    void showLoadingDialog();

    //加载结束
    void closeLoadingDialog();
}
