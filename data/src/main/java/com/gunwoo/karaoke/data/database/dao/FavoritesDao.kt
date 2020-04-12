package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.FavoritesEntity
import com.gunwoo.karaoke.data.database.entity.FavoritesWithItemEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoritesDao : BaseDao<FavoritesEntity> {

    @Query("SELECT * FROM favorites_table")
    fun getFavoritesWithItemList(): Single<List<FavoritesWithItemEntity>>

    @Query("UPDATE favorites_table SET title=:title WHERE id=:id")
    fun updateFavorites(id: Int, title: String): Completable

    @Query("DELETE FROM favorites_table WHERE id=:id")
    fun deleteFavorites(id: Int): Completable
}