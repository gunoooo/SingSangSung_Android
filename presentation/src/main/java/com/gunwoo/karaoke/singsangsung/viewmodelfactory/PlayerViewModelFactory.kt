package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.InsertHidingUseCase
import com.gunwoo.karaoke.domain.usecase.recent.InsertRecentUseCase
import com.gunwoo.karaoke.domain.usecase.record.InsertRecordUseCase
import javax.inject.Inject

open class PlayerViewModelFactory @Inject constructor(
    private val insertRecordUseCase: InsertRecordUseCase,
    private val insertRecentUseCase: InsertRecentUseCase,
    private val insertFavoritesItemUseCase: InsertFavoritesItemUseCase,
    private val insertHidingUseCase: InsertHidingUseCase,
    private val deleteFavoritesItemUseCase: DeleteFavoritesItemUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertRecordUseCase::class.java,
            InsertRecentUseCase::class.java,
            InsertFavoritesItemUseCase::class.java,
            InsertHidingUseCase::class.java,
            DeleteFavoritesItemUseCase::class.java
        ).newInstance(
            insertRecordUseCase,
            insertRecentUseCase,
            insertFavoritesItemUseCase,
            insertHidingUseCase,
            deleteFavoritesItemUseCase
        )
}