package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.RecentEntity
import com.gunwoo.karaoke.data.database.entity.SearchHistoryEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SearchHistoryDao : BaseDao<SearchHistoryEntity> {

    @Query("SELECT * FROM search_history_table")
    fun getSearchHistoryList(): Single<List<SearchHistoryEntity>>

    @Query("DELETE FROM search_history_table WHERE search=:search")
    fun delete(search: String): Completable

    @Query("DELETE FROM search_history_table")
    fun deleteAll(): Completable
}