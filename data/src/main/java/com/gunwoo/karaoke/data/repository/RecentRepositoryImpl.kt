package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.FavoritesItemDataSource
import com.gunwoo.karaoke.data.datasource.HidingDataSource
import com.gunwoo.karaoke.data.datasource.RecentDataSource
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.RecentRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecentRepositoryImpl @Inject constructor(
    private val recentDataSource: RecentDataSource,
    private val favoritesItemDataSource: FavoritesItemDataSource,
    private val hidingDataSource: HidingDataSource
) : RecentRepository {

    private lateinit var recentList: List<YoutubeData>
    private lateinit var favoritesItemList: List<YoutubeData>
    private lateinit var hidingList: List<YoutubeData>

    override fun getRecentList(): Single<List<YoutubeData>> {
        return recentDataSource.getRecentList().flatMap { recentList -> this.recentList = recentList
            favoritesItemDataSource.getFavoritesItemList().flatMap { favoritesItemList -> this.favoritesItemList = favoritesItemList
                hidingDataSource.getHidingList().flatMap { hidingList -> this.hidingList = hidingList
                    Single.just(getResultRecentList())
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

        return list
    }
}