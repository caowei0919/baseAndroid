package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.UpdataUserBean;

import java.io.File;

/**
 * FileName: UserInfoView
 * Author: 曹伟
 * Date: 2019/9/24 21:20
 * Description: 个人资料view层
 */

public interface UserInfoView extends MvpView {
    /**
     * 图片修改成功
     * @param imgFil
     */
    void changeImageSuccess(File imgFil);

    /**
     * 获取个人资料用户信息
     * @param updataUserBean
     */
    void getMemberUserSuccess(UpdataUserBean updataUserBean);


    /**
     * 昵称修改成功
     * @param nick
     */
    void updataNickSuccess(UpdataUserBean nick);
}
