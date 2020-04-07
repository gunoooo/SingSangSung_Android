package com.gunwoo.karaoke.domain.usecase.searchsetting

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.domain.repository.SearchSettingRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteSearchSettingUseCase @Inject constructor(
    private val searchSettingRepository: SearchSettingRepository
) : ParamsUseCase<DeleteSearchSettingUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return searchSettingRepository.deleteSearchSetting(params.searchSetting)
    }

    data class Params(
        val searchSetting: SearchSetting
    )
}