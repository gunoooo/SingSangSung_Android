package com.gunwoo.karaoke.singsangsung.ui.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.record.GetRecordListUseCase
import javax.inject.Inject

open class RecordViewModelFactory @Inject constructor(
    private val getRecordListUseCase: GetRecordListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            GetRecordListUseCase::class.java
        ).newInstance(getRecordListUseCase)
}