package com.zj.wz.wbyx.wbyxAndroid.presenter;
import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.config.UploadApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ServiceBean;
import com.zj.wz.wbyx.wbyxAndroid.utils.MultipartUtil;
import com.zj.wz.wbyx.wbyxAndroid.view.FeedBackView;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.RequestBody;


/**
 * FileName: FeedBackPresenter
 * Author: 曹伟
 * Date: 2019/10/11 20:56
 * Description:问题反馈P层
 */

public class FeedBackPresenter extends BasePresenter<FeedBackView> {
    MyApi api ;
    UploadApi uploadApi ;

    @Inject
    public FeedBackPresenter(MyApi api, UploadApi uploadApi) {
        this.api = api;
        this.uploadApi = uploadApi;
    }

    /**
     * 获取常见问题
     * @param typeWrite
     */
    public void getServerWrite(int typeWrite) {
        api.getServerWrite(typeWrite)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<ServiceBean>>(){
                    @Override
                    public void onNext(BaseBean<ServiceBean> baseBean) {
                        getView().getServiceWriteSuccess(baseBean.getResponse());
                    }
                });
    }

    public void submit(File[] imgFile, String toString) {
        Map<String,Object> parms = new HashMap<>();
        parms.put("content",toString);
        final Map<String, RequestBody> params = MultipartUtil.getRequestBodyMap(parms, "image[]", imgFile);
        uploadApi.uploadFileWithFeedBack(params)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if(RxObserver.checkJsonCode(baseBean)){
                            getView().getSubmitSuccess(baseBean);
                        }
                    }
                });
    }
}
