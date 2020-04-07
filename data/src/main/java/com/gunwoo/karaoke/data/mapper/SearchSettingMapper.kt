package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.base.BaseEntityMapper
import com.gunwoo.karaoke.data.database.entity.SearchSettingEntity
import com.gunwoo.karaoke.domain.model.SearchSetting

class SearchSettingMapper : BaseEntityMapper<SearchSetting, SearchSettingEntity> {

    override fun mapToModel(entity: SearchSettingEntity): SearchSetting {
        return SearchSetting(
            entity.channelId,
            entity.channelTitle,
            entity.maxResults
        )
    }

    override fun mapToEntity(model: SearchSetting): SearchSettingEntity {
        return SearchSettingEntity(
            model.channelId,
            model.channelTitle,
            model.maxResults
        )
    }
}