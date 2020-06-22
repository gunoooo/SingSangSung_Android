package com.gunwoo.karaoke.data.network.remote

import com.gunwoo.karaoke.data.base.BaseRemote
import com.gunwoo.karaoke.data.network.service.SearchService
import com.gunwoo.karaoke.domain.model.Search
import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.domain.model.youtuberesponse.search.SearchItem
import io.reactivex.Single

class SearchRemote(override val service: SearchService) : BaseRemote<SearchService>() {

    fun getSearchList(search: String, channelId: String, maxResults: Int): Single<List<Search>> =
        service.getSearchList(search, channelId, maxResults).map(getResponse())
}