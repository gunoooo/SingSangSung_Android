package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.InsertRecentUseCase
import com.gunwoo.karaoke.domain.usecase.InsertRecordUseCase
import javax.inject.Inject

open class PlayerViewModelFactory @Inject constructor(
    private val insertRecordUseCase: InsertRecordUseCase,
    private val insertRecentUseCase: InsertRecentUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertRecordUseCase::class.java,
            InsertRecentUseCase::class.java
        ).newInstance(insertRecordUseCase, insertRecentUseCase)
}