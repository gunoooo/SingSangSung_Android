package com.gunwoo.karaoke.singsangsung.widget.recyclerview.playlist

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Playlist
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel

class PlaylistItemViewModel : BaseItemViewModel<PlaylistNavigator>() {

    private lateinit var playlist: Playlist

    val thumbnail = MutableLiveData<Int>()
    val title = MutableLiveData<String>()

    fun bind(data: Playlist) {
        playlist = data

        thumbnail.value = playlist.thumbnail
        title.value = playlist.title
    }

    fun onClickItem() { getNavigator().onClickItem(playlist) }
}