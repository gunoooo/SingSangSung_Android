package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.DeleteHidingUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.InsertHidingUseCase
import javax.inject.Inject

open class ListViewModelFactory @Inject constructor(
    private val insertFavoritesUseCase: InsertFavoritesUseCase,
    private val insertHidingUseCase: InsertHidingUseCase,
    private val deleteFavoritesUseCase: DeleteFavoritesUseCase,
    private val deleteHidingUseCase: DeleteHidingUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertFavoritesUseCase::class.java,
            InsertHidingUseCase::class.java,
            DeleteFavoritesUseCase::class.java,
            DeleteHidingUseCase::class.java
        ).newInstance(
            insertFavoritesUseCase,
            insertHidingUseCase,
            deleteFavoritesUseCase,
            deleteHidingUseCase
        )
}