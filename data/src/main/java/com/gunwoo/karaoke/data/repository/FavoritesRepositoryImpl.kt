package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.FavoritesDataSource
import com.gunwoo.karaoke.data.datasource.FavoritesItemDataSource
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.FavoritesRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDataSource: FavoritesDataSource,
    private val favoritesItemDataSource: FavoritesItemDataSource
) : FavoritesRepository {

    override fun getFavoritesList(): Single<List<Favorites>> {
        return favoritesDataSource.getFavoritesList().map { if (it.isEmpty()) throw Exception("즐겨찾기 리스트가 없습니다") else it }
    }

    override fun insertFavorites(favorites: Favorites): Completable {
        return favoritesDataSource.insertFavorites(favorites)
    }

    override fun deleteFavorites(favorites: Favorites): Completable {
        return favoritesDataSource.deleteFavorites(favorites)
    }

    override fun insertFavoritesItem(youtubeData: YoutubeData, favoritesId: Int): Completable {
        return favoritesItemDataSource.insertFavoritesItem(youtubeData, favoritesId)
    }

    override fun deleteFavoritesItem(youtubeData: YoutubeData, favoritesId: Int): Completable {
        return favoritesItemDataSource.deleteFavoritesItem(youtubeData, favoritesId)
    }
}