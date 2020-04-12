package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.FavoritesDataSource
import com.gunwoo.karaoke.data.datasource.FavoritesItemDataSource
import com.gunwoo.karaoke.data.exception.ListEmptyException
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
        return favoritesDataSource.getFavoritesList()
    }

    override fun getFavoritesList(videoId: String): Single<List<Favorites>> {
        return favoritesDataSource.getFavoritesList()
            .map { favoritesList ->
                favoritesList.forEach { it.checkFavoritesItemContained(videoId) }
                return@map favoritesList
            }
    }

    override fun insertFavorites(favorites: Favorites): Completable {
        return favoritesDataSource.insertFavorites(favorites)
    }

    override fun updateFavorites(id: Int, title: String): Completable {
        return favoritesDataSource.updateFavorites(id, title)
    }

    override fun deleteFavorites(id: Int): Completable {
        return favoritesDataSource.deleteFavorites(id)
    }

    override fun insertFavoritesItem(youtubeData: YoutubeData, favoritesId: Int): Completable {
        return favoritesItemDataSource.insertFavoritesItem(youtubeData, favoritesId)
    }

    override fun deleteFavoritesItem(youtubeData: YoutubeData, favoritesId: Int): Completable {
        return favoritesItemDataSource.deleteFavoritesItem(youtubeData, favoritesId)
    }
}