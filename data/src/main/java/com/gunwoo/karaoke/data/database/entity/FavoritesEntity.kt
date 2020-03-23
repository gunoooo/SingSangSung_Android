package com.gunwoo.karaoke.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class FavoritesEntity(
    @PrimaryKey
    val videoId: String,
    val thumbnailUrl: String?,
    val videoTitle: String,
    val channelTitle: String
)