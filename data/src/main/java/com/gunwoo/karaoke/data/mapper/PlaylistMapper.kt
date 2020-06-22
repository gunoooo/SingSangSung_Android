package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.base.BaseEntityMapper
import com.gunwoo.karaoke.data.database.entity.PlaylistEntity
import com.gunwoo.karaoke.domain.model.PlaylistItem
import com.gunwoo.karaoke.domain.model.YoutubeData

class PlaylistMapper : BaseEntityMapper<PlaylistItem, PlaylistEntity> {

    override fun mapToModel(entity: PlaylistEntity): PlaylistItem {
        return PlaylistItem(
            entity.playlistVideoId,
            entity.videoId,
            entity.playlistId,
            entity.channelId,
            entity.thumbnailUrl,
            entity.videoTitle,
            entity.channelTitle
        )
    }

    override fun mapToEntity(model: PlaylistItem): PlaylistEntity {
        return PlaylistEntity(
            model.playlistVideoId,
            model.videoId,
            model.playlistId,
            model.channelId,
            model.thumbnailUrl,
            model.videoTitle,
            model.channelTitle
        )
    }

    fun mapToYoutubeData(entity: PlaylistEntity): YoutubeData {
        return YoutubeData(
            entity.videoId,
            entity.thumbnailUrl,
            entity.videoTitle,
            entity.channelTitle,
            entity.playlistId,
            entity.channelId,
            null
        )
    }
}