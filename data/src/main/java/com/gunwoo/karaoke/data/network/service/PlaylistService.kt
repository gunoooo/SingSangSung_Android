package com.gunwoo.karaoke.data.network.service

import com.gunwoo.karaoke.domain.model.youtuberesponse.playlist.PlaylistResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaylistService {
    @GET("playlistItems?part=snippet&maxResults=50&type=video")
    fun getPlaylistsList(
        @Query("playlistId") playlistId: String
    ): Single<Response<PlaylistResponse>>
}