package com.gunwoo.karaoke.data.database.cache

import android.app.Application
import androidx.room.EmptyResultSetException
import com.gunwoo.karaoke.data.base.BaseCache
import com.gunwoo.karaoke.data.database.entity.PlaylistEntity
import com.gunwoo.karaoke.data.database.entity.SearchEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SearchCache @Inject constructor(application: Application) : BaseCache(application) {

    private val searchDao = database.searchDao()

    fun getSearchList(search: String): Single<List<SearchEntity>> =
        searchDao.getSearchList(search)
            .flatMap {
                if (it.isEmpty()) Single.error(EmptyResultSetException("Search table is empty"))
                else Single.just(it)
            }

    fun insertSearchList(searchEntityList: List<SearchEntity>): Completable = searchDao.insert(searchEntityList)

    fun deleteAll(): Completable = searchDao.deleteAll()
}