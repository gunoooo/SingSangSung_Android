package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.FavoritesCache
import com.gunwoo.karaoke.data.database.entity.FavoritesEntity
import com.gunwoo.karaoke.data.mapper.FavoritesMapper
import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesDataSource @Inject constructor(
    override val remote: Any,
    override val cache: FavoritesCache
): BaseDataSource<Any, FavoritesCache>() {

    private val favoritesMapper = FavoritesMapper()

    fun getFavoritesList(): Single<List<YoutubeData>> =
        cache.getFavoritesList().map { favoritesList -> favoritesList.map { favoritesMapper.mapToModel(it) } }

    fun insertFavorites(youtubeData: YoutubeData): Completable = cache.insertFavorites(favoritesMapper.mapToEntity(youtubeData))

    fun deleteFavorites(youtubeData: YoutubeData): Completable = cache.deleteFavorites(favoritesMapper.mapToEntity(youtubeData))
}