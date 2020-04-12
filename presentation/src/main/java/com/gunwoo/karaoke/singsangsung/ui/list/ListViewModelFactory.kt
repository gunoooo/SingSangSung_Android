package com.gunwoo.karaoke.singsangsung.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.DeleteHidingUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.InsertHidingUseCase
import javax.inject.Inject

open class ListViewModelFactory @Inject constructor(
    private val insertHidingUseCase: InsertHidingUseCase,
    private val deleteHidingUseCase: DeleteHidingUseCase,
    private val deleteFavoritesItemUseCase: DeleteFavoritesItemUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertHidingUseCase::class.java,
            DeleteHidingUseCase::class.java,
            DeleteFavoritesItemUseCase::class.java
        ).newInstance(
            insertHidingUseCase,
            deleteHidingUseCase,
            deleteFavoritesItemUseCase
        )
}