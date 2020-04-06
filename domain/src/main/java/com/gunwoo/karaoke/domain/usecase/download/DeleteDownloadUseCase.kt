package com.gunwoo.karaoke.domain.usecase.download

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.repository.DownloadRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteDownloadUseCase @Inject constructor(
    private val downloadRepository: DownloadRepository
) : ParamsUseCase<DeleteDownloadUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return downloadRepository.deleteDownload(params.download)
    }

    data class Params(
        val download: Download
    )
}