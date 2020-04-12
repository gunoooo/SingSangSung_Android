package com.gunwoo.karaoke.singsangsung.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.favorites.GetFavoritesListUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.GetHidingListUseCase
import com.gunwoo.karaoke.domain.usecase.recent.GetRecentListUseCase
import javax.inject.Inject

open class FavoritesAddViewModelFactory @Inject constructor(
    private val insertFavoritesUseCase: InsertFavoritesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertFavoritesUseCase::class.java
        ).newInstance(insertFavoritesUseCase)
}