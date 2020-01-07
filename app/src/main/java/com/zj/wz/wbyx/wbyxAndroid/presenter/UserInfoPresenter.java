package com.zj.wz.wbyx.wbyxAndroid.presenter;

import android.content.Context;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.config.UploadApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.UpdataUserList;
import com.zj.wz.wbyx.wbyxAndroid.utils.MultipartUtil;
import com.zj.wz.wbyx.wbyxAndroid.view.UserInfoView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * FileName: UserInfoPresenter
 * Author: 曹伟
 * Date: 2019/9/24 21:21
 * Description:个人资料P层
 */

public class UserInfoPresenter extends BasePresenter<UserInfoView>{
    MyApi myApi ;
    UploadApi uploadApi ;

    @Inject
    public UserInfoPresenter(MyApi myApi,UploadApi uploadApi) {
        this.myApi = myApi;
        this.uploadApi = uploadApi ;
    }

    /**
     * 上传头像
     * @param imgFil
     */
    public void upLoadAvatar(File imgFil) {
        Map<String, String> options = new HashMap<>();
        final Map<String, RequestBody> params = MultipartUtil.getRequestBodyMap(options, "img", imgFil);
        getView().showLoadingDialog();
        uploadApi.uploadFileWithPartMap(params)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>() {
                    @Override
                    public void onComplete() {
                        getView().closeLoadingDialog();
                    }

                    @Override
                    public void onNext(BaseBean responseBody) {
                        ToastUtils.showLongToast(responseBody.getMsg());
                        getView().changeImageSuccess(imgFil);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().closeLoadingDialog();
                    }
                });
    }

    /**
     * 压缩图片，鲁班
     */
    public void initLuBanIO(Context mContext,String filePath) {
        Luban.with(mContext)
                .load(filePath)  // 传人要压缩的图片列表(包括file，path，List<String>)
                .ignoreBy(100)   // 忽略不压缩图片的大小
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        PLog.e("LuBan is onSuccess ,file ——> " + file.getName());
                        upLoadAvatar(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch(); //启动压缩
    }

//    /**
//     *
//     * @param selectedDate
//     */
//    public void updataBirthday(String selectedDate) {
//        myApi.updataBirthday(selectedDate)
//                .compose(RxUtils.applySchedulersLifeCycle(getView()))
//                .subscribe(new RxObserver<BaseBean<UpdataUserList>>(){
//                    @Override
//                    public void onComplete() {
//                        super.onComplete();
//                    }
//
//                    @Override
//                    public void onNext(BaseBean<UpdataUserList> updataUserListBaseBean) {
//                        getView().updataBirthdaySuccess(selectedDate);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//                    }
//                });
//
//    }

    /**
     * 获取个人资料用户要展示的信息
     */
    public void getMemberUser() {
        myApi.getMemberUser()
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<UpdataUserList>>(){
                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }

                    @Override
                    public void onNext(BaseBean<UpdataUserList> updataUserListBaseBean) {
                        getView().getMemberUserSuccess(updataUserListBaseBean.getResponse().get(0));
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

//    /**
//     * 修改性别
//     * @param codeGenderMale
//     */
//    public void updataGender(String codeGenderMale) {
//        myApi.updataGender(codeGenderMale)
//                .compose(RxUtils.applySchedulersLifeCycle(getView()))
//                .subscribe(new RxObserver<BaseBean<UpdataUserList>>(){
//                    @Override
//                    public void onComplete() {
//                        super.onComplete();
//                    }
//
//                    @Override
//                    public void onNext(BaseBean<UpdataUserList> updataUserListBaseBean) {
//                        getView().updataGenderSuccess(codeGenderMale);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//                    }
//                });
//    }

    /**
     * 修改昵称
     * @param nick
     */
    public void updataUserInfo(String selectedDate,String codeGenderMale,String nick) {
        myApi.updataUserInfo(selectedDate,codeGenderMale,nick)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<UpdataUserList>>(){
                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }

                    @Override
                    public void onNext(BaseBean<UpdataUserList> updataUserListBaseBean) {
                        getView().updataNickSuccess(updataUserListBaseBean.getResponse().get(0));
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
