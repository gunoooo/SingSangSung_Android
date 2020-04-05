package com.gunwoo.karaoke.domain.model

import java.io.Serializable

data class YoutubeData(
    val videoId: String?,
    val thumbnailUrl: String?,
    val videoTitle: String,
    val channelTitle: String,
    var state: State,

    val playlistId: String?,
    val search: String?
) : Serializable {

    constructor(videoId: String?, thumbnailUrl: String?, videoTitle: String, channelTitle: String, state: State):
            this(videoId, thumbnailUrl, videoTitle, channelTitle, state, null, null)

    enum class State {
        NONE,
        FAVORITES,
        FAVORITES_AND_DOWNLOAD,
        DOWNLOAD,
        HIDING
    }
}