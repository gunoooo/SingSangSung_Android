package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.DownloadCache
import com.gunwoo.karaoke.data.database.cache.RecordCache
import com.gunwoo.karaoke.data.database.entity.DownloadEntity
import com.gunwoo.karaoke.data.database.entity.RecordEntity
import com.gunwoo.karaoke.data.mapper.DownloadMapper
import com.gunwoo.karaoke.domain.model.Download
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class DownloadDataSource @Inject constructor(
    override val remote: Any,
    override val cache: DownloadCache
) : BaseDataSource<Any, DownloadCache>() {

    private val downloadMapper = DownloadMapper()

    fun getDownloadList(): Single<List<Download>> =
        cache.getDownloadList().map { downloadEntityList -> downloadEntityList.map { downloadMapper.mapToModel(it) }.filter { it.video.isFile } }

    fun insertDownload(download: Download): Completable = cache.insertDownload(downloadMapper.mapToEntity(download))

    fun deleteDownload(download: Download): Completable = cache.deleteDownload(downloadMapper.mapToEntity(download))

    fun deleteDownload(videoId: String): Completable = cache.deleteDownload(videoId)
}