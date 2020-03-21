package com.gunwoo.karaoke.singsangsung.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.MusicListAdapter

class ListViewModel : BaseViewModel() {

    val youtubeDataList = ArrayList<YoutubeData>()
    lateinit var type: String

    val thumbnail = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val musicListAdapter = MusicListAdapter()

    init { musicListAdapter.setYoutubeDataList(youtubeDataList) }

    fun setData(youtubeDataList: YoutubeDataList, type: String) {
        this.type = type
        thumbnail.value = youtubeDataList[0].thumbnailUrl
        description.value = "총 ${youtubeDataList.size}곡  |  $type"
        this.youtubeDataList.clear()
        this.youtubeDataList.addAll(youtubeDataList)
        musicListAdapter.notifyDataSetChanged()
    }
}