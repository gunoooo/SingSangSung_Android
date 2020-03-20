package com.gunwoo.karaoke.domain.model.youtuberesponse.search

import com.gunwoo.karaoke.domain.model.youtuberesponse.ThumbnailDetails
import java.util.*

data class SearchItemSnippet(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val liveBroadcastContent: String,
    val publishedAt: Date,
    val thumbnails: ThumbnailDetails?,
    val title: String
)