package com.gunwoo.karaoke.domain.model

import java.io.Serializable

data class YoutubeData(
    val videoId: String?,
    val thumbnailUrl: String?,
    val videoTitle: String,
    val channelTitle: String,
    var state: State
) : Serializable {

    enum class State {
        NONE,
        FAVORITES,
        FAVORITES_AND_DOWNLOAD,
        HIDING
    }
}