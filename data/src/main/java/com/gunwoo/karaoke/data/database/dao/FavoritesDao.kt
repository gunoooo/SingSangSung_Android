package com.gunwoo.karaoke.data.database.dao

import androidx.room.Dao
import com.gunwoo.karaoke.data.base.BaseDao
import com.gunwoo.karaoke.data.database.entity.FavoritesEntity

@Dao
interface FavoritesDao : BaseDao<FavoritesEntity>