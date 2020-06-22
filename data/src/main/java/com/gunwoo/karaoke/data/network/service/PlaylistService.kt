package com.gunwoo.karaoke.data.network.service

import com.gunwoo.karaoke.domain.model.PlaylistItem
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaylistService {
    @GET("playlist")
    fun getPlaylistsList(
        @Query("playlistId") playlistId: String
    ): Single<Response<List<PlaylistItem>>>
}