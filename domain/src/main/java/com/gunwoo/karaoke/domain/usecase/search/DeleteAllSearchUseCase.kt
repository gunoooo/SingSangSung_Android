package com.gunwoo.karaoke.domain.usecase.search

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.repository.SearchRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DeleteAllSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseUseCase<Completable>() {

    override fun buildUseCaseObservable(): Completable {
        return searchRepository.deleteAllSearch()
    }
}