package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.SearchHistoryCache
import com.gunwoo.karaoke.data.database.entity.SearchHistoryEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SearchHistoryDataSource @Inject constructor(
    override val remote: Any,
    override val cache: SearchHistoryCache
) : BaseDataSource<Any, SearchHistoryCache>() {

    fun getSearchHistoryList(): Single<List<String>> = cache.getSearchHistoryList()
        .map { searchHistoryList -> searchHistoryList.sortedByDescending { it.idx }.map { it.search }.distinct().take(6) }

    fun insertSearchHistory(search: String): Completable = cache.insertSearchHistory(SearchHistoryEntity(search))

    fun deleteSearchHistory(search: String): Completable = cache.deleteSearchHistory(search)

    fun deleteAll(): Completable = cache.deleteAll()
}