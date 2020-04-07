package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.FavoritesEntity
import io.reactivex.Single

@Dao
interface FavoritesDao : BaseDao<FavoritesEntity> {

    @Query("SELECT * FROM favorites_table")
    fun getFavoritesList(): Single<List<FavoritesEntity>>
}