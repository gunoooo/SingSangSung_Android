package com.gunwoo.karaoke.domain.usecase

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.Record
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.DownloadRepository
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import com.gunwoo.karaoke.domain.repository.RecordRepository
import com.gunwoo.karaoke.domain.repository.SearchRepository
import com.gunwoo.karaoke.domain.util.yearDateWeekFormat
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File
import java.util.*
import javax.inject.Inject

class InsertDownloadUseCase @Inject constructor(
    private val downloadRepository: DownloadRepository
) : ParamsUseCase<InsertDownloadUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return downloadRepository.insertDownload(params.download)
    }

    data class Params(
        val download: Download
    )
}