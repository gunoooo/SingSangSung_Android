package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.searchsetting.*
import javax.inject.Inject

open class SearchSettingViewModelFactory @Inject constructor(
    private val getSearchSettingListUseCase: GetSearchSettingListUseCase,
    private val deleteSearchSettingUseCase: DeleteSearchSettingUseCase,
    private val updateSearchSettingUseCase: UpdateSearchSettingUseCase,
    private val updateSearchSettingListUseCase: UpdateSearchSettingListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            GetSearchSettingListUseCase::class.java,
            DeleteSearchSettingUseCase::class.java,
            UpdateSearchSettingUseCase::class.java,
            UpdateSearchSettingListUseCase::class.java
        ).newInstance(getSearchSettingListUseCase, deleteSearchSettingUseCase, updateSearchSettingUseCase, updateSearchSettingListUseCase)
}