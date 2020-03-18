package com.gunwoo.karaoke.data.network.service

import com.google.api.services.youtube.model.SearchListResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search")
    fun getSearchList(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int,
        @Query("type") type: String,
        @Query("q") q: String
    ): Single<Response<SearchListResponse>>
}