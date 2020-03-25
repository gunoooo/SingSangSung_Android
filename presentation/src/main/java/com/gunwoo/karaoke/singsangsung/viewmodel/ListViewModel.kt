package com.gunwoo.karaoke.singsangsung.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.domain.usecase.*
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import io.reactivex.observers.DisposableCompletableObserver


class ListViewModel(
    insertDownloadUseCase: InsertDownloadUseCase,
    insertFavoritesUseCase: InsertFavoritesUseCase,
    insertHidingUseCase: InsertHidingUseCase,
    deleteFavoritesUseCase: DeleteFavoritesUseCase,
    private val deleteHidingUseCase: DeleteHidingUseCase
) : MusicViewModel(insertDownloadUseCase, insertFavoritesUseCase, insertHidingUseCase, deleteFavoritesUseCase) {

    lateinit var type: String

    val thumbnail = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val onSuccessDeleteHidingEvent = SingleLiveEvent<Unit>()

    fun setData(youtubeDataList: YoutubeDataList, type: String) {
        this.type = type
        thumbnail.value = youtubeDataList[0].thumbnailUrl
        description.value = "총 ${youtubeDataList.size}곡  |  $type"
        this.youtubeDataList.clear()
        this.youtubeDataList.addAll(youtubeDataList)
        musicListAdapter.notifyDataSetChanged()
    }

    fun deleteHiding(youtubeData: YoutubeData) {
        addDisposable(deleteHidingUseCase.buildUseCaseObservable(DeleteHidingUseCase.Params(youtubeData)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    val position = youtubeDataList.indexOf(youtubeData)
                    youtubeDataList.remove(youtubeData)
                    musicListAdapter.notifyItemRemoved(position)

                    onSuccessDeleteHidingEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }
}