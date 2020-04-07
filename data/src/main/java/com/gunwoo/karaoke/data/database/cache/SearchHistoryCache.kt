package com.gunwoo.karaoke.data.database.cache

import android.app.Application
import com.gunwoo.karaoke.data.base.BaseCache
import com.gunwoo.karaoke.data.database.entity.SearchHistoryEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SearchHistoryCache @Inject constructor(application: Application) : BaseCache(application) {

    private val searchHistoryDao = database.searchHistoryDao()

    fun getSearchHistoryList(): Single<List<SearchHistoryEntity>> = searchHistoryDao.getSearchHistoryList()

    fun insertSearchHistory(searchHistoryEntity: SearchHistoryEntity): Completable = searchHistoryDao.insert(searchHistoryEntity)

    fun deleteSearchHistory(searchHistoryEntity: SearchHistoryEntity): Completable = searchHistoryDao.delete(searchHistoryEntity)

    fun deleteAll(): Completable = searchHistoryDao.deleteAll()
}