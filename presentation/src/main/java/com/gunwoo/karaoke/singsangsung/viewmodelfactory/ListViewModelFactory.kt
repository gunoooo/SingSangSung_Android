package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.DeleteHidingUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.InsertHidingUseCase
import javax.inject.Inject

open class ListViewModelFactory @Inject constructor(
    private val insertFavoritesItemUseCase: InsertFavoritesItemUseCase,
    private val insertHidingUseCase: InsertHidingUseCase,
    private val deleteFavoritesItemUseCase: DeleteFavoritesItemUseCase,
    private val deleteHidingUseCase: DeleteHidingUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertFavoritesItemUseCase::class.java,
            InsertHidingUseCase::class.java,
            DeleteFavoritesItemUseCase::class.java,
            DeleteHidingUseCase::class.java
        ).newInstance(
            insertFavoritesItemUseCase,
            insertHidingUseCase,
            deleteFavoritesItemUseCase,
            deleteHidingUseCase
        )
}