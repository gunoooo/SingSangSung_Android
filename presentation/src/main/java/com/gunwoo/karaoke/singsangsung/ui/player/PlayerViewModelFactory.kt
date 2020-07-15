package com.gunwoo.karaoke.singsangsung.ui.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.extract.ExtractUseCase
import com.gunwoo.karaoke.domain.usecase.recent.InsertRecentUseCase
import com.gunwoo.karaoke.domain.usecase.record.InsertRecordUseCase
import javax.inject.Inject

open class PlayerViewModelFactory @Inject constructor(
    private val insertRecordUseCase: InsertRecordUseCase,
    private val insertRecentUseCase: InsertRecentUseCase,
    private val extractUseCase: ExtractUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertRecordUseCase::class.java,
            InsertRecentUseCase::class.java,
            ExtractUseCase::class.java
        ).newInstance(
            insertRecordUseCase,
            insertRecentUseCase,
            extractUseCase
        )
}