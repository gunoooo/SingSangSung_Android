package com.gunwoo.karaoke.domain.model.youtuberesponse.playlist

data class PlaylistItem(
    val etag: String,
    val kind: String,
    val snippet: PlaylistItemSnippet
)