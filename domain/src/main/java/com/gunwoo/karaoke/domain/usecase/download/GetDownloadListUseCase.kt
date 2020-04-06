package com.gunwoo.karaoke.domain.usecase.download

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.repository.DownloadRepository
import io.reactivex.Single
import javax.inject.Inject

class GetDownloadListUseCase @Inject constructor(
    private val downloadRepository: DownloadRepository
) : BaseUseCase<Single<List<Download>>>() {

    override fun buildUseCaseObservable(): Single<List<Download>> {
        return downloadRepository.getDownloadList()
    }
}