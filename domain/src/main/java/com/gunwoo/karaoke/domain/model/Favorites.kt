package com.gunwoo.karaoke.domain.model

data class Favorites(
    val id: Int?,
    val favoritesTitle: String,
    val createDate: String,
    val favoritesItemList: List<YoutubeData>?
) {

    constructor(favoritesTitle: String, createDate: String): this(null, favoritesTitle, createDate, null)

    var isContained: Boolean = false

    fun checkFavoritesItemContained(videoId: String) {
        isContained = favoritesItemList?.any { it.videoId == videoId } ?: false
    }
}