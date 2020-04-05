package com.gunwoo.karaoke.data.database.cache

import android.app.Application
import com.gunwoo.karaoke.data.base.BaseCache
import com.gunwoo.karaoke.data.database.entity.DownloadEntity
import com.gunwoo.karaoke.data.database.entity.RecordEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DownloadCache @Inject constructor(application: Application) : BaseCache(application) {

    private val downloadDao = database.downloadDao()

    fun getDownloadList(): Single<List<DownloadEntity>> = downloadDao.getDownloadList()

    fun insertDownload(downloadEntity: DownloadEntity): Completable = downloadDao.insert(downloadEntity)

    fun deleteDownload(downloadEntity: DownloadEntity): Completable = downloadDao.delete(downloadEntity)

    fun deleteDownload(videoId: String): Completable = downloadDao.delete(videoId)
}