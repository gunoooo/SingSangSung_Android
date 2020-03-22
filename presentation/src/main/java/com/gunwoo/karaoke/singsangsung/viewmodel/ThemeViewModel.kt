package com.gunwoo.karaoke.singsangsung.viewmodel

import com.gunwoo.karaoke.domain.model.Playlist
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.PlaylistListAdapter

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
        genrePlaylistList.addAll(Playlist.getGenrePlaylistList())
        genrePlaylistListAdapter.notifyDataSetChanged()
    }

    private fun setProgramPlaylist() {
        programPlaylistListAdapter.setPlaylistList(programPlaylistList)
        programPlaylistList.clear()
        programPlaylistList.addAll(Playlist.getProgramPlaylistList())
        programPlaylistListAdapter.notifyDataSetChanged()
    }

    private fun setMoodPlaylist() {
        moodPlaylistListAdapter.setPlaylistList(moodPlaylistList)
        moodPlaylistList.clear()
        moodPlaylistList.addAll(Playlist.getMoodPlaylistList())
        moodPlaylistListAdapter.notifyDataSetChanged()
    }
}