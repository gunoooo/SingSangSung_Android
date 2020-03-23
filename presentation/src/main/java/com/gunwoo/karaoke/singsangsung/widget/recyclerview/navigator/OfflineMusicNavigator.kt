package com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator

import com.gunwoo.karaoke.domain.model.Download

interface OfflineMusicNavigator {
    fun onClickItem(download: Download)
}