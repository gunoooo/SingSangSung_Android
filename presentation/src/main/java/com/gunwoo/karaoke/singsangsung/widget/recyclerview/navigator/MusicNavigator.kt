package com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator

import com.gunwoo.karaoke.domain.model.YoutubeData

interface MusicNavigator {
    fun onClickItem(youtubeData: YoutubeData)

    fun download(youtubeData: YoutubeData)

    fun addFavorites(youtubeData: YoutubeData)

    fun deleteFavorites(youtubeData: YoutubeData)

    fun hide(youtubeData: YoutubeData)

    fun deleteHiding(youtubeData: YoutubeData)

    fun deleteDownload(youtubeData: YoutubeData)
}