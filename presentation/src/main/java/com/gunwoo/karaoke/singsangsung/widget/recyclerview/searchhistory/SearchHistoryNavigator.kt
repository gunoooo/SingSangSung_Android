package com.gunwoo.karaoke.singsangsung.widget.recyclerview.searchhistory

interface SearchHistoryNavigator {
    fun onClickItem(search: String)

    fun onClickRemove(search: String)
}