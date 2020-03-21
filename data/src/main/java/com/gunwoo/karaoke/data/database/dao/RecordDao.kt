package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.RecordEntity
import io.reactivex.Single

@Dao
interface RecordDao : BaseDao<RecordEntity> {

    @Query("SELECT * FROM record_table")
    fun getRecordList(): Single<List<RecordEntity>>
}