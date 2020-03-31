package com.gunwoo.karaoke.singsangsung.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.GetDownloadListUseCase
import com.gunwoo.karaoke.domain.usecase.GetFavoritesListUseCase
import com.gunwoo.karaoke.domain.usecase.GetHidingListUseCase
import com.gunwoo.karaoke.domain.usecase.GetRecentListUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.HorizontalMusicListAdapter
import io.reactivex.observers.DisposableSingleObserver

class FavoritesViewModel(
    private val getRecentListUseCase: GetRecentListUseCase,
    private val getDownloadListUseCase: GetDownloadListUseCase,
    private val getFavoritesListUseCase: GetFavoritesListUseCase,
    private val getHidingListUseCase: GetHidingListUseCase
) : BaseViewModel() {

    val downloadList = ArrayList<Download>()
    val videoList = ArrayList<YoutubeData>()
    val recentList = ArrayList<YoutubeData>()

    val recentListAdapter = HorizontalMusicListAdapter()

    val isEmptyRecentList = MutableLiveData<Boolean>()

    val onOpenOfflineListFragmentEvent = SingleLiveEvent<Unit>()
    val onOpenFavoritesListFragmentEvent = SingleLiveEvent<Unit>()
    val onOpenHidingListFragmentEvent = SingleLiveEvent<Unit>()

    init { recentListAdapter.setYoutubeDataList(recentList) }

    fun setRecentList() {
        addDisposable(getRecentListUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    if (t.isEmpty()) {
                        isEmptyRecentList.value = true
                        return
                    }
                    isEmptyRecentList.value = false
                    recentList.clear()
                    recentList.addAll(t)
                    recentListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

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