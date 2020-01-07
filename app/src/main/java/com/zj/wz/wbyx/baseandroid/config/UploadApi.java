package com.zj.wz.wbyx.baseandroid.config;



import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;

import java.util.Map;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface UploadApi {

    @Multipart
    @POST("/member_user")
    Observable<BaseBean> uploadFileWithPartMap(@PartMap Map<String, RequestBody> partMap);

    @Multipart
    @POST("/feedback")
    Observable<BaseBean> uploadFileWithFeedBack(@PartMap Map<String, RequestBody> partMap);
}
