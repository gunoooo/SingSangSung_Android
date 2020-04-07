package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.searchsetting.*
import javax.inject.Inject

open class ChannelAddViewModelFactory @Inject constructor(
    private val getNotSelectedSearchSettingListUseCase: GetNotSelectedSearchSettingListUseCase,
    private val insertSearchSettingUseCase: InsertSearchSettingUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            GetNotSelectedSearchSettingListUseCase::class.java,
            InsertSearchSettingUseCase::class.java
        ).newInstance(getNotSelectedSearchSettingListUseCase, insertSearchSettingUseCase)
}