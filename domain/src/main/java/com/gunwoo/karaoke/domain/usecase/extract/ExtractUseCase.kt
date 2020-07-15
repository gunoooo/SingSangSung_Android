package com.gunwoo.karaoke.domain.usecase.extract

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.repository.ExtractRepository
import io.reactivex.Single
import javax.inject.Inject

class ExtractUseCase @Inject constructor(
    private val extractRepository: ExtractRepository
) : ParamsUseCase<ExtractUseCase.Params, Single<String>>() {

    override fun buildUseCaseObservable(params: Params): Single<String> {
        return extractRepository.extract(params.videoId)
    }

    data class Params(val videoId: String)
}