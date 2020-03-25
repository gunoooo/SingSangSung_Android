package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.base.BaseEntityMapper
import com.gunwoo.karaoke.data.database.entity.PlaylistEntity
import com.gunwoo.karaoke.domain.model.YoutubeData

class PlaylistMapper : BaseEntityMapper<YoutubeData, PlaylistEntity> {

    override fun mapToModel(entity: PlaylistEntity): YoutubeData {
        return YoutubeData(
            entity.videoId,
            entity.thumbnailUrl,
            entity.videoTitle,
            entity.channelTitle,
            YoutubeData.State.NONE
        )
    }

    override fun mapToEntity(model: YoutubeData): PlaylistEntity {
        return PlaylistEntity(
            "${model.videoId}.${model.playlistId}",
            model.videoId!!,
            model.playlistId!!,
            model.thumbnailUrl,
            model.videoTitle,
            model.channelTitle
        )
    }
}