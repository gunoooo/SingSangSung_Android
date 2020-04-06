package com.gunwoo.karaoke.domain.usecase.recent

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.*
import io.reactivex.Completable
import javax.inject.Inject

class InsertRecentUseCase @Inject constructor(
    private val recentRepository: RecentRepository
) : ParamsUseCase<InsertRecentUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return recentRepository.insertRecent(params.youtubeData)
    }

    data class Params(
        val youtubeData: YoutubeData
    )
}