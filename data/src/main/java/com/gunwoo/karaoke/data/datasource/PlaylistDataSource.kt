package com.gunwoo.karaoke.data.datasource

import androidx.core.text.HtmlCompat
import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.PlaylistCache
import com.gunwoo.karaoke.data.database.entity.PlaylistEntity
import com.gunwoo.karaoke.data.mapper.PlaylistMapper
import com.gunwoo.karaoke.data.network.remote.PlaylistRemote
import com.gunwoo.karaoke.data.util.trimTitle
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.youtuberesponse.playlist.PlaylistItem
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class PlaylistDataSource @Inject constructor(
    override val remote: PlaylistRemote,
    override val cache: PlaylistCache
): BaseDataSource<PlaylistRemote, PlaylistCache>() {

    private val playlistMapper = PlaylistMapper()

    fun getPlaylistsList(id: String): Single<List<YoutubeData>> =
        cache.getPlaylistsList(id).onErrorResumeNext { getPlaylistsListRemote(id) }
            .map { playlistEntityList -> playlistEntityList.map { playlistMapper.mapToModel(it) } }

    fun deleteAllPlaylist(): Completable = cache.deleteAll()

    private fun getPlaylistsListRemote(id: String): Single<List<PlaylistEntity>> =
        remote.getPlaylistsList(id)
            .map { playlistItem ->
                playlistItem.map {
                    playlistMapper.mapToEntity(
                        YoutubeData(
                            it.snippet.resourceId?.videoId,
                            it.snippet.thumbnails?.getThumbnailUrl(),
                            HtmlCompat.fromHtml(it.snippet.title.trimTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT).toString(),
                            HtmlCompat.fromHtml(
                                it.snippet.channelTitle,
                                HtmlCompat.FROM_HTML_MODE_COMPACT
                            ).toString(),
                            YoutubeData.State.NONE,
                            id,
                            null
                        )
                    )
                }
            }.flatMap { playlistsList -> cache.insertPlaylistsList(playlistsList).toSingleDefault(playlistsList) }
}