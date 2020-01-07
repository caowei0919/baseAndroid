package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;


/**
 * FileName: SearchHistoryBean
 * Author: 曹伟
 * Date: 2019/11/29 10:18
 * Description:搜索历史
 */
@Keep
public class SearchHistoryBean  {
    private String keywords ;   //搜索内容名称

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
