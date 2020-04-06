package com.gunwoo.karaoke.domain.usecase.searchhistory

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.repository.DownloadRepository
import com.gunwoo.karaoke.domain.repository.SearchHistoryRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : ParamsUseCase<DeleteSearchHistoryUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return searchHistoryRepository.deleteSearchHistory(params.search)
    }

    data class Params(
        val search: String
    )
}