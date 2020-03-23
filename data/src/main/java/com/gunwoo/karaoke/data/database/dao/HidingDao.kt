package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.DownloadEntity
import com.gunwoo.karaoke.data.database.entity.HidingEntity
import com.gunwoo.karaoke.data.database.entity.RecordEntity
import io.reactivex.Single

@Dao
interface HidingDao : BaseDao<HidingEntity> {

    @Query("SELECT * FROM hiding_table")
    fun getHidingList(): Single<List<HidingEntity>>
}