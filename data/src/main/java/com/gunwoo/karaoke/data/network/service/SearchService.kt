package com.gunwoo.karaoke.data.network.service

import com.gunwoo.karaoke.domain.model.Search
import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.domain.model.youtuberesponse.search.SearchResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search")
    fun getSearchList(
        @Query("search") search: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int
    ): Single<Response<List<Search>>>
}