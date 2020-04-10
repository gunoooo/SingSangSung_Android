package com.gunwoo.karaoke.domain.model

import java.io.Serializable

data class YoutubeData(
    val videoId: String?,
    val thumbnailUrl: String?,
    val videoTitle: String,
    val channelTitle: String,

    val playlistId: String?,
    val channelId: String?,
    val search: String?
) : Serializable {

    constructor(videoId: String?, thumbnailUrl: String?, videoTitle: String, channelTitle: String):
            this(videoId, thumbnailUrl, videoTitle, channelTitle, null, null, null)

    var isHiding = false
}