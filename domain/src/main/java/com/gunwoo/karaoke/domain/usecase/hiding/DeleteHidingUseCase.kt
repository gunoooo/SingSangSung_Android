package com.gunwoo.karaoke.domain.usecase.hiding

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.HidingRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteHidingUseCase @Inject constructor(
    private val hidingRepository: HidingRepository
) : ParamsUseCase<DeleteHidingUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return hidingRepository.deleteHiding(params.youtubeData)
    }

    data class Params(
        val youtubeData: YoutubeData
    )
}