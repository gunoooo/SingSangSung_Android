package com.gunwoo.karaoke.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_item_table",
    primaryKeys = ["favoritesId", "videoId"])
data class FavoritesItemEntity(
    val favoritesId: Int,
    val videoId: String,
    val thumbnailUrl: String?,
    val videoTitle: String,
    val channelTitle: String
)