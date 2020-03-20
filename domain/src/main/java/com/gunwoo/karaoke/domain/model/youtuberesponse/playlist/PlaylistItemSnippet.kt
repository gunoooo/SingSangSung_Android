package com.gunwoo.karaoke.domain.model.youtuberesponse.playlist

import com.gunwoo.karaoke.domain.model.youtuberesponse.ResourceId
import com.gunwoo.karaoke.domain.model.youtuberesponse.ThumbnailDetails
import java.util.*

data class PlaylistItemSnippet(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val publishedAt: Date,
    val resourceId: ResourceId?,
    val thumbnails: ThumbnailDetails?,
    val title: String
)