package com.gunwoo.karaoke.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist_table")
data class PlaylistEntity(
    @PrimaryKey
    val playlistVideoId: String,
    val videoId: String,
    val playlistId: String,
    val channelId: String,
    val thumbnailUrl: String?,
    val videoTitle: String,
    val channelTitle: String
)