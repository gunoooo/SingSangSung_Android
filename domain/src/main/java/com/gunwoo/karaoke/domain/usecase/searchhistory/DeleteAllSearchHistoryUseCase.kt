package com.gunwoo.karaoke.domain.usecase.searchhistory

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.repository.DownloadRepository
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import com.gunwoo.karaoke.domain.repository.SearchHistoryRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DeleteAllSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : BaseUseCase<Completable>() {

    override fun buildUseCaseObservable(): Completable {
        return searchHistoryRepository.deleteAll()
    }
}