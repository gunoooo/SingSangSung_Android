package com.gunwoo.karaoke.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_table")
data class SearchEntity(
    @PrimaryKey
    val searchVideoId: String,
    val videoId: String,
    val channelId: String,
    val search: String,
    val thumbnailUrl: String?,
    val videoTitle: String,
    val channelTitle: String
)