package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.SearchSettingDataSource
import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.domain.repository.SearchSettingRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SearchSettingRepositoryImpl @Inject constructor(
    private val searchSettingDataSource: SearchSettingDataSource
) : SearchSettingRepository {

    override fun getSearchSettingList(): Single<List<SearchSetting>> {
        return searchSettingDataSource.getSearchSettingList()
    }

    override fun getNotSelectedSearchSettingList(): Single<List<SearchSetting>> {
        return searchSettingDataSource.getNotSelectedSearchSettingList()
    }

    override fun insertSearchSetting(searchSetting: SearchSetting): Completable {
        return searchSettingDataSource.insertSearchSetting(searchSetting)
    }

    override fun deleteSearchSetting(searchSetting: SearchSetting): Completable {
        return searchSettingDataSource.deleteSearchSetting(searchSetting)
    }

    override fun updateSearchSetting(searchSetting: SearchSetting): Completable {
        return searchSettingDataSource.updateSearchSetting(searchSetting)
    }

    override fun updateSearchSettingList(searchSettingList: List<SearchSetting>): Completable {
        return searchSettingDataSource.updateSearchSettingList(searchSettingList)
    }
}