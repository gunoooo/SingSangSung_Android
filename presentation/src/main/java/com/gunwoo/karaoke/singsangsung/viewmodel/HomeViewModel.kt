package com.gunwoo.karaoke.singsangsung.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.data.util.CharacterFactory
import com.gunwoo.karaoke.data.util.Constants
import com.gunwoo.karaoke.data.util.PlaylistFactory
import com.gunwoo.karaoke.domain.model.Character
import com.gunwoo.karaoke.domain.model.Playlist
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.GetPlaylistListUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.CharacterListAdapter
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.PlaylistListAdapter
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.HorizontalMusicListAdapter
import io.reactivex.observers.DisposableSingleObserver

class HomeViewModel(
    private val getPlaylistListUseCase: GetPlaylistListUseCase
) : BaseViewModel() {

    private val characterList = ArrayList<Character>()
    val recommendList = ArrayList<YoutubeData>()
    private val recommendPlaylistList = ArrayList<Playlist>()

    val videoList = ArrayList<YoutubeData>()
    var playlistTitle: String? = null
    var playlistType: String? = null

    val popularityList = MutableLiveData<List<YoutubeData>>()
    val recentlyList = MutableLiveData<List<YoutubeData>>()

    val characterListAdapter = CharacterListAdapter()
    val recommendListAdapter = HorizontalMusicListAdapter()
    val recommendPlaylistListAdapter = PlaylistListAdapter()

    val onClickPopularityChartEvent = SingleLiveEvent<Unit>()
    val onClickRecentlyChartEvent = SingleLiveEvent<Unit>()
    val onOpenListFragmentEvent = SingleLiveEvent<Unit>()

    init {
        setCharacterList()
        setGenrePlaylist()
    }

    fun setPlaylistList() {
        addDisposable(getPlaylistListUseCase.buildUseCaseObservable(GetPlaylistListUseCase.Params(Constants.POPULARITY_PLAYLIST_ID)),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    popularityList.value = t

                    setRecentlyChart()
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

                    setRecommendList()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    private fun setRecommendList() {
        recommendListAdapter.setYoutubeDataList(recommendList)

        addDisposable(getPlaylistListUseCase.buildUseCaseObservable(GetPlaylistListUseCase.Params(Constants.RECOMMEND_PLAYLIST_ID)),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    recommendList.clear()
                    recommendList.addAll(t)
                    recommendListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    private fun setCharacterList() {
        characterListAdapter.setCharacterList(characterList)
        characterList.addAll(CharacterFactory.getCharacterList())
        characterListAdapter.notifyDataSetChanged()
    }

    private fun setGenrePlaylist() {
        recommendPlaylistListAdapter.setPlaylistList(recommendPlaylistList)
        recommendPlaylistList.clear()
        recommendPlaylistList.addAll(PlaylistFactory.getRecommendPlaylistList())
        recommendPlaylistListAdapter.notifyDataSetChanged()
    }

    fun setVideoList(playlistId: String) {
        addDisposable(getPlaylistListUseCase.buildUseCaseObservable(GetPlaylistListUseCase.Params(playlistId)),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    videoList.clear()
                    videoList.addAll(t)
                    onOpenListFragmentEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun onClickPopularityChart() {
        onClickPopularityChartEvent.call()
    }

    fun onClickRecentlyChart() {
        onClickRecentlyChartEvent.call()
    }
}