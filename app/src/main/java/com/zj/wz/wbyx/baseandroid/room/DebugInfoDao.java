package com.zj.wz.wbyx.baseandroid.room;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * FileName: DebugInfoDao
 * Author: 曹伟
 * Date: 2019/9/16 15:33
 * Description:
 */

@Dao
public interface DebugInfoDao {

    @Query("SELECT * FROM debuginfo")
    public Flowable<List<DebugInfo>> loadAll();

    //Using Room DB,Need to Use DataSource.Factory Implement PositionalDataSource
    @Query("SELECT * FROM debuginfo")
    public DataSource.Factory<Integer, DebugInfo> loadDataSource();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertDebugInfo(List<DebugInfo> debugInfos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertDebugInfo(DebugInfo debugInfo);

    @Query("DELETE FROM debuginfo")
    void deleteAllDebugInfo();

}
