package com.gunwoo.karaoke.singsangsung.widget.recyclerview.music

import com.gunwoo.karaoke.domain.model.YoutubeData

interface MusicHorizontalNavigator {
    fun onClickItem(youtubeData: YoutubeData)
}