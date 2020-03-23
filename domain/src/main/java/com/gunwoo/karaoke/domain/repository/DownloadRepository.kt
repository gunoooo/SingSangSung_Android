package com.gunwoo.karaoke.domain.repository

import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.Record
import io.reactivex.Completable
import io.reactivex.Single

interface DownloadRepository {
    fun getDownloadList(): Single<List<Download>>

    fun insertDownload(download: Download): Completable
}