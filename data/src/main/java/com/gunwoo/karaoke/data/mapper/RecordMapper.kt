package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.base.BaseEntityMapper
import com.gunwoo.karaoke.data.database.entity.RecordEntity
import com.gunwoo.karaoke.domain.model.Record
import java.io.File

class RecordMapper : BaseEntityMapper<Record, RecordEntity> {

    override fun mapToModel(entity: RecordEntity): Record {
        return Record(
            entity.title,
            entity.time,
            entity.thumbnail,
            File(entity.path)
        )
    }

    override fun mapToEntity(model: Record): RecordEntity {
        return RecordEntity(
            model.title,
            model.thumbnail,
            model.time,
            model.file.absolutePath
        )
    }
}