package com.gunwoo.karaoke.domain.usecase.searchsetting

import com.gunwoo.karaoke.domain.base.ParamsUseCase
import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.domain.repository.SearchSettingRepository
import io.reactivex.Completable
import javax.inject.Inject

class InsertSearchSettingUseCase @Inject constructor(
    private val searchSettingRepository: SearchSettingRepository
) : ParamsUseCase<InsertSearchSettingUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return searchSettingRepository.insertSearchSetting(params.searchSetting)
    }

    data class Params(
        val searchSetting: SearchSetting
    )
}