package com.gunwoo.karaoke.data.database.cache

import android.app.Application
import androidx.room.EmptyResultSetException
import com.gunwoo.karaoke.data.base.BaseCache
import com.gunwoo.karaoke.data.database.entity.SearchSettingEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SearchSettingCache @Inject constructor(application: Application) : BaseCache(application) {

    private val searchSettingDao = database.searchSettingDao()

    fun getSearchSettingList(): Single<List<SearchSettingEntity>> =
        searchSettingDao.getSearchSettingList()
            .flatMap {
                if (it.isEmpty()) Single.error(EmptyResultSetException("SearchSetting table is empty"))
                else Single.just(it)
            }

    fun insertSearchSetting(searchSettingEntity: SearchSettingEntity): Completable = searchSettingDao.insert(searchSettingEntity)

    fun insertSearchSettingList(searchSettingEntityList: List<SearchSettingEntity>): Completable = searchSettingDao.insert(searchSettingEntityList)

    fun deleteSearchSetting(searchSettingEntity: SearchSettingEntity): Completable = searchSettingDao.deleteSearchSetting(searchSettingEntity.channelId)

    fun updateSearchSetting(searchSettingEntity: SearchSettingEntity): Completable =
        searchSettingDao.updateSearchSetting(searchSettingEntity.maxResults, searchSettingEntity.channelId)

    fun deleteAll(): Completable = searchSettingDao.deleteAll()
}