package com.gunwoo.karaoke.domain.usecase.hiding

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.HidingRepository
import io.reactivex.Completable
import javax.inject.Inject

class InsertHidingUseCase @Inject constructor(
    private val hidingRepository: HidingRepository
) : ParamsUseCase<InsertHidingUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return hidingRepository.insertHiding(params.youtubeData)
    }

    data class Params(
        val youtubeData: YoutubeData
    )
}