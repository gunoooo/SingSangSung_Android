package com.gunwoo.karaoke.data.database.cache

import android.app.Application
import androidx.room.EmptyResultSetException
import com.gunwoo.karaoke.data.base.BaseCache
import com.gunwoo.karaoke.data.database.entity.PlaylistEntity
import com.gunwoo.karaoke.data.database.entity.SearchEntity
import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class SearchCache @Inject constructor(application: Application) : BaseCache(application) {

    private val searchDao = database.searchDao()

    fun getSearchList(search: String, channelId: String, maxResults: Int): Single<List<SearchEntity>> {
        return searchDao.getSearchList(search, channelId)
            .flatMap {
                if (it.isEmpty()) Single.error(EmptyResultSetException("Search table is empty"))
                else Single.just(it)
            }
            .flatMap {
                if (it.size < maxResults) Single.error(Exception("MaxResults require more table size"))
                else Single.just(it.take(maxResults))
            }
    }

    fun insertSearchList(searchEntityList: List<SearchEntity>): Completable = searchDao.insert(searchEntityList)

    fun deleteAll(): Completable = searchDao.deleteAll()
}