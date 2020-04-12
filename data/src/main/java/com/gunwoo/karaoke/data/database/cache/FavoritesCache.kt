package com.gunwoo.karaoke.data.database.cache

import android.app.Application
import com.gunwoo.karaoke.data.base.BaseCache
import com.gunwoo.karaoke.data.database.entity.FavoritesEntity
import com.gunwoo.karaoke.data.database.entity.FavoritesWithItemEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesCache @Inject constructor(application: Application) : BaseCache(application) {

    private val favoritesDao = database.favoritesDao()

    fun getFavoritesWithItemList(): Single<List<FavoritesWithItemEntity>> = favoritesDao.getFavoritesWithItemList()

    fun insertFavorites(favoritesEntity: FavoritesEntity): Completable = favoritesDao.insert(favoritesEntity)

    fun updateFavorites(id: Int, title: String): Completable = favoritesDao.updateFavorites(id, title)

    fun deleteFavorites(id: Int): Completable = favoritesDao.deleteFavorites(id)
}