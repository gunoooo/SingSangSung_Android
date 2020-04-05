package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.*
import javax.inject.Inject

open class ListViewModelFactory @Inject constructor(
    private val insertDownloadUseCase: InsertDownloadUseCase,
    private val insertFavoritesUseCase: InsertFavoritesUseCase,
    private val insertHidingUseCase: InsertHidingUseCase,
    private val deleteFavoritesUseCase: DeleteFavoritesUseCase,
    private val deleteDownloadByVideoIdUseCase: DeleteDownloadByVideoIdUseCase,
    private val deleteHidingUseCase: DeleteHidingUseCase,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertDownloadUseCase::class.java,
            InsertFavoritesUseCase::class.java,
            InsertHidingUseCase::class.java,
            DeleteFavoritesUseCase::class.java,
            DeleteDownloadByVideoIdUseCase::class.java,
            DeleteHidingUseCase::class.java,
            Context::class.java
        ).newInstance(
            insertDownloadUseCase,
            insertFavoritesUseCase,
            insertHidingUseCase,
            deleteFavoritesUseCase,
            deleteDownloadByVideoIdUseCase,
            deleteHidingUseCase,
            context
        )
}