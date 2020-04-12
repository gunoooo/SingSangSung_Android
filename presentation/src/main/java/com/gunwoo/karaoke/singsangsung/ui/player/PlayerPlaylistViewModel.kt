package com.gunwoo.karaoke.singsangsung.ui.player

import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.hiding.InsertHidingUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.music.MusicListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import java.util.*

class PlayerPlaylistViewModel(
    private val insertHidingUseCase: InsertHidingUseCase
) : BaseViewModel() {

    val youtubeDataList = ArrayList<YoutubeData>()

    val musicListAdapter = MusicListAdapter()

    val onSuccessHideEvent = SingleLiveEvent<Unit>()

    init {
        musicListAdapter.setYoutubeDataList(youtubeDataList)
        musicListAdapter.setViewType(MusicListAdapter.ViewType.OTHER)
    }

    fun setYoutubeDataList(youtubeDataList: List<YoutubeData>) {
        this.youtubeDataList.clear()
        this.youtubeDataList.addAll(youtubeDataList)
        musicListAdapter.notifyDataSetChanged()
    }

    fun hide(youtubeData: YoutubeData) {
        addDisposable(insertHidingUseCase.buildUseCaseObservable(InsertHidingUseCase.Params(youtubeData)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    val position = youtubeDataList.indexOf(youtubeData)
                    youtubeDataList.remove(youtubeData)
                    musicListAdapter.notifyItemRemoved(position)
                    onSuccessHideEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }
}