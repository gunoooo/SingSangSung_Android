package com.gunwoo.karaoke.domain.usecase.searchsetting

import com.gunwoo.karaoke.domain.base.BaseUseCase
import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.domain.repository.SearchSettingRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSearchSettingListUseCase @Inject constructor(
    private val searchSettingRepository: SearchSettingRepository
) : BaseUseCase<Single<List<SearchSetting>>>() {

    override fun buildUseCaseObservable(): Single<List<SearchSetting>> {
        return searchSettingRepository.getSearchSettingList()
    }
}