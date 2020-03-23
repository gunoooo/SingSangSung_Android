package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.DownloadDataSource
import com.gunwoo.karaoke.data.mapper.DownloadMapper
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.repository.DownloadRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class DownloadRepositoryImpl @Inject constructor(
    private val downloadDataSource: DownloadDataSource
) : DownloadRepository {

    override fun getDownloadList(): Single<List<Download>> {
        return downloadDataSource.getDownloadList().map { if (it.isEmpty()) throw Exception("다운로드 리스트가 없습니다") else it }
    }

    override fun insertDownload(download: Download): Completable {
        return downloadDataSource.insertDownload(download)
    }

    override fun deleteDownload(download: Download): Completable {
        return downloadDataSource.deleteDownload(download)
    }
}