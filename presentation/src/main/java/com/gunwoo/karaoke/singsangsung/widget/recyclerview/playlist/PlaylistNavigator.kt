package com.gunwoo.karaoke.singsangsung.widget.recyclerview.playlist

import com.gunwoo.karaoke.domain.model.Playlist

interface PlaylistNavigator {
    fun onClickItem(playlist: Playlist)
}