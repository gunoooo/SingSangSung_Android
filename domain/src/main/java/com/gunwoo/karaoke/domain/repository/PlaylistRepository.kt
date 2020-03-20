package com.gunwoo.karaoke.domain.repository

import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Single

interface PlaylistRepository {
    fun getPlaylistsList(id: String): Single<List<YoutubeData>>
}