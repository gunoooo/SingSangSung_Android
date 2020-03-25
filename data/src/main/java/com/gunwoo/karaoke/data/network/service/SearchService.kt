package com.gunwoo.karaoke.data.network.service

import com.gunwoo.karaoke.domain.model.youtuberesponse.search.SearchResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search?order=viewCount&part=snippet&maxResults=20&type=video&key=AIzaSyA1AA1ws32FEojCTyIqmtjOb8f5VKMOyf4")
    fun getSearchList(
        @Query("channelId") channelId: String,
        @Query("q") q: String
    ): Single<Response<SearchResponse>>
}