package com.gunwoo.karaoke.singsangsung.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.playlist.GetPlaylistListUseCase
import javax.inject.Inject

open class HomeViewModelFactory @Inject constructor(
    private val getPlaylistListUseCase: GetPlaylistListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            GetPlaylistListUseCase::class.java
        ).newInstance(getPlaylistListUseCase)
}