package com.gunwoo.karaoke.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FavoritesWithItemEntity(
    @Embedded
    val favorites: FavoritesEntity,
    @Relation(parentColumn = "id", entityColumn = "favoritesId")
    val favoritesItemList: List<FavoritesItemEntity>
)