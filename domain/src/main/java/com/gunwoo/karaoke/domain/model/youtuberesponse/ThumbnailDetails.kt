package com.gunwoo.karaoke.domain.model.youtuberesponse

data class ThumbnailDetails(
    val default: Thumbnail?,
    val high: Thumbnail?,
    val medium: Thumbnail?
) {

    fun getThumbnailUrl(): String? {
        return high?.url ?: (medium?.url ?: default?.url)
    }
}