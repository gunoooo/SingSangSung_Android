package com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator

interface SearchHistoryNavigator {
    fun onClickItem(search: String)

    fun onClickRemove(search: String)
}