package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.download.DeleteDownloadByVideoIdUseCase
import com.gunwoo.karaoke.domain.usecase.download.InsertDownloadUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.InsertHidingUseCase
import com.gunwoo.karaoke.domain.usecase.recent.InsertRecentUseCase
import com.gunwoo.karaoke.domain.usecase.record.InsertRecordUseCase
import javax.inject.Inject

open class PlayerViewModelFactory @Inject constructor(
    private val insertRecordUseCase: InsertRecordUseCase,
    private val insertRecentUseCase: InsertRecentUseCase,
    private val insertDownloadUseCase: InsertDownloadUseCase,
    private val insertFavoritesUseCase: InsertFavoritesUseCase,
    private val insertHidingUseCase: InsertHidingUseCase,
    private val deleteFavoritesUseCase: DeleteFavoritesUseCase,
    private val deleteDownloadByVideoIdUseCase: DeleteDownloadByVideoIdUseCase,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            InsertRecordUseCase::class.java,
            InsertRecentUseCase::class.java,
            InsertDownloadUseCase::class.java,
            InsertFavoritesUseCase::class.java,
            InsertHidingUseCase::class.java,
            DeleteFavoritesUseCase::class.java,
            DeleteDownloadByVideoIdUseCase::class.java,
            Context::class.java
        ).newInstance(
            insertRecordUseCase,
            insertRecentUseCase,
            insertDownloadUseCase,
            insertFavoritesUseCase,
            insertHidingUseCase,
            deleteFavoritesUseCase,
            deleteDownloadByVideoIdUseCase,
            context
        )
}