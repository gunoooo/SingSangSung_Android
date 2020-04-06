package com.gunwoo.karaoke.domain.usecase.searchhistory

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.DownloadRepository
import com.gunwoo.karaoke.domain.repository.FavoritesRepository
import com.gunwoo.karaoke.domain.repository.HidingRepository
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