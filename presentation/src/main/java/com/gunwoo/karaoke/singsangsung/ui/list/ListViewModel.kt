package com.gunwoo.karaoke.singsangsung.ui.list

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.DeleteHidingUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.InsertHidingUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.music.MusicListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import java.util.*


class ListViewModel(
    private val insertHidingUseCase: InsertHidingUseCase,
    private val deleteHidingUseCase: DeleteHidingUseCase,
    private val deleteFavoritesItemUseCase: DeleteFavoritesItemUseCase
) : BaseViewModel() {

    lateinit var type: String
    lateinit var title: String
    var favoritesId: Int = -1

    val thumbnail = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val youtubeDataList = ArrayList<YoutubeData>()

    val musicListAdapter = MusicListAdapter()

    val onSuccessDeleteFavoritesEvent = SingleLiveEvent<Unit>()
    val onSuccessHideEvent = SingleLiveEvent<Unit>()
    val onSuccessDeleteHidingEvent = SingleLiveEvent<Unit>()

    init { musicListAdapter.setYoutubeDataList(youtubeDataList) }

    fun setData(youtubeDataList: YoutubeDataList, type: String, title: String, favoritesId: Int) {
        this.type = type
        this.title = title
        this.favoritesId = favoritesId
        thumbnail.value = youtubeDataList[0].thumbnailUrl
        description.value = "총 ${youtubeDataList.size}곡  |  $type"
        this.youtubeDataList.clear()
        this.youtubeDataList.addAll(youtubeDataList)
        musicListAdapter.setViewType(if (favoritesId == -1) MusicListAdapter.ViewType.OTHER else MusicListAdapter.ViewType.FAVORITES)
        musicListAdapter.notifyDataSetChanged()
    }

    fun deleteFavoritesItem(youtubeData: YoutubeData) {
        if (favoritesId == -1) return

        addDisposable(deleteFavoritesItemUseCase.buildUseCaseObservable(DeleteFavoritesItemUseCase.Params(youtubeData, favoritesId)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    val position = youtubeDataList.indexOf(youtubeData)
                    youtubeDataList.remove(youtubeData)
                    musicListAdapter.notifyItemRemoved(position)
                    onSuccessDeleteFavoritesEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
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