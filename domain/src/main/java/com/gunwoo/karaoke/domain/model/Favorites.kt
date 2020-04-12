package com.gunwoo.karaoke.domain.model

data class Favorites(
    val id: Int?,
    val title: String,
    val createDate: String,
    val favoritesItemList: List<YoutubeData>?
) {

    constructor(title: String, createDate: String): this(null, title, createDate, null)

    var isContained: Boolean = false

    fun checkFavoritesItemContained(videoId: String) {
        isContained = favoritesItemList?.any { it.videoId == videoId } ?: false
    }
}