package com.gunwoo.karaoke.singsangsung.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.GetSearchListUseCase
import javax.inject.Inject

open class SearchViewModelFactory @Inject constructor(
    private val searchListUseCase: GetSearchListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            GetSearchListUseCase::class.java
        ).newInstance(searchListUseCase)
}