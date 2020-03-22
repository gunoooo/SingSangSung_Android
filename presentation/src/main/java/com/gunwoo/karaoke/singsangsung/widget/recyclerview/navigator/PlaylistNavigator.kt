package com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator

import com.gunwoo.karaoke.domain.model.Playlist

interface PlaylistNavigator {
    fun onClickItem(playlist: Playlist)
}