package com.gunwoo.karaoke.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history_table")
data class SearchHistoryEntity(
    val search: String
) {

    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0
}