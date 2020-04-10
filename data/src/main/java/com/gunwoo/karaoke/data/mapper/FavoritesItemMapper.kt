package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.base.BaseEntityMapper
import com.gunwoo.karaoke.data.database.entity.FavoritesItemEntity
import com.gunwoo.karaoke.domain.model.YoutubeData

class FavoritesItemMapper : BaseEntityMapper<YoutubeData, FavoritesItemEntity> {

    override fun mapToModel(entity: FavoritesItemEntity): YoutubeData {
        return YoutubeData(
            entity.videoId,
            entity.thumbnailUrl,
            entity.videoTitle,
            entity.channelTitle
        )
    }

    override fun mapToEntity(model: YoutubeData): FavoritesItemEntity {
        return FavoritesItemEntity(
            0,
            model.videoId!!,
            model.thumbnailUrl,
            model.videoTitle,
            model.channelTitle
        )
    }

    fun mapToEntity(model: YoutubeData, favoritesId: Int): FavoritesItemEntity {
        return FavoritesItemEntity(
            favoritesId,
            model.videoId!!,
            model.thumbnailUrl,
            model.videoTitle,
            model.channelTitle
        )
    }
}