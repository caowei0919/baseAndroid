package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BuildListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryAddress;
import com.zj.wz.wbyx.wbyxAndroid.view.AddDormitoryView;

import javax.inject.Inject;



/**
 * FileName: AddDormitoryPresenter
 * Author: 曹伟
 * Date: 2019/10/16 10:53
 * Description:新增修改宿舍地址P层
 */

public class AddDormitoryPresenter extends BasePresenter<AddDormitoryView> {
    MyApi api ;

    @Inject
    public AddDormitoryPresenter(MyApi api) {
        this.api = api;
    }

    /**
     * 获取附近学校
     * @param type 1=请求全国省市区列表+当前城市学校列表 2=请求检索完省市区以后的学校列表 3=获取该学校的楼栋
     * @param current_city_name  当前城市名称
     * @param area_id   区域id
     * @param school_id  学校id
     * @param page  页码
     */
    public void getSchoolBuild(String type,String current_city_name
            ,String area_id,String school_id ,int page ) {
        api.chooseBuild(type,current_city_name,area_id,school_id,page)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<BuildListBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<BuildListBean> baseBean) {
                        getView().getBuildSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 添加地址
     * @param type
     * @param address_info
     */
    public void addAddress(String type, String address_info) {
        api.addDormitoryAddress(type,address_info)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<DormitoryAddress>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<DormitoryAddress> baseBean) {
                        getView().addAddress(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 修改宿舍地址
     * @param type
     * @param address_info
     * @param address_id
     */
    public void updataAddress(String type, String address_info, String address_id) {
        api.upDateNotDormitoryAddress(type,address_info,address_id)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        getView().upDateSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
