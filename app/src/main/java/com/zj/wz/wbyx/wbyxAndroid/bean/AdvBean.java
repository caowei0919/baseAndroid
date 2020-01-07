package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;

/**
 * FileName: AdvBean
 * Author: 曹伟
 * Date: 2019/10/15 11:03
 * Description:广告
 */
@Keep
public class AdvBean {

    /**
     * id : 25
     * title : 默认的分享有礼海报
     * type : 1
     * position_id : 2
     * start_time : 1546300800
     * end_time : 2147483647
     * add_time : null
     * image : http://img.wobianmall.com/uploads/2019/08/25/8d495c7fedc19a89a3df5b67fe1924fc.jpg
     * link : http://h5.wobianmall.com/share_money/share_vip?from=singlemessage&isappinstalled=0
     * desc : #FCBE60
     * sortnum : 0
     * status : 1
     */

    private int id;
    private String title;
    private String type;
    private String position_id;
    private String start_time;
    private String end_time;
    private Object add_time;
    private String image;
    private String link;
    private String desc;
    private String sortnum;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Object getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Object add_time) {
        this.add_time = add_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSortnum() {
        return sortnum;
    }

    public void setSortnum(String sortnum) {
        this.sortnum = sortnum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
