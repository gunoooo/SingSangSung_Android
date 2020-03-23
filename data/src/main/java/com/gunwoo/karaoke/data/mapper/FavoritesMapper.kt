package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.base.BaseEntityMapper
import com.gunwoo.karaoke.data.database.entity.FavoritesEntity
import com.gunwoo.karaoke.domain.model.YoutubeData

class FavoritesMapper : BaseEntityMapper<YoutubeData, FavoritesEntity> {

    override fun mapToModel(entity: FavoritesEntity): YoutubeData {
        return YoutubeData(
            entity.videoId,
            entity.thumbnailUrl,
            entity.videoTitle,
            entity.channelTitle,
            YoutubeData.State.FAVORITES
        )
    }

    override fun mapToEntity(model: YoutubeData): FavoritesEntity {
        return FavoritesEntity(
            model.videoId!!,
            model.thumbnailUrl,
            model.videoTitle,
            model.channelTitle
        )
    }
}