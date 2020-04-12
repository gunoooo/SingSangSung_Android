package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.FavoritesCache
import com.gunwoo.karaoke.data.database.cache.FavoritesItemCache
import com.gunwoo.karaoke.data.mapper.FavoritesItemMapper
import com.gunwoo.karaoke.data.mapper.FavoritesMapper
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.domain.model.YoutubeData
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesDataSource @Inject constructor(
    override val remote: Any,
    override val cache: FavoritesCache
): BaseDataSource<Any, FavoritesCache>() {

    private val favoritesMapper = FavoritesMapper()

    fun getFavoritesList(): Single<List<Favorites>> =
        cache.getFavoritesWithItemList().map { favoritesWithItemEntityList -> favoritesWithItemEntityList.map { favoritesMapper.mapToModel(it) } }

    fun insertFavorites(favorites: Favorites): Completable =
        cache.insertFavorites(favoritesMapper.mapToEntity(favorites))

    fun updateFavorites(id: Int, title: String): Completable =
        cache.updateFavorites(id, title)

    fun deleteFavorites(id: Int): Completable =
        cache.deleteFavorites(id)
}