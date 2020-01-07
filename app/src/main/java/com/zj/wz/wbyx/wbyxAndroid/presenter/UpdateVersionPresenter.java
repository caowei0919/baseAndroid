package com.zj.wz.wbyx.wbyxAndroid.presenter;


import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.download.DownloadException;
import com.zj.wz.wbyx.baseandroid.download.DownloadManager;
import com.zj.wz.wbyx.baseandroid.download.SimpleDownLoadCallBack;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ListVersionBean;
import com.zj.wz.wbyx.wbyxAndroid.view.UpdateVersionView;

import java.io.File;

import javax.inject.Inject;


/**
 * FileName: UpdateVersionPresenter
 * Author: 曹伟
 * Date: 2019/10/11 9:41
 * Description:下载更新P层
 */

public class UpdateVersionPresenter extends BasePresenter<UpdateVersionView> {
    MyApi api ;

    @Inject
    public UpdateVersionPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 检查版本
     */
    public void checkVersion() {
        api.checkVersion()
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<ListVersionBean>>(){
                    @Override
                    public void onNext(BaseBean<ListVersionBean> baseBean) {
                        if(baseBean.getResponse() != null && baseBean.getResponse().size() > 0)
                            getView().checkVersionSuccess(baseBean.getResponse().get(0));
                    }
                });
    }

    /**
     * 下载文件
     * @param download_url
     * @param mDownLoadApkFolde
     * @param mDownLoadApkFileName
     */
    public void requestDownload(String download_url, String mDownLoadApkFolde
            , String mDownLoadApkFileName) {
        DownloadManager.getInstance()
                .download(download_url, new File(mDownLoadApkFolde), mDownLoadApkFileName
                        , false, new SimpleDownLoadCallBack() {

                    @Override
                    public void onProgress(long finished, long total, int progress) {
                        if (getView() != null) {
                            getView().downloadProgress(progress);
                        }
                    }

                    @Override
                    public void onCompleted(File downloadfile) {
                        if (getView() != null) {
                            getView().downloadCompleted();
                        }
                    }

                    @Override
                    public void onFailed(DownloadException e) {
                        if (getView() != null) {
                            getView().downloadFailed();
                        }
                    }
                });
    }
}
