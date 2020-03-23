package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.DownloadDataSource
import com.gunwoo.karaoke.data.datasource.FavoritesDataSource
import com.gunwoo.karaoke.data.datasource.HidingDataSource
import com.gunwoo.karaoke.data.datasource.SearchDataSource
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
    private val favoritesDataSource: FavoritesDataSource,
    private val hidingDataSource: HidingDataSource,
    private val downloadDataSource: DownloadDataSource
) : SearchRepository {

    private lateinit var searchList: List<YoutubeData>
    private lateinit var favoritesList: List<YoutubeData>
    private lateinit var hidingList: List<YoutubeData>
    private lateinit var downloadList: List<Download>

    override fun getSearchList(search: String): Single<List<YoutubeData>> {
        return searchDataSource.getSearchList(search).flatMap { searchList -> this.searchList = searchList
            favoritesDataSource.getFavoritesList().flatMap { favoritesList -> this.favoritesList = favoritesList
                hidingDataSource.getHidingList().flatMap { hidingList -> this.hidingList = hidingList
                    downloadDataSource.getDownloadList().flatMap { downloadList -> this.downloadList = downloadList
                        Single.just(getResultSearchList())
                    }
                }
            }
        }.map { if (it.isEmpty()) throw Exception("검색 결과가 없습니다") else it }
    }

    private fun getResultSearchList(): List<YoutubeData> {
        val list = ArrayList<YoutubeData>()

        searchList.map { searchItem ->
            var isHidden = false

            hidingList.forEach { hiding ->
                if (hiding.videoId == searchItem.videoId)
                    isHidden = true
            }

            if (!isHidden)
                list.add(searchItem)
        }

        list.forEach { playlistItem ->
            favoritesList.forEach { favorites ->
                @Suppress("LABEL_NAME_CLASH")
                if (playlistItem.videoId == favorites.videoId) {
                    playlistItem.state = YoutubeData.State.FAVORITES

                    downloadList.forEach { download ->
                        if (favorites.videoId == download.videoId) {
                            playlistItem.state = YoutubeData.State.FAVORITES_AND_DOWNLOAD

                            return@forEach
                        }
                    }

                    return@forEach
                }
            }
        }

        return list
    }
}