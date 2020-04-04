package com.gunwoo.karaoke.singsangsung.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.domain.usecase.*
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BasePlaylistViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import io.reactivex.observers.DisposableCompletableObserver


class ListViewModel(
    insertDownloadUseCase: InsertDownloadUseCase,
    insertFavoritesUseCase: InsertFavoritesUseCase,
    insertHidingUseCase: InsertHidingUseCase,
    deleteFavoritesUseCase: DeleteFavoritesUseCase,
    private val deleteHidingUseCase: DeleteHidingUseCase
) : BasePlaylistViewModel(insertDownloadUseCase, insertFavoritesUseCase, insertHidingUseCase, deleteFavoritesUseCase) {

    lateinit var type: String
    lateinit var title: String

    val thumbnail = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val onSuccessDeleteHidingEvent = SingleLiveEvent<Unit>()

    fun setData(youtubeDataList: YoutubeDataList, type: String, title: String) {
        this.type = type
        this.title = title
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