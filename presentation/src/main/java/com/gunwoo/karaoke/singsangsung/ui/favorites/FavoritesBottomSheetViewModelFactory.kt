package com.gunwoo.karaoke.singsangsung.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.GetFavoritesListUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesItemUseCase
import javax.inject.Inject

open class FavoritesBottomSheetViewModelFactory @Inject constructor(
    private val getFavoritesListUseCase: GetFavoritesListUseCase,
    private val insertFavoritesItemUseCase: InsertFavoritesItemUseCase,
    private val deleteFavoritesItemUseCase: DeleteFavoritesItemUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            GetFavoritesListUseCase::class.java,
            InsertFavoritesItemUseCase::class.java,
            DeleteFavoritesItemUseCase::class.java
        ).newInstance(getFavoritesListUseCase, insertFavoritesItemUseCase, deleteFavoritesItemUseCase)
}