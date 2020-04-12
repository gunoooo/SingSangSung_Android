package com.gunwoo.karaoke.domain.repository

import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Completable
import io.reactivex.Single

interface FavoritesRepository {
    fun getFavoritesList(): Single<List<Favorites>>

    fun getFavoritesList(videoId: String): Single<List<Favorites>>

    fun insertFavorites(favorites: Favorites): Completable

    fun updateFavorites(id: Int, title: String): Completable

    fun deleteFavorites(id: Int): Completable

    fun insertFavoritesItem(youtubeData: YoutubeData, favoritesId: Int): Completable

    fun deleteFavoritesItem(youtubeData: YoutubeData, favoritesId: Int): Completable
}