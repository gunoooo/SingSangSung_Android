package com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator

import com.gunwoo.karaoke.domain.model.YoutubeData

interface RecommendNavigator {
    fun onClickItem(youtubeData: YoutubeData)
}