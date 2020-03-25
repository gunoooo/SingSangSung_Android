package com.gunwoo.karaoke.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_table")
data class RecentEntity(
    val videoId: String,
    val thumbnailUrl: String?,
    val videoTitle: String,
    val channelTitle: String
) {

    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0
}