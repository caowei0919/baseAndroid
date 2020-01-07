package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * FileName: HomeDateBean
 * Author: 曹伟
 * Date: 2019/10/28 17:45
 * Description:首页数据模块
 */
@Keep
public class HomeDateBean {
    private List<BannersBean> banners;      //banner图
    private List<CategoriesBean> categories ;   //分类
    private RecomBean recom ;   //模块
    @SerializedName("new")
    private NewRecommendBean newX;      //新品推荐
    private GrouponBean groupon ;   //拼团
    private List<SubjectsBean> subjects ;   //专题详情

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public GrouponBean getGroupon() {
        return groupon;
    }

    public void setGroupon(GrouponBean groupon) {
        this.groupon = groupon;
    }

    public NewRecommendBean getNewX() {
        return newX;
    }

    public void setNewX(NewRecommendBean newX) {
        this.newX = newX;
    }

    public RecomBean getRecom() {
        return recom;
    }

    public void setRecom(RecomBean recom) {
        this.recom = recom;
    }

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public List<BannersBean> getBanners() {
        return banners;

    }
    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }
}
