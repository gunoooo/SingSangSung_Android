package com.gunwoo.karaoke.data.datasource

import androidx.core.text.HtmlCompat
import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.SearchCache
import com.gunwoo.karaoke.data.database.entity.SearchEntity
import com.gunwoo.karaoke.data.mapper.SearchMapper
import com.gunwoo.karaoke.data.network.remote.SearchRemote
import com.gunwoo.karaoke.data.util.trimTitle
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
            .map { searchEntityList -> searchEntityList.map { searchMapper.mapToModel(it) } }

    fun deleteAllPlaylist(): Completable = cache.deleteAll()

    private fun getSearchListRemote(search: String, channelId: String, maxResults: Int): Single<List<SearchEntity>> {
        return remote.getSearchList(channelId, search, maxResults).map { searchItem ->
            searchItem.map {
                searchMapper.mapToEntity(
                    YoutubeData(
                        it.id.videoId,
                        it.snippet.thumbnails?.getThumbnailUrl(),
                        HtmlCompat.fromHtml(
                            it.snippet.title.trimTitle(),
                            HtmlCompat.FROM_HTML_MODE_COMPACT
                        ).toString(),
                        HtmlCompat.fromHtml(
                            it.snippet.channelTitle,
                            HtmlCompat.FROM_HTML_MODE_COMPACT
                        ).toString(),
                        null,
                        channelId,
                        search
                    )
                )
            }
        }.flatMap { searchList -> cache.insertSearchList(searchList).toSingleDefault(searchList) }
    }
}