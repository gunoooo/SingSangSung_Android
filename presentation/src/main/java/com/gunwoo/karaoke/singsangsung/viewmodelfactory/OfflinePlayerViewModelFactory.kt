package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.GetSearchListUseCase
import com.gunwoo.karaoke.domain.usecase.InsertRecordUseCase
import javax.inject.Inject

open class OfflinePlayerViewModelFactory @Inject constructor(
    private val insertRecordUseCase: InsertRecordUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertRecordUseCase::class.java
        ).newInstance(insertRecordUseCase)
}