package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.RecentEntity
import com.gunwoo.karaoke.data.database.entity.SearchHistoryEntity
import com.gunwoo.karaoke.data.database.entity.SearchSettingEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SearchSettingDao : BaseDao<SearchSettingEntity> {

    @Query("SELECT * FROM search_setting_table")
    fun getSearchSettingList(): Single<List<SearchSettingEntity>>

    @Query("UPDATE search_setting_table SET maxResults=:maxResults WHERE channelId=:channelId")
    fun updateSearchSetting(maxResults: Int, channelId: String): Completable

    @Query("DELETE FROM search_setting_table WHERE channelId=:channelId")
    fun deleteSearchSetting(channelId: String): Completable

    @Query("DELETE FROM search_setting_table")
    fun deleteAll(): Completable
}