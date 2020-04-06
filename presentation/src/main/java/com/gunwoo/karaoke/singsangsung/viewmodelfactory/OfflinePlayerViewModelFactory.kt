package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.download.DeleteDownloadUseCase
import com.gunwoo.karaoke.domain.usecase.record.InsertRecordUseCase
import javax.inject.Inject

open class OfflinePlayerViewModelFactory @Inject constructor(
    private val insertRecordUseCase: InsertRecordUseCase,
    private val deleteDownloadUseCase: DeleteDownloadUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertRecordUseCase::class.java,
            DeleteDownloadUseCase::class.java
        ).newInstance(insertRecordUseCase, deleteDownloadUseCase)
}