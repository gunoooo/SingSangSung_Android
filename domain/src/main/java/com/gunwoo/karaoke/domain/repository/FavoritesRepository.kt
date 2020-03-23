package com.gunwoo.karaoke.domain.repository

import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Completable
import io.reactivex.Single

interface FavoritesRepository {
    fun getFavoritesList(): Single<List<YoutubeData>>

    fun insertFavorites(youtubeData: YoutubeData): Completable

    fun deleteFavorites(youtubeData: YoutubeData): Completable
}