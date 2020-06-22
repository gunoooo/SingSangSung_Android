package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.base.BaseEntityMapper
import com.gunwoo.karaoke.data.database.entity.SearchEntity
import com.gunwoo.karaoke.domain.model.Search
import com.gunwoo.karaoke.domain.model.YoutubeData

class SearchMapper : BaseEntityMapper<Search, SearchEntity> {

    override fun mapToModel(entity: SearchEntity): Search {
        return Search(
            entity.searchVideoId,
            entity.videoId,
            entity.channelId,
            entity.search,
            entity.thumbnailUrl,
            entity.videoTitle,
            entity.channelTitle
        )
    }

    override fun mapToEntity(model: Search): SearchEntity {
        return SearchEntity(
            model.searchVideoId,
            model.videoId,
            model.channelId,
            model.search,
            model.thumbnailUrl,
            model.videoTitle,
            model.channelTitle
        )
    }

    fun mapToYoutubeData(entity: SearchEntity): YoutubeData {
        return YoutubeData(
            entity.videoId,
            entity.thumbnailUrl,
            entity.videoTitle,
            entity.channelTitle,
            null,
            entity.channelId,
            entity.search
        )
    }
}