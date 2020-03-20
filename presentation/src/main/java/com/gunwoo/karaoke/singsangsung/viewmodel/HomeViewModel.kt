package com.gunwoo.karaoke.singsangsung.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.data.util.Constants
import com.gunwoo.karaoke.domain.model.Character
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.GetPlaylistListUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.CharacterListAdapter
import io.reactivex.observers.DisposableSingleObserver

class HomeViewModel(
    private val getPlaylistListUseCase: GetPlaylistListUseCase
) : BaseViewModel() {

    private val characterList = ArrayList<Character>()

    val popularityList = MutableLiveData<List<YoutubeData>>()
    val recentlyList = MutableLiveData<List<YoutubeData>>()

    val characterListAdapter = CharacterListAdapter()

    val onClickPopularityChartEvent = SingleLiveEvent<Unit>()
    val onClickRecentlyChartEvent = SingleLiveEvent<Unit>()

    init {
        setPlaylist()
        setCharacterList()
    }

    fun setPlaylist() {
        setPopularityChart()
        setRecentlyChart()
    }

    private fun setPopularityChart() {
        addDisposable(getPlaylistListUseCase.buildUseCaseObservable(GetPlaylistListUseCase.Params(Constants.POPULARITY_PLAYLIST_ID)),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    popularityList.value = t
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    private fun setRecentlyChart() {
        addDisposable(getPlaylistListUseCase.buildUseCaseObservable(GetPlaylistListUseCase.Params(Constants.RECENTLY_PLAYLIST_ID)),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    recentlyList.value = t
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    private fun setCharacterList() {
        characterListAdapter.setCharacterList(characterList)
        characterList.addAll(Character.getCharacterList())
        characterListAdapter.notifyDataSetChanged()
    }

    fun onClickPopularityChart() {
        onClickPopularityChartEvent.call()
    }

    fun onClickRecentlyChart() {
        onClickRecentlyChartEvent.call()
    }
}