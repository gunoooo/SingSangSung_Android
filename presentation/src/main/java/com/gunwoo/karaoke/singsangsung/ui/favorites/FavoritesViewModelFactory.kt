package com.gunwoo.karaoke.singsangsung.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Delete
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.GetFavoritesListUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.GetHidingListUseCase
import com.gunwoo.karaoke.domain.usecase.recent.GetRecentListUseCase
import javax.inject.Inject

open class FavoritesViewModelFactory @Inject constructor(
    private val getRecentListUseCase: GetRecentListUseCase,
    private val getFavoritesListUseCase: GetFavoritesListUseCase,
    private val getHidingListUseCase: GetHidingListUseCase,
    private val deleteFavoritesUseCase: DeleteFavoritesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            GetRecentListUseCase::class.java,
            GetFavoritesListUseCase::class.java,
            GetHidingListUseCase::class.java,
            DeleteFavoritesUseCase::class.java
        ).newInstance(getRecentListUseCase, getFavoritesListUseCase, getHidingListUseCase, deleteFavoritesUseCase)
}