package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.RecentEntity
import io.reactivex.Single

@Dao
interface RecentDao : BaseDao<RecentEntity> {

    @Query("SELECT * FROM recent_table")
    fun getRecentList(): Single<List<RecentEntity>>
}