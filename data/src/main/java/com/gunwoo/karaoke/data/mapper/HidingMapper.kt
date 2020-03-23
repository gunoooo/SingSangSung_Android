package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.base.BaseEntityMapper
import com.gunwoo.karaoke.data.database.entity.FavoritesEntity
import com.gunwoo.karaoke.data.database.entity.HidingEntity
import com.gunwoo.karaoke.domain.model.YoutubeData

class HidingMapper : BaseEntityMapper<YoutubeData, HidingEntity> {

    override fun mapToModel(entity: HidingEntity): YoutubeData {
        return YoutubeData(
            entity.videoId,
            entity.thumbnailUrl,
            entity.videoTitle,
            entity.channelTitle,
            YoutubeData.State.HIDING
        )
    }

    override fun mapToEntity(model: YoutubeData): HidingEntity {
        return HidingEntity(
            model.videoId!!,
            model.thumbnailUrl,
            model.videoTitle,
            model.channelTitle
        )
    }
}