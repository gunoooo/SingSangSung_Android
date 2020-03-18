package com.gunwoo.karaoke.domain.usecase

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSearchListUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : ParamsUseCase<GetSearchListUseCase.Params, Single<List<YoutubeData>>>() {

    override fun buildUseCaseObservable(params: Params): Single<List<YoutubeData>> {
        return searchRepository.getSearchList(params.search)
    }

    data class Params(val search: String)
}