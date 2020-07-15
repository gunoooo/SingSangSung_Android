package com.gunwoo.karaoke.singsangsung.ui.theme

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.data.util.PlaylistFactory
import com.gunwoo.karaoke.domain.model.Playlist
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.playlist.GetPlaylistListUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.playlist.PlaylistListAdapter
import io.reactivex.observers.DisposableSingleObserver

class ThemeViewModel(
    private val getPlaylistListUseCase: GetPlaylistListUseCase
) : BaseViewModel() {

    private val genrePlaylistList = ArrayList<Playlist>()
    private val programPlaylistList = ArrayList<Playlist>()
    private val moodPlaylistList = ArrayList<Playlist>()

    val genrePlaylistListAdapter = PlaylistListAdapter()
    val programPlaylistListAdapter = PlaylistListAdapter()
    val moodPlaylistListAdapter = PlaylistListAdapter()

    val playlistItemList = SingleLiveEvent<PlaylistWithItem>()

    init {
        setGenrePlaylist()
        setProgramPlaylist()
        setMoodPlaylist()
    }

    private fun setGenrePlaylist() {
        genrePlaylistListAdapter.setPlaylistList(genrePlaylistList)
        genrePlaylistList.clear()
        genrePlaylistList.addAll(PlaylistFactory.getGenrePlaylistList())
        genrePlaylistListAdapter.notifyDataSetChanged()
    }

    private fun setProgramPlaylist() {
        programPlaylistListAdapter.setPlaylistList(programPlaylistList)
        programPlaylistList.clear()
        programPlaylistList.addAll(PlaylistFactory.getProgramPlaylistList())
        programPlaylistListAdapter.notifyDataSetChanged()
    }

    private fun setMoodPlaylist() {
        moodPlaylistListAdapter.setPlaylistList(moodPlaylistList)
        moodPlaylistList.clear()
        moodPlaylistList.addAll(PlaylistFactory.getMoodPlaylistList())
        moodPlaylistListAdapter.notifyDataSetChanged()
    }

    fun setPlaylistItemList(playlist: Playlist) {
        addDisposable(getPlaylistListUseCase.buildUseCaseObservable(GetPlaylistListUseCase.Params(playlist.playlistId)),
            object : DisposableSingleObserver<List<YoutubeData>>() {
                override fun onSuccess(t: List<YoutubeData>) {
                    playlistItemList.value = PlaylistWithItem(t, playlist)
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    data class PlaylistWithItem(
        val playlistItemList: List<YoutubeData>,
        val playlist: Playlist
    )
}