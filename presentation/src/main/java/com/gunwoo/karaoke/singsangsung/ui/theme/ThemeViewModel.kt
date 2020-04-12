package com.gunwoo.karaoke.singsangsung.ui.theme

import com.gunwoo.karaoke.data.util.PlaylistFactory
import com.gunwoo.karaoke.domain.model.Playlist
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.playlist.PlaylistListAdapter

class ThemeViewModel : BaseViewModel() {

    private val genrePlaylistList = ArrayList<Playlist>()
    private val programPlaylistList = ArrayList<Playlist>()
    private val moodPlaylistList = ArrayList<Playlist>()

    val genrePlaylistListAdapter = PlaylistListAdapter()
    val programPlaylistListAdapter = PlaylistListAdapter()
    val moodPlaylistListAdapter = PlaylistListAdapter()

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
}