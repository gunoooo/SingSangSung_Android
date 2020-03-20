package com.gunwoo.karaoke.domain.model.youtuberesponse

data class ResourceId(
    val channelId: String,
    val kind: String,
    val playlistId: String,
    val videoId: String
)