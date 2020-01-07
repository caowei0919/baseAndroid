package com.zj.wz.wbyx.wbyxAndroid.presenter;

import com.zj.wz.wbyx.baseandroid.config.MyApi;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ChooseSchool;
import com.zj.wz.wbyx.wbyxAndroid.bean.SelectSchoolBean;
import com.zj.wz.wbyx.wbyxAndroid.view.ChooseSchoolView;

import javax.inject.Inject;


/**
 * FileName: ChooseSchoolPresenter
 * Author: 曹伟
 * Date: 2019/10/7 14:40
 * Description:选择学校P层
 */

public class ChooseSchoolPresenter extends BasePresenter<ChooseSchoolView> {
    private MyApi api ;

    @Inject
    public ChooseSchoolPresenter(MyApi api) {
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
    public void getNearSchool(String type,String current_city_name
            ,String area_id,String school_id ,int page ) {
        api.chooseSchool(type,current_city_name,area_id,school_id,page)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<ChooseSchool>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<ChooseSchool> baseBean) {
                        if(baseBean.getResponse().getSchool()!= null)
                            getView().getNearSchool(baseBean.getResponse().getSchool());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 根据关键字搜索学校
     * @param keyWord
     * @param area_id
     * @param page
     */
    public void searchSchool(String keyWord, String area_id, int page) {
        api.searchSchool(keyWord,area_id,page)
                .compose(RxUtils.applySchedulersLifeCycle(getView()))
                .subscribe(new RxObserver<BaseBean<SelectSchoolBean>>(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(BaseBean<SelectSchoolBean> baseBean) {
                        getView().searchSchoolSuccess(baseBean.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
