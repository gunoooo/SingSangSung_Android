package com.gunwoo.karaoke.singsangsung.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.DeleteHidingUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.InsertHidingUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.MusicListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import kr.co.prnd.YouTubePlayerView
import java.util.*


class ListViewModel(
    private val insertFavoritesItemUseCase: InsertFavoritesItemUseCase,
    private val insertHidingUseCase: InsertHidingUseCase,
    private val deleteFavoritesItemUseCase: DeleteFavoritesItemUseCase,
    private val deleteHidingUseCase: DeleteHidingUseCase
) : BaseViewModel() {

    lateinit var type: String
    lateinit var title: String

    val thumbnail = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val youtubeDataList = ArrayList<YoutubeData>()

    val musicListAdapter = MusicListAdapter()

    val onSuccessAddFavoritesEvent = SingleLiveEvent<Unit>()
    val onSuccessDeleteFavoritesEvent = SingleLiveEvent<Unit>()
    val onSuccessHideEvent = SingleLiveEvent<Unit>()
    val onSuccessDeleteHidingEvent = SingleLiveEvent<Unit>()

    init { musicListAdapter.setYoutubeDataList(youtubeDataList) }

    fun setData(youtubeDataList: YoutubeDataList, type: String, title: String) {
        this.type = type
        this.title = title
        thumbnail.value = youtubeDataList[0].thumbnailUrl
        description.value = "총 ${youtubeDataList.size}곡  |  $type"
        this.youtubeDataList.clear()
        this.youtubeDataList.addAll(youtubeDataList)
        musicListAdapter.notifyDataSetChanged()
    }

    fun addFavorites(youtubeData: YoutubeData) {
        addDisposable(insertFavoritesItemUseCase.buildUseCaseObservable(InsertFavoritesItemUseCase.Params(youtubeData)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    youtubeDataList
                        .forEachIndexed { index: Int, data: YoutubeData ->
                            if (data.videoId == youtubeData.videoId) {
                                youtubeDataList[index].state = YoutubeData.State.FAVORITES
                                musicListAdapter.notifyItemChanged(index)
                            }
                        }
                    onSuccessAddFavoritesEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun deleteFavorites(youtubeData: YoutubeData) {
        addDisposable(deleteFavoritesItemUseCase.buildUseCaseObservable(DeleteFavoritesItemUseCase.Params(youtubeData)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    if (title == "즐겨찾기 목록") {
                        youtubeDataList.remove(youtubeData)
                        musicListAdapter.notifyItemRemoved(youtubeDataList.indexOf(youtubeData))
                    }
                    else {
                        youtubeDataList
                            .forEachIndexed { index: Int, data: YoutubeData ->
                                if (data.videoId == youtubeData.videoId) {
                                    youtubeDataList[index].state = YoutubeData.State.NONE
                                    musicListAdapter.notifyItemChanged(index)
                                }
                            }
                    }
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