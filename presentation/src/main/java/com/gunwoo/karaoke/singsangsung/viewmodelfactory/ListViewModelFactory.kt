package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.InsertDownloadUseCase
import javax.inject.Inject

open class ListViewModelFactory @Inject constructor(
    private val insertDownloadUseCase: InsertDownloadUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertDownloadUseCase::class.java
        ).newInstance(insertDownloadUseCase)
}