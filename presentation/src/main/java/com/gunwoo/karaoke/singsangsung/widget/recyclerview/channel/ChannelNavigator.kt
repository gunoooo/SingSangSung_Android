package com.gunwoo.karaoke.singsangsung.widget.recyclerview.channel

import com.gunwoo.karaoke.domain.model.SearchSetting

interface ChannelNavigator {
    fun onClickItem(searchSetting: SearchSetting)

    fun deleteSearchSetting(searchSetting: SearchSetting)

    fun updateSearchSetting(searchSetting: SearchSetting)
}