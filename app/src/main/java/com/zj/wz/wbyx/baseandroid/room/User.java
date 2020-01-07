package com.zj.wz.wbyx.baseandroid.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * FileName: User
 * Author: 曹伟
 * Date: 2019/9/16 15:31
 * Description:
 */

@Keep
@Entity(tableName = "users")
public class User {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "userid")
    private String mId;

    @ColumnInfo(name = "username")
    private String mUserName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "last_update")
    private Date mDate;

    @Ignore
    Bitmap picture;

    public User(String id, String userName, Date date) {
        this.mId = id;
        this.mUserName = userName;
        this.mDate = date;
    }

    public String getId() {
        return mId;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDate() {
        return mDate;
    }

    public void setId(@NonNull String id) {
        mId = id;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}