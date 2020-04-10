package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.FavoritesItemEntity
import io.reactivex.Single

@Dao
interface FavoritesItemDao : BaseDao<FavoritesItemEntity> {

    @Query("SELECT * FROM favorites_item_table")
    fun getFavoritesItemList(): Single<List<FavoritesItemEntity>>
}