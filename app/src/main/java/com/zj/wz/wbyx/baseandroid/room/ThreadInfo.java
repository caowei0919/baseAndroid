package com.zj.wz.wbyx.baseandroid.room;

import android.arch.persistence.room.Entity;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * FileName: ThreadInfo
 * Author: 曹伟
 * Date: 2019/9/16 15:14
 * Description:
 */

@Keep
@Entity(primaryKeys = {"id", "tag"})
public class ThreadInfo {
    long id;
    String uri;
    @NonNull
    String tag;
    long startoffset;
    long endoffset;
    long finished;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Nullable
    public String getTag() {
        return tag;
    }

    public void setTag(@Nullable String tag) {
        this.tag = tag;
    }

    public long getStartoffset() {
        return startoffset;
    }

    public void setStartoffset(long startoffset) {
        this.startoffset = startoffset;
    }

    public long getEndoffset() {
        return endoffset;
    }

    public void setEndoffset(long endoffset) {
        this.endoffset = endoffset;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
