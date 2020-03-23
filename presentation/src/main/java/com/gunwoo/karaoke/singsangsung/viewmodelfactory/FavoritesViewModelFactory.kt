package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.GetDownloadListUseCase
import com.gunwoo.karaoke.domain.usecase.GetFavoritesListUseCase
import com.gunwoo.karaoke.domain.usecase.GetHidingListUseCase
import javax.inject.Inject

open class FavoritesViewModelFactory @Inject constructor(
    private val getDownloadListUseCase: GetDownloadListUseCase,
    private val getFavoritesListUseCase: GetFavoritesListUseCase,
    private val getHidingListUseCase: GetHidingListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            GetDownloadListUseCase::class.java,
            GetFavoritesListUseCase::class.java,
            GetHidingListUseCase::class.java
        ).newInstance(getDownloadListUseCase, getFavoritesListUseCase, getHidingListUseCase)
}