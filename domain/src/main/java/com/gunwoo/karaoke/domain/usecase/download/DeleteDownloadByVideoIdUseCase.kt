package com.gunwoo.karaoke.domain.usecase.download

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.repository.DownloadRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteDownloadByVideoIdUseCase @Inject constructor(
    private val downloadRepository: DownloadRepository
) : ParamsUseCase<DeleteDownloadByVideoIdUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return downloadRepository.deleteDownload(params.videoId)
    }

    data class Params(
        val videoId: String
    )
}