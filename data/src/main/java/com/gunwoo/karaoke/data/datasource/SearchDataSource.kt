package com.gunwoo.karaoke.data.datasource

import androidx.core.text.HtmlCompat
import com.google.api.services.youtube.model.SearchResult
import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.network.remote.SearchRemote
import com.gunwoo.karaoke.data.util.Constants
import io.reactivex.Single
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    override val remote: SearchRemote,
    override val cache: Any
) : BaseDataSource<SearchRemote, Any>() {

    fun getSearchList(search: String): Single<List<SearchResult>> {
        val searchList = ArrayList<SearchResult>()

        return remote.getSearchList(Constants.TJ_CHANNEL_ID, search).flatMap { tjChannelResponse ->
            searchList.addAll(tjChannelResponse.items.filter { item -> isContains(item, "[TJ 노래방]") })

            remote.getSearchList(Constants.KY_CHANNEL_ID, search).flatMap { kyChannelResponse ->
                searchList.addAll(kyChannelResponse.items.filter { item -> isContains(item, "[KY ENTERTAINMENT]") })
                searchList.addAll(kyChannelResponse.items.filter { item -> isContains(item, "[KY 금영노래방]") })

                remote.getSearchList(Constants.CHILD_CHANNEL_ID, search).flatMap { childResponse ->
                    searchList.addAll(childResponse.items.filter { item -> isContains(item, "[동요 노래방]") })

                    return@flatMap Single.just(searchList)
                }
            }
        }
    }

    private fun isContains(searchResult: SearchResult, other: String): Boolean =
        HtmlCompat.fromHtml(searchResult.snippet.title, HtmlCompat.FROM_HTML_MODE_COMPACT).toString().contains(other)
}