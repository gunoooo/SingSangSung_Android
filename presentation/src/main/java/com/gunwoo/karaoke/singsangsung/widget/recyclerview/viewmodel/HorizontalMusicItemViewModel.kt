package com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.HorizontalMusicNavigator

class HorizontalMusicItemViewModel : BaseItemViewModel<HorizontalMusicNavigator>() {

    private lateinit var youtubeData: YoutubeData

    val thumbnail = MutableLiveData<String>()
    val title = MutableLiveData<String>()

    fun bind(data: YoutubeData) {
        youtubeData = data

        thumbnail.value = youtubeData.thumbnailUrl
        title.value = youtubeData.videoTitle
    }

    fun onClickItem() { getNavigator().onClickItem(youtubeData) }
}