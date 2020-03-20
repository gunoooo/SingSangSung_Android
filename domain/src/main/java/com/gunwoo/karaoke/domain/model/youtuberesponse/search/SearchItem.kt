package com.gunwoo.karaoke.domain.model.youtuberesponse.search

import com.gunwoo.karaoke.domain.model.youtuberesponse.ResourceId

data class SearchItem(
    val etag: String,
    val id: ResourceId,
    val kind: String,
    val snippet: SearchItemSnippet
)