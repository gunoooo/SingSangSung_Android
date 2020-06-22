package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.SearchCache
import com.gunwoo.karaoke.data.database.entity.SearchEntity
import com.gunwoo.karaoke.data.mapper.SearchMapper
import com.gunwoo.karaoke.data.network.remote.SearchRemote
import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    override val remote: SearchRemote,
    override val cache: SearchCache
) : BaseDataSource<SearchRemote, SearchCache>() {

    private val searchMapper = SearchMapper()

    fun getSearchList(search: String, channelId: String, maxResults: Int): Single<List<YoutubeData>> =
        cache.getSearchList(search, channelId, maxResults).onErrorResumeNext { getSearchListRemote(search, channelId, maxResults) }
            .map { searchEntityList -> searchEntityList.map { searchMapper.mapToYoutubeData(it) } }

    fun deleteAllPlaylist(): Completable = cache.deleteAll()

    private fun getSearchListRemote(search: String, channelId: String, maxResults: Int): Single<List<SearchEntity>> {
        return remote.getSearchList(search, channelId, maxResults).map { searchList -> searchList.map { searchMapper.mapToEntity(it) } }
            .flatMap { searchList -> cache.insertSearchList(searchList).toSingleDefault(searchList) }
    }
}