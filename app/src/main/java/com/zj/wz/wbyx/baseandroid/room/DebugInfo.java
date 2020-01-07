package com.zj.wz.wbyx.baseandroid.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;

/**
 * FileName: DebugInfo
 * Author: 曹伟
 * Date: 2019/9/16 15:23
 * Description:
 */

@Keep
@Entity(tableName = "debuginfo")
public class DebugInfo {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    // userinfo、network、business
    private String debugtype;

    private String debuginfo;

    private String debugtime;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getDebugtype() {
        return debugtype;
    }

    public void setDebugtype(String debugtype) {
        this.debugtype = debugtype;
    }

    public String getDebuginfo() {
        return debuginfo;
    }

    public void setDebuginfo(String debuginfo) {
        this.debuginfo = debuginfo;
    }

    public String getDebugtime() {
        return debugtime;
    }

    public void setDebugtime(String debugtime) {
        this.debugtime = debugtime;
    }
}
