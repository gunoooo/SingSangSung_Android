package com.gunwoo.karaoke.singsangsung.viewmodel

import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.GetDownloadListUseCase
import com.gunwoo.karaoke.domain.usecase.GetFavoritesListUseCase
import com.gunwoo.karaoke.domain.usecase.GetHidingListUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import io.reactivex.observers.DisposableSingleObserver

class FavoritesViewModel(
    private val getDownloadListUseCase: GetDownloadListUseCase,
    private val getFavoritesListUseCase: GetFavoritesListUseCase,
    private val getHidingListUseCase: GetHidingListUseCase
) : BaseViewModel() {

    val downloadList = ArrayList<Download>()
    val videoList = ArrayList<YoutubeData>()

    val onOpenOfflineListFragmentEvent = SingleLiveEvent<Unit>()
    val onOpenFavoritesListFragmentEvent = SingleLiveEvent<Unit>()
    val onOpenHidingListFragmentEvent = SingleLiveEvent<Unit>()

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

    private fun setFavoritesList() {
        addDisposable(getFavoritesListUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    videoList.clear()
                    videoList.addAll(t)
                    onOpenFavoritesListFragmentEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    private fun setHidingList() {
        addDisposable(getHidingListUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    videoList.clear()
                    videoList.addAll(t)
                    onOpenHidingListFragmentEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun onClickDownloadList() { setDownloadList() }

    fun onClickFavoritesList() { setFavoritesList() }

    fun onClickHidingList() { setHidingList() }
}