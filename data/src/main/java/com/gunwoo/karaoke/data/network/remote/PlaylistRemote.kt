package com.gunwoo.karaoke.data.network.remote

import com.gunwoo.karaoke.data.base.BaseRemote
import com.gunwoo.karaoke.data.network.service.PlaylistService
import com.gunwoo.karaoke.domain.model.PlaylistItem
import io.reactivex.Single

class PlaylistRemote(override val service: PlaylistService) : BaseRemote<PlaylistService>() {

    fun getPlaylistsList(id: String): Single<List<PlaylistItem>> =
        service.getPlaylistsList(id).map(getResponse())
}