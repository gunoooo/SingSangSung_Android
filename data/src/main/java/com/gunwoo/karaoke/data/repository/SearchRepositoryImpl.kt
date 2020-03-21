package com.gunwoo.karaoke.data.repository

import androidx.core.text.HtmlCompat
import com.gunwoo.karaoke.data.datasource.SearchDataSource
import com.gunwoo.karaoke.data.util.trimTitle
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {

    override fun getSearchList(search: String): Single<List<YoutubeData>> {
        return searchDataSource.getSearchList(search).map { searchList ->
            searchList.map {
                YoutubeData(
                    it.id.videoId,
                    it.snippet.thumbnails?.getThumbnailUrl(),
                    HtmlCompat.fromHtml(it.snippet.title.trimTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT).toString(),
                    HtmlCompat.fromHtml(
                        it.snippet.channelTitle,
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    ).toString()
                )
            }
        }
    }
}