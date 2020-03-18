package com.gunwoo.karaoke.data.network.service

import com.google.api.services.youtube.model.PlaylistItemListResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaylistService {
    @GET("playlists")
    fun getPlaylistsList(
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int,
        @Query("id") id: String
        ): Single<Response<PlaylistItemListResponse>>
}