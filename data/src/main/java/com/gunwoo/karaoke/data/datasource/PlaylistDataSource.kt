package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.network.remote.PlaylistRemote
import com.gunwoo.karaoke.domain.model.youtuberesponse.playlist.PlaylistItem
import io.reactivex.Single
import javax.inject.Inject

class PlaylistDataSource @Inject constructor(
    override val remote: PlaylistRemote,
    override val cache: Any
): BaseDataSource<PlaylistRemote, Any>() {

    fun getPlaylistsList(id: String): Single<List<PlaylistItem>> = remote.getPlaylistsList(id)
}