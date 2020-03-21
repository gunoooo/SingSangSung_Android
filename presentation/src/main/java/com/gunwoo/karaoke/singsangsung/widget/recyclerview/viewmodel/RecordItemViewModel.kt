package com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Record
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.MusicNavigator
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.RecordNavigator

class RecordItemViewModel : BaseItemViewModel<RecordNavigator>() {

    private lateinit var record: Record

    val thumbnail = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val time = MutableLiveData<String>()

    fun bind(data: Record) {
        record = data

        thumbnail.value = record.thumbnail
        title.value = record.title
        time.value = record.time
    }

    fun onClickItem() { getNavigator().onClickItem(record) }
}