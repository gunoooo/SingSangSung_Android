package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.DownloadDataSource
import com.gunwoo.karaoke.data.datasource.FavoritesDataSource
import com.gunwoo.karaoke.data.datasource.HidingDataSource
import com.gunwoo.karaoke.data.mapper.FavoritesMapper
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.FavoritesRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDataSource: FavoritesDataSource,
    private val downloadDataSource: DownloadDataSource
) : FavoritesRepository {

    private lateinit var favoritesList: List<YoutubeData>
    private lateinit var downloadList: List<Download>

    override fun getFavoritesList(): Single<List<YoutubeData>> {
        return favoritesDataSource.getFavoritesList().flatMap { favoritesList -> this.favoritesList = favoritesList
            downloadDataSource.getDownloadList().flatMap { downloadList -> this.downloadList = downloadList
                Single.just(getResultFavoritesList())
            }
        }.map { if (it.isEmpty()) throw Exception("즐겨찾기 리스트가 없습니다") else it }
    }

    private fun getResultFavoritesList(): List<YoutubeData> {
        favoritesList.forEach { favorites ->
            downloadList.forEach { download ->
                @Suppress("LABEL_NAME_CLASH")
                if (favorites.videoId == download.videoId) {
                    favorites.state = YoutubeData.State.FAVORITES_AND_DOWNLOAD
                    return@forEach
                }
            }
        }

        return favoritesList
    }

    override fun insertFavorites(youtubeData: YoutubeData): Completable {
        return favoritesDataSource.insertFavorites(youtubeData)
    }

    override fun deleteFavorites(youtubeData: YoutubeData): Completable {
        return favoritesDataSource.deleteFavorites(youtubeData)
    }
}