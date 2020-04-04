package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.DeleteFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.InsertDownloadUseCase
import com.gunwoo.karaoke.domain.usecase.InsertFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.InsertHidingUseCase
import javax.inject.Inject

open class PlayerPlaylistViewModelFactory @Inject constructor(
    private val insertDownloadUseCase: InsertDownloadUseCase,
    private val insertFavoritesUseCase: InsertFavoritesUseCase,
    private val insertHidingUseCase: InsertHidingUseCase,
    private val deleteFavoritesUseCase: DeleteFavoritesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertDownloadUseCase::class.java,
            InsertFavoritesUseCase::class.java,
            InsertHidingUseCase::class.java,
            DeleteFavoritesUseCase::class.java
        ).newInstance(insertDownloadUseCase, insertFavoritesUseCase, insertHidingUseCase, deleteFavoritesUseCase)
}