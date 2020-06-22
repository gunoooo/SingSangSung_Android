package com.gunwoo.karaoke.domain.model

data class PlaylistItem(
    val playlistVideoId: String,
    val videoId: String,
    val playlistId: String,
    val channelId: String,
    val thumbnailUrl: String?,
    val videoTitle: String,
    val channelTitle: String
)