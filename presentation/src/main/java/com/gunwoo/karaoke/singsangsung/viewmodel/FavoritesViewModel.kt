package com.gunwoo.karaoke.singsangsung.viewmodel

import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.usecase.GetDownloadListUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import io.reactivex.observers.DisposableSingleObserver

class FavoritesViewModel(
    private val getDownloadListUseCase: GetDownloadListUseCase
) : BaseViewModel() {

    val downloadList = ArrayList<Download>()

    val onOpenOfflineListFragmentEvent = SingleLiveEvent<Unit>()

    private fun setDownloadList() {
        addDisposable(getDownloadListUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<Download>>() {
                override fun onSuccess(t: List<Download>) {
                    downloadList.clear()
                    downloadList.addAll(t)
                    onOpenOfflineListFragmentEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun onClickDownloadList() { setDownloadList() }
}