package com.gunwoo.karaoke.singsangsung.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.favorites.GetFavoritesListUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.UpdateFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.GetHidingListUseCase
import com.gunwoo.karaoke.domain.usecase.recent.GetRecentListUseCase
import javax.inject.Inject

open class FavoritesFixViewModelFactory @Inject constructor(
    private val updateFavoritesUseCase: UpdateFavoritesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            UpdateFavoritesUseCase::class.java
        ).newInstance(updateFavoritesUseCase)
}