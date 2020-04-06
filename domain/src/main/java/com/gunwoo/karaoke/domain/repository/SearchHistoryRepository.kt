package com.gunwoo.karaoke.domain.repository

import io.reactivex.Completable
import io.reactivex.Single

interface SearchHistoryRepository {
    fun getSearchHistoryList(): Single<List<String>>

    fun deleteSearchHistory(search: String): Completable

    fun deleteAll(): Completable
}