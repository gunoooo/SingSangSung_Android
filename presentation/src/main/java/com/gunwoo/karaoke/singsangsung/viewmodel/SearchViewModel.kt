package com.gunwoo.karaoke.singsangsung.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.GetSearchListUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import io.reactivex.observers.DisposableSingleObserver

class SearchViewModel(
    private val getSearchListUseCase: GetSearchListUseCase
) : BaseViewModel() {

    val searchList = ArrayList<YoutubeData>()

    val search = MutableLiveData<String>()

    val onHideKeyEvent = SingleLiveEvent<Unit>()
    val onEmptyEvent = SingleLiveEvent<Unit>()
    val onOpenListEvent = SingleLiveEvent<Unit>()

    private fun setSearchList() {
        isLoading.value = true

        addDisposable(getSearchListUseCase.buildUseCaseObservable(GetSearchListUseCase.Params(search.value!!)),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    searchList.clear()
                    searchList.addAll(t)
                    onOpenListEvent.call()
                    isLoading.value = false
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                    isLoading.value = false
                }
            })
    }

    fun search() {
        onHideKeyEvent.call()

        val isEmpty = search.value.isNullOrBlank()

        if (isEmpty)
            onEmptyEvent.call()
        else
            setSearchList()
    }
}