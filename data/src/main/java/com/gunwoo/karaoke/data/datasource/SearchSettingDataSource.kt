package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.SearchSettingCache
import com.gunwoo.karaoke.data.mapper.SearchSettingMapper
import com.gunwoo.karaoke.data.util.SearchSettingFactory
import com.gunwoo.karaoke.domain.model.SearchSetting
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SearchSettingDataSource @Inject constructor(
    override val remote: Any,
    override val cache: SearchSettingCache
) : BaseDataSource<Any, SearchSettingCache>() {

    private val searchSettingMapper = SearchSettingMapper()

    fun getSearchSettingList(): Single<List<SearchSetting>> =
        cache.getSearchSettingList()
            .onErrorResumeNext {
                val baseSearchSettingList = SearchSettingFactory.getBaseSearchSetting()
                insertSearchSettingList(baseSearchSettingList).toSingleDefault(baseSearchSettingList.map { searchSettingMapper.mapToEntity(it) })
            }
            .map { searchSettingEntityList -> searchSettingEntityList.map { searchSettingMapper.mapToModel(it) } }

    fun getNotSelectedSearchSettingList(): Single<List<SearchSetting>> =
        getSearchSettingList().map { searchSettingList -> SearchSettingFactory.getAllSearchSetting().filter { it !in searchSettingList } }

    fun insertSearchSetting(searchSetting: SearchSetting): Completable = cache.insertSearchSetting(searchSettingMapper.mapToEntity(searchSetting))

    fun deleteSearchSetting(searchSetting: SearchSetting): Completable = cache.deleteSearchSetting(searchSettingMapper.mapToEntity(searchSetting))

    fun updateSearchSetting(searchSetting: SearchSetting): Completable = cache.updateSearchSetting(searchSettingMapper.mapToEntity(searchSetting))

    fun updateSearchSettingList(searchSettingList: List<SearchSetting>): Completable = deleteAll().andThen(insertSearchSettingList(searchSettingList))

    private fun insertSearchSettingList(searchSettingList: List<SearchSetting>): Completable =
        cache.insertSearchSettingList(searchSettingList.map { searchSettingMapper.mapToEntity(it) })

    fun deleteAll(): Completable = cache.deleteAll()
}