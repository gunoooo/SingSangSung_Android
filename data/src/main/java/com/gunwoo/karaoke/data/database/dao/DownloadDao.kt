package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.DownloadEntity
import com.gunwoo.karaoke.data.database.entity.RecordEntity
import io.reactivex.Single

@Dao
interface DownloadDao : BaseDao<DownloadEntity> {

    @Query("SELECT * FROM download_table")
    fun getDownloadList(): Single<List<DownloadEntity>>
}