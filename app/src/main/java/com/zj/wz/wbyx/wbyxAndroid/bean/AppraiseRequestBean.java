package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;

import java.util.List;
@Keep
public class AppraiseRequestBean {

    private String score;
    private String comment;
    private String id;
    private List<String> img;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }
}
