package com.gunwoo.karaoke.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record_table")
data class RecordEntity(
    val title: String,
    val thumbnail: String,
    val time: String,
    val path: String
) {

    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0
}