package com.gunwoo.karaoke.singsangsung.ui.search

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.search.GetSearchListUseCase
import com.gunwoo.karaoke.domain.usecase.searchhistory.DeleteAllSearchHistoryUseCase
import com.gunwoo.karaoke.domain.usecase.searchhistory.DeleteSearchHistoryUseCase
import com.gunwoo.karaoke.domain.usecase.searchhistory.GetSearchHistoryListUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.searchhistory.SearchHistoryListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver

class SearchViewModel(
    private val getSearchListUseCase: GetSearchListUseCase,
    private val getSearchHistoryListUseCase: GetSearchHistoryListUseCase,
    private val deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase,
    private val deleteAllSearchHistoryUseCase: DeleteAllSearchHistoryUseCase
) : BaseViewModel() {

    val searchList = ArrayList<YoutubeData>()
    val searchHistoryList = ArrayList<String>()

    val searchHistoryListAdapter = SearchHistoryListAdapter()

    val search = MutableLiveData<String>()

    val onHideKeyEvent = SingleLiveEvent<Unit>()
    val onEmptyEvent = SingleLiveEvent<Unit>()
    val onOpenListEvent = SingleLiveEvent<Unit>()
    val onOpenSettingEvent = SingleLiveEvent<Unit>()

    init { searchHistoryListAdapter.setYoutubeDataList(searchHistoryList) }

    fun setSearchHistoryList() {
        addDisposable(getSearchHistoryListUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<String>>() {
                override fun onSuccess(t: List<String>) {
                    searchHistoryList.clear()
                    searchHistoryList.addAll(t)
                    searchHistoryListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    private fun setSearchList() {
        setIsLoading(true)
        addDisposable(getSearchListUseCase.buildUseCaseObservable(GetSearchListUseCase.Params(search.value!!)),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    searchList.clear()
                    searchList.addAll(t)
                    onOpenListEvent.call()
                    setIsLoading(false)
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                    setIsLoading(false)
                }
            })
    }

    fun deleteSearchHistory(search: String) {
        addDisposable(deleteSearchHistoryUseCase.buildUseCaseObservable(DeleteSearchHistoryUseCase.Params((search))),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    val position = searchHistoryList.indexOf(search)
                    searchHistoryList.remove(search)
                    searchHistoryListAdapter.notifyItemRemoved(position)
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    private fun deleteAllSearchHistory() {
        addDisposable(deleteAllSearchHistoryUseCase.buildUseCaseObservable(),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    searchHistoryList.clear()
                    searchHistoryListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun search(search: String) {
        this.search.value = search
        onHideKeyEvent.call()
        setSearchList()
    }

    fun search() {
        onHideKeyEvent.call()

        val isEmpty = search.value.isNullOrBlank()

        if (isEmpty)
            onEmptyEvent.call()
        else
            setSearchList()
    }

    fun onClickDeleteAll() = deleteAllSearchHistory()

    fun onClickOpenSetting() = onOpenSettingEvent.call()
}