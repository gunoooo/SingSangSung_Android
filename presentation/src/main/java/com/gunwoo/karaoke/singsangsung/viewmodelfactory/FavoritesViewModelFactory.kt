package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.favorites.GetFavoritesItemListUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.GetHidingListUseCase
import com.gunwoo.karaoke.domain.usecase.recent.GetRecentListUseCase
import javax.inject.Inject

open class FavoritesViewModelFactory @Inject constructor(
    private val getRecentListUseCase: GetRecentListUseCase,
    private val getFavoritesItemListUseCase: GetFavoritesItemListUseCase,
    private val getHidingListUseCase: GetHidingListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            GetRecentListUseCase::class.java,
            GetFavoritesItemListUseCase::class.java,
            GetHidingListUseCase::class.java
        ).newInstance(getRecentListUseCase, getFavoritesItemListUseCase, getHidingListUseCase)
}