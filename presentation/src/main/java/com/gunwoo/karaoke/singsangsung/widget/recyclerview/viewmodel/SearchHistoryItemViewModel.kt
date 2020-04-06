package com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.SearchHistoryNavigator

class SearchHistoryItemViewModel : BaseItemViewModel<SearchHistoryNavigator>() {

    private lateinit var search: String

    val searchText = MutableLiveData<String>()

    fun bind(data: String) {
        search = data

        searchText.value = search
    }

    fun onClickItem() { getNavigator().onClickItem(search) }

    fun onClickRemove() { getNavigator().onClickRemove(search) }
}