package com.gunwoo.karaoke.data.mapper

import com.gunwoo.karaoke.data.base.BaseEntityMapper
import com.gunwoo.karaoke.data.database.entity.DownloadEntity
import com.gunwoo.karaoke.domain.model.Download
import java.io.File

class DownloadMapper : BaseEntityMapper<Download, DownloadEntity> {

    override fun mapToModel(entity: DownloadEntity): Download {
        return Download(
            entity.videoId,
            entity.title,
            File(entity.thumbnailPath),
            entity.thumbnailUrl,
            File(entity.videoPath)
        )
    }

    override fun mapToEntity(model: Download): DownloadEntity {
        return DownloadEntity(
            model.videoId,
            model.title,
            model.thumbnail.absolutePath,
            model.thumbnailUrl,
            model.video.absolutePath
        )
    }
}