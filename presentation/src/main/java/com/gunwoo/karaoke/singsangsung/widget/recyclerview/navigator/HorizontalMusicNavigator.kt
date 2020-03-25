package com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator

import com.gunwoo.karaoke.domain.model.YoutubeData

interface HorizontalMusicNavigator {
    fun onClickItem(youtubeData: YoutubeData)
}