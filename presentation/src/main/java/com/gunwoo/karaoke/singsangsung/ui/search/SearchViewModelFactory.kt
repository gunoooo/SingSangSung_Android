package com.gunwoo.karaoke.singsangsung.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunwoo.karaoke.domain.usecase.search.GetSearchListUseCase
import com.gunwoo.karaoke.domain.usecase.searchhistory.DeleteAllSearchHistoryUseCase
import com.gunwoo.karaoke.domain.usecase.searchhistory.DeleteSearchHistoryUseCase
import com.gunwoo.karaoke.domain.usecase.searchhistory.GetSearchHistoryListUseCase
import javax.inject.Inject

open class SearchViewModelFactory @Inject constructor(
    private val getSearchListUseCase: GetSearchListUseCase,
    private val getSearchHistoryListUseCase: GetSearchHistoryListUseCase,
    private val deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase,
    private val deleteAllSearchHistoryUseCase: DeleteAllSearchHistoryUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            GetSearchListUseCase::class.java,
            GetSearchHistoryListUseCase::class.java,
            DeleteSearchHistoryUseCase::class.java,
            DeleteAllSearchHistoryUseCase::class.java
        ).newInstance(getSearchListUseCase, getSearchHistoryListUseCase, deleteSearchHistoryUseCase, deleteAllSearchHistoryUseCase)
}