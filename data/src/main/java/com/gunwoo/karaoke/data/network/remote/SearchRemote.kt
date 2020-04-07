package com.gunwoo.karaoke.data.network.remote

import com.gunwoo.karaoke.data.base.BaseRemote
import com.gunwoo.karaoke.data.network.service.SearchService
import com.gunwoo.karaoke.data.util.Constants
import com.gunwoo.karaoke.domain.model.youtuberesponse.search.SearchItem
import io.reactivex.Single

class SearchRemote(override val service: SearchService) : BaseRemote<SearchService>() {

    fun getSearchList(channelId: String, search: String, maxResults: Int): Single<List<SearchItem>> =
        service.getSearchList(channelId, search, maxResults).map(getResponse()).map { it.items }
}