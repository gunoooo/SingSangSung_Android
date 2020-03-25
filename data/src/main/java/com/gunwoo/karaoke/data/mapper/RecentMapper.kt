package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.base.BaseEntityMapper
import com.gunwoo.karaoke.data.database.entity.FavoritesEntity
import com.gunwoo.karaoke.data.database.entity.HidingEntity
import com.gunwoo.karaoke.data.database.entity.RecentEntity
import com.gunwoo.karaoke.domain.model.YoutubeData

class RecentMapper : BaseEntityMapper<YoutubeData, RecentEntity> {

    override fun mapToModel(entity: RecentEntity): YoutubeData {
        return YoutubeData(
            entity.videoId,
            entity.thumbnailUrl,
            entity.videoTitle,
            entity.channelTitle,
            YoutubeData.State.NONE
        )
    }

    override fun mapToEntity(model: YoutubeData): RecentEntity {
        return RecentEntity(
            model.videoId!!,
            model.thumbnailUrl,
            model.videoTitle,
            model.channelTitle
        )
    }
}