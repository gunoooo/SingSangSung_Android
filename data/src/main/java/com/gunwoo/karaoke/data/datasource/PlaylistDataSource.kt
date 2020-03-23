package com.gunwoo.karaoke.data.datasource

import androidx.core.text.HtmlCompat
import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.network.remote.PlaylistRemote
import com.gunwoo.karaoke.data.util.trimTitle
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.youtuberesponse.playlist.PlaylistItem
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class PlaylistDataSource @Inject constructor(
    override val remote: PlaylistRemote,
    override val cache: Any
): BaseDataSource<PlaylistRemote, Any>() {

    fun getPlaylistsList(id: String): Single<List<YoutubeData>> =
        remote.getPlaylistsList(id)
            .map { playlistItem ->
                playlistItem.map {
                    YoutubeData(
                        it.snippet.resourceId?.videoId,
                        it.snippet.thumbnails?.getThumbnailUrl(),
                        HtmlCompat.fromHtml(it.snippet.title.trimTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT).toString(),
                        HtmlCompat.fromHtml(
                            it.snippet.channelTitle,
                            HtmlCompat.FROM_HTML_MODE_COMPACT
                        ).toString(),
                        YoutubeData.State.NONE
                    )
                }
            }
}