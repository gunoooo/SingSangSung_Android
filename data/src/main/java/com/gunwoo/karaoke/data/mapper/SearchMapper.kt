package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.base.BaseEntityMapper
import com.gunwoo.karaoke.data.database.entity.PlaylistEntity
import com.gunwoo.karaoke.data.database.entity.SearchEntity
import com.gunwoo.karaoke.domain.model.YoutubeData

class SearchMapper : BaseEntityMapper<YoutubeData, SearchEntity> {

    override fun mapToModel(entity: SearchEntity): YoutubeData {
        return YoutubeData(
            entity.videoId,
            entity.thumbnailUrl,
            entity.videoTitle,
            entity.channelTitle
        )
    }

    override fun mapToEntity(model: YoutubeData): SearchEntity {
        return SearchEntity(
            "${model.videoId}.${model.search}",
            model.videoId!!,
            model.channelId!!,
            model.search!!,
            model.thumbnailUrl,
            model.videoTitle,
            model.channelTitle
        )
    }
}