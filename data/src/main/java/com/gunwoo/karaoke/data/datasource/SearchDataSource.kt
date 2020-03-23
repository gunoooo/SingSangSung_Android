package com.gunwoo.karaoke.data.datasource

import androidx.core.text.HtmlCompat
import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.network.remote.SearchRemote
import com.gunwoo.karaoke.data.util.Constants
import com.gunwoo.karaoke.data.util.trimTitle
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.youtuberesponse.search.SearchItem
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    override val remote: SearchRemote,
    override val cache: Any
) : BaseDataSource<SearchRemote, Any>() {

    fun getSearchList(search: String): Single<List<YoutubeData>> {
        val searchList = ArrayList<SearchItem>()

        return remote.getSearchList(Constants.KY_CHANNEL_ID, search).flatMap { kyChannelResponse ->
            searchList.addAll(kyChannelResponse.filter { item -> isContains(item, "[KY 금영노래방]", "[KY ENTERTAINMENT]") })

            remote.getSearchList(Constants.MO_CHANNEL_ID, search).flatMap { moChannelResponse ->
                searchList.addAll(moChannelResponse)

                remote.getSearchList(Constants.ZZANG_CHANNEL_ID, search).flatMap { zzangRespnse ->
                    searchList.addAll(zzangRespnse)

                    remote.getSearchList(Constants.LALA_CHANNEL_ID, search).flatMap { lalaResponse ->
                        searchList.addAll(lalaResponse)

                        remote.getSearchList(Constants.CHILD_CHANNEL_ID, search).flatMap { childResponse ->
                            searchList.addAll(childResponse)

                            Single.just(searchList)
                        }
                    }
                }
            }
        }.map { searchItem ->
            searchItem.map {
                YoutubeData(
                    it.id.videoId,
                    it.snippet.thumbnails?.getThumbnailUrl(),
                    HtmlCompat.fromHtml(
                        it.snippet.title.trimTitle(),
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    ).toString(),
                    HtmlCompat.fromHtml(
                        it.snippet.channelTitle,
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    ).toString(),
                    YoutubeData.State.NONE
                )
            }
        }
    }

    private fun isContains(searchItem: SearchItem, vararg other: String): Boolean =
        HtmlCompat.fromHtml(searchItem.snippet.title, HtmlCompat.FROM_HTML_MODE_COMPACT).toString().contains(other[0]) ||
                HtmlCompat.fromHtml(searchItem.snippet.title, HtmlCompat.FROM_HTML_MODE_COMPACT).toString().contains(other[1])

}