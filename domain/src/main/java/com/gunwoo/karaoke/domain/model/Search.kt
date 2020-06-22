package com.gunwoo.karaoke.domain.model

data class Search(
    val searchVideoId: String,
    val videoId: String,
    val channelId: String,
    val search: String,
    val thumbnailUrl: String?,
    val videoTitle: String,
    val channelTitle: String
)