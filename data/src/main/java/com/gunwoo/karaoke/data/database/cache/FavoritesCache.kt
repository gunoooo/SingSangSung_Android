package com.gunwoo.karaoke.data.database.cache

import android.app.Application
import com.gunwoo.karaoke.data.base.BaseCache
import com.gunwoo.karaoke.data.database.entity.DownloadEntity
import com.gunwoo.karaoke.data.database.entity.FavoritesEntity
import com.gunwoo.karaoke.data.database.entity.RecordEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesCache @Inject constructor(application: Application) : BaseCache(application) {

    private val favoritesDao = database.favoritesDao()

    fun getFavoritesList(): Single<List<FavoritesEntity>> = favoritesDao.getFavoritesList()

    fun insertFavorites(favoritesEntity: FavoritesEntity): Completable = favoritesDao.insert(favoritesEntity)

    fun deleteFavorites(favoritesEntity: FavoritesEntity): Completable = favoritesDao.delete(favoritesEntity)
}