package com.gunwoo.karaoke.data.repository

import androidx.core.text.HtmlCompat
import com.gunwoo.karaoke.data.datasource.PlaylistDataSource
import com.gunwoo.karaoke.data.util.trimTitle
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import io.reactivex.Single
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDataSource: PlaylistDataSource
) : PlaylistRepository {

    override fun getPlaylistsList(id: String): Single<List<YoutubeData>> {
        return playlistDataSource.getPlaylistsList(id).map { playlistList ->
            playlistList.map {
                YoutubeData(
                    it.snippet.resourceId?.videoId,
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