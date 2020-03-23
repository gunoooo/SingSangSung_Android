package com.gunwoo.karaoke.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "download_table")
data class DownloadEntity(
    @PrimaryKey
    val videoId: String,
    val title: String,
    val thumbnailPath: String,
    val thumbnailUrl: String?,
    val videoPath: String
)