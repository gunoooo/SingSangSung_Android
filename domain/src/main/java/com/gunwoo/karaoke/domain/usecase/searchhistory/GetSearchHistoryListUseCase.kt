package com.gunwoo.karaoke.domain.usecase.searchhistory

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.repository.SearchHistoryRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSearchHistoryListUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : BaseUseCase<Single<List<String>>>() {

    override fun buildUseCaseObservable(): Single<List<String>> {
        return searchHistoryRepository.getSearchHistoryList()
    }
}