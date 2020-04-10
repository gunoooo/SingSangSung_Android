package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.FavoritesItemCache
import com.gunwoo.karaoke.data.mapper.FavoritesItemMapper
import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesItemDataSource @Inject constructor(
    override val remote: Any,
    override val cache: FavoritesItemCache
): BaseDataSource<Any, FavoritesItemCache>() {

    private val favoritesItemMapper = FavoritesItemMapper()

    fun getFavoritesItemList(): Single<List<YoutubeData>> =
        cache.getFavoritesItemList().map { favoritesItemList -> favoritesItemList.map { favoritesItemMapper.mapToModel(it) } }

    fun insertFavoritesItem(youtubeData: YoutubeData, favoritesId: Int): Completable =
        cache.insertFavoritesItem(favoritesItemMapper.mapToEntity(youtubeData, favoritesId))

    fun deleteFavoritesItem(youtubeData: YoutubeData, favoritesId: Int): Completable =
        cache.deleteFavoritesItem(favoritesItemMapper.mapToEntity(youtubeData, favoritesId))
}