package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.DownloadDataSource
import com.gunwoo.karaoke.data.datasource.FavoritesDataSource
import com.gunwoo.karaoke.data.datasource.HidingDataSource
import com.gunwoo.karaoke.data.datasource.RecentDataSource
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.RecentRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecentRepositoryImpl @Inject constructor(
    private val recentDataSource: RecentDataSource,
    private val favoritesDataSource: FavoritesDataSource,
    private val hidingDataSource: HidingDataSource,
    private val downloadDataSource: DownloadDataSource
) : RecentRepository {

    private lateinit var recentList: List<YoutubeData>
    private lateinit var favoritesList: List<YoutubeData>
    private lateinit var hidingList: List<YoutubeData>
    private lateinit var downloadList: List<Download>

    override fun getRecentList(): Single<List<YoutubeData>> {
        return recentDataSource.getRecentList().flatMap { recentList -> this.recentList = recentList
            favoritesDataSource.getFavoritesList().flatMap { favoritesList -> this.favoritesList = favoritesList
                hidingDataSource.getHidingList().flatMap { hidingList -> this.hidingList = hidingList
                    downloadDataSource.getDownloadList().flatMap { downloadList -> this.downloadList = downloadList
                        Single.just(getResultRecentList())
                    }
                }
            }
        }
    }

    override fun insertRecent(youtubeData: YoutubeData): Completable {
        return recentDataSource.insertRecent(youtubeData)
    }

    private fun getResultRecentList(): List<YoutubeData> {
        val list = ArrayList<YoutubeData>()

        recentList.map { recent ->
            var isHidden = false

            hidingList.forEach { hiding ->
                if (hiding.videoId == recent.videoId)
                    isHidden = true
            }

            if (!isHidden)
                list.add(recent)
        }

        list.forEach { recent ->
            favoritesList.forEach { favorites ->
                @Suppress("LABEL_NAME_CLASH")
                if (recent.videoId == favorites.videoId) {
                    recent.state = YoutubeData.State.FAVORITES

                    downloadList.forEach { download ->
                        if (favorites.videoId == download.videoId) {
                            recent.state = YoutubeData.State.FAVORITES_AND_DOWNLOAD

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