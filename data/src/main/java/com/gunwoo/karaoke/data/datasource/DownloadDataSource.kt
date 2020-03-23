package com.gunwoo.karaoke.data.datasource

import com.gunwoo.karaoke.data.base.BaseDataSource
import com.gunwoo.karaoke.data.database.cache.DownloadCache
import com.gunwoo.karaoke.data.database.cache.RecordCache
import com.gunwoo.karaoke.data.database.entity.DownloadEntity
import com.gunwoo.karaoke.data.database.entity.RecordEntity
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class DownloadDataSource @Inject constructor(
    override val remote: Any,
    override val cache: DownloadCache
) : BaseDataSource<Any, DownloadCache>() {

    fun getDownloadList(): Single<List<DownloadEntity>> = cache.getDownloadList()

    fun insertDownload(downloadEntity: DownloadEntity): Completable = cache.insertDownload(downloadEntity)
}