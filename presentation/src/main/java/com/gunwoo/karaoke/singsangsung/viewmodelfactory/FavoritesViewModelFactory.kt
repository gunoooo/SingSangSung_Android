package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.GetDownloadListUseCase
import javax.inject.Inject

open class FavoritesViewModelFactory @Inject constructor(
    private val getDownloadListUseCase: GetDownloadListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            GetDownloadListUseCase::class.java
        ).newInstance(getDownloadListUseCase)
}