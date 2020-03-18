package com.gunwoo.karaoke.data.network.remote

import com.google.api.services.youtube.model.SearchListResponse
import com.gunwoo.karaoke.data.base.BaseRemote
import com.gunwoo.karaoke.data.network.service.SearchService
import com.gunwoo.karaoke.data.util.Constants
import io.reactivex.Single

class SearchRemote(override val service: SearchService) : BaseRemote<SearchService>() {

    fun getSearchList(channelId: String, search: String): Single<SearchListResponse> =
        service.getSearchList(Constants.PART_SNIPPET, channelId, Constants.MAX_RESULT, Constants.TYPE, search).map(getResponse())
}