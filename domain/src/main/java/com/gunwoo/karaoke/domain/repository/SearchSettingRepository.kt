package com.gunwoo.karaoke.domain.repository

import com.gunwoo.karaoke.domain.model.SearchSetting
import io.reactivex.Completable
import io.reactivex.Single

interface SearchSettingRepository {
    fun getSearchSettingList(): Single<List<SearchSetting>>

    fun getNotSelectedSearchSettingList(): Single<List<SearchSetting>>

    fun insertSearchSetting(searchSetting: SearchSetting): Completable

    fun deleteSearchSetting(searchSetting: SearchSetting): Completable

    fun updateSearchSetting(searchSetting: SearchSetting): Completable

    fun updateSearchSettingList(searchSettingList: List<SearchSetting>): Completable
}