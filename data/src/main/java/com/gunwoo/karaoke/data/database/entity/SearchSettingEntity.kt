package com.gunwoo.karaoke.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_setting_table")
data class SearchSettingEntity(
    val channelId: String,
    val channelTitle: String,
    val maxResults: Int
) {

    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0
}