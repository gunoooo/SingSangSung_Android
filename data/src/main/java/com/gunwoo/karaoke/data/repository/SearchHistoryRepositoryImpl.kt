package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.SearchHistoryDataSource
import com.gunwoo.karaoke.domain.repository.SearchHistoryRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor(
    private val searchHistoryDataSource: SearchHistoryDataSource
) : SearchHistoryRepository {

    override fun getSearchHistoryList(): Single<List<String>> {
        return searchHistoryDataSource.getSearchHistoryList()
    }

    override fun deleteSearchHistory(search: String): Completable {
        return searchHistoryDataSource.deleteSearchHistory(search)
    }

    override fun deleteAll(): Completable {
        return searchHistoryDataSource.deleteAll()
    }
}