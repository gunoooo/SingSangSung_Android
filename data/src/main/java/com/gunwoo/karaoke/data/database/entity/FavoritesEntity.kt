package com.gunwoo.karaoke.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class FavoritesEntity(
    val title: String,
    val createDate: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}