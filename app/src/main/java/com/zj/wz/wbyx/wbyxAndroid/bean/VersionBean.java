package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

/**
 * FileName: VersionBean
 * Author: 曹伟
 * Date: 2019/10/11 9:26
 * Description:版本实体
 */
@Keep
public class VersionBean {

    /**
     * id : 3
     * version : 9.0.0
     * content : 1、更新登陆
     2、更新商品
     3、优化bug
     4、解决问题
     * type : Android
     * addtime : 1560761125
     * uptime : 1560761226
     * version_code : 209000000
     * download_url : http://img.wobianmall.com/app/9.0.0.apk
     * forced_updates : 0
     * compare : 1
     */

    private String id;
    private String version;
    private String content;
    private String type;
    private String addtime;
    private String uptime;
    private String version_code;
    private String download_url;
    private String forced_updates;
    private int compare;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getForced_updates() {
        return forced_updates;
    }

    public void setForced_updates(String forced_updates) {
        this.forced_updates = forced_updates;
    }

    public int getCompare() {
        return compare;
    }

    public void setCompare(int compare) {
        this.compare = compare;
    }
}
