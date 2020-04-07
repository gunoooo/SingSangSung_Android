package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.PlaylistEntity
import com.gunwoo.karaoke.data.database.entity.SearchEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SearchDao : BaseDao<SearchEntity> {

    @Query("SELECT * FROM search_table WHERE search=:search AND channelId=:channelId")
    fun getSearchList(search: String, channelId: String): Single<List<SearchEntity>>

    @Query("DELETE FROM search_table")
    fun deleteAll(): Completable
}