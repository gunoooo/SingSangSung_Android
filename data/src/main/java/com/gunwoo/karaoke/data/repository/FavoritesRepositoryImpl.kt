package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.FavoritesDataSource
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.FavoritesRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDataSource: FavoritesDataSource
) : FavoritesRepository {

    override fun getFavoritesList(): Single<List<YoutubeData>> {
        return favoritesDataSource.getFavoritesList().map { if (it.isEmpty()) throw Exception("즐겨찾기 리스트가 없습니다") else it }
    }

    override fun insertFavorites(youtubeData: YoutubeData): Completable {
        return favoritesDataSource.insertFavorites(youtubeData)
    }

    override fun deleteFavorites(youtubeData: YoutubeData): Completable {
        return favoritesDataSource.deleteFavorites(youtubeData)
    }
}