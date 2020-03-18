package com.gunwoo.karaoke.singsangsung.viewmodel

import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.MusicListAdapter

class ListViewModel : BaseViewModel() {

    private lateinit var youtubeDataList: YoutubeDataList

    val musicListAdapter = MusicListAdapter()

    init { musicListAdapter.setYoutubeDataList(youtubeDataList) }

    fun setYoutubeDataList(youtubeDataList: YoutubeDataList) {
        this.youtubeDataList = youtubeDataList
        musicListAdapter.notifyDataSetChanged()
    }
}