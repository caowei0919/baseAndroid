package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.utils.ApiLevel;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.ProgressBarView;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.Application.MyApplication;
import com.zj.wz.wbyx.wbyxAndroid.bean.VersionBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.UpdateVersionPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.UpdateVersionView;


import java.io.File;

import butterknife.BindView;

/**
 * FileName: UpdateVersionActivity
 * Author: 曹伟
 * Date: 2019/10/11 9:40
 * Description:下载更新
 */

public class UpdateVersionActivity extends BaseMvpActivity<UpdateVersionView, UpdateVersionPresenter>
        implements UpdateVersionView {

    @BindView(R.id.progress_update)
    ProgressBarView progressUpdate ;    //更新进度

    private String mDownLoadApkFolde ;  //下载文件夹
    private String mDownLoadApkFileName ;   //文件名

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_version;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_FFFFFF));
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().checkVersion();
    }

    /**
     * 查询版本成功
     * @param versionBean
     */
    @Override
    public void checkVersionSuccess(VersionBean versionBean) {
        mDownLoadApkFolde = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .getAbsolutePath();
        mDownLoadApkFileName = "wbyx_" + versionBean.getVersion() + ".apk";
        new RxPermissions(mContext).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .compose(RxUtils.<Boolean>applySchedulersLifeCycle(getMvpView()))
                .subscribe(new RxObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            getPresenter().requestDownload(versionBean.getDownload_url()
                                    , mDownLoadApkFolde
                                    , mDownLoadApkFileName);
                        } else {
                            ToastUtils.showShortToast(mContext.getResources()
                                    .getString(R.string.permissions_not_to_down));
                        }
                    }
                });
    }

    /**
     * 下载进度
     * @param progress
     */
    @Override
    public void downloadProgress(int progress) {
        progressUpdate.setProgress(progress);
    }

    /**
     * 下载完成
     */
    @Override
    public void downloadCompleted() {
        if (ApiLevel.requireOreo()) {
            installApp();
        } else {
            AndroidUtils.installApk(new File(mDownLoadApkFolde, mDownLoadApkFileName));
            MyApplication.exitApp();
        }
    }

    /**
     * 安装app
     */
    private void installApp() {
        @SuppressLint({"NewApi", "LocalSuppress"})
        boolean canbeInstall = AndroidUtils.getContext()
                .getPackageManager()
                .canRequestPackageInstalls();
        if (canbeInstall) {
            AndroidUtils.installApk(new File(mDownLoadApkFolde, mDownLoadApkFileName));
            MyApplication.exitApp();
        } else {
            //请求安装未知应用来源的权限
            new RxPermissions(mContext).request(Manifest.permission.REQUEST_INSTALL_PACKAGES)
                    .compose(RxUtils.<Boolean>applySchedulersLifeCycle(getMvpView()))
                    .subscribe(new RxObserver<Boolean>() {
                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {
                                AndroidUtils.installApk(new File(mDownLoadApkFolde
                                        , mDownLoadApkFileName));
                                MyApplication.exitApp();
                            } else {
                                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES
                                        , Uri.parse("package:" + getPackageName()));
                                startActivityForResult(intent, Constant.INSTALL_APK);
                            }
                        }
                    });
        }
    }

    /**
     * 下载失败
     */
    @Override
    public void downloadFailed() {
        ToastUtils.showLongToast(R.string.start_file_download_error);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode ==  Constant.INSTALL_APK) {
            installApp();
        } else if (resultCode == RESULT_CANCELED && requestCode ==  Constant.INSTALL_APK) {
            MyApplication.exitApp();
        }
    }
}
