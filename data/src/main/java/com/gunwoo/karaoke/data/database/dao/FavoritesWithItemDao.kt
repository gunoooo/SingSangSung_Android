package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.FavoritesItemEntity
import com.gunwoo.karaoke.data.database.entity.FavoritesWithItemEntity
import io.reactivex.Single

@Dao
interface FavoritesWithItemDao : BaseDao<FavoritesItemEntity> {

    @Query("SELECT * FROM favorites_table")
    fun getFavoritesWithItemList(): Single<List<FavoritesWithItemEntity>>
}