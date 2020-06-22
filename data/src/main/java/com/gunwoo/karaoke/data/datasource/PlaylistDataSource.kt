package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.PlaylistCache
import com.gunwoo.karaoke.data.database.entity.PlaylistEntity
import com.gunwoo.karaoke.data.mapper.PlaylistMapper
import com.gunwoo.karaoke.data.network.remote.PlaylistRemote
import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PlaylistDataSource @Inject constructor(
    override val remote: PlaylistRemote,
    override val cache: PlaylistCache
): BaseDataSource<PlaylistRemote, PlaylistCache>() {

    private val playlistMapper = PlaylistMapper()

    fun getPlaylistsList(id: String): Single<List<YoutubeData>> =
        cache.getPlaylistsList(id).onErrorResumeNext { getPlaylistsListRemote(id) }
            .map { playlistEntityList -> playlistEntityList.map { playlistMapper.mapToYoutubeData(it) } }

    fun deleteAllPlaylist(): Completable = cache.deleteAll()

    private fun getPlaylistsListRemote(id: String): Single<List<PlaylistEntity>> =
        remote.getPlaylistsList(id).map { playlistList -> playlistList.map { playlistMapper.mapToEntity(it) } }
            .flatMap { playlistsList -> cache.insertPlaylistsList(playlistsList).toSingleDefault(playlistsList) }
}