package com.zj.wz.wbyx.wbyxAndroid.view;


import com.zj.wz.wbyx.baseandroid.mvp.base.MvpView;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ServiceBean;

/**
 * FileName: FeedBackView
 * Author: 曹伟
 * Date: 2019/10/11 20:55
 * Description:问题反馈V层
 */

public interface FeedBackView extends MvpView {
    /**
     * 获取常见问题成功
     * @param response
     */
    void getServiceWriteSuccess(ServiceBean response);

    /**
     * 提交意见反馈成功
     * @param baseBean
     */
    void getSubmitSuccess(BaseBean baseBean);
}
