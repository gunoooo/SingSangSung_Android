package com.gunwoo.karaoke.singsangsung.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel

class PlayerViewModel : BaseViewModel() {

    lateinit var videoId: String

    val viewType = MutableLiveData<ViewType>(ViewType.STOP)

    fun onClickRecord() { if (viewType.value!! == ViewType.STOP) viewType.value = ViewType.RECORD }

    fun onClickPause() {
        if (viewType.value!! == ViewType.PAUSE)
            viewType.value = ViewType.RECORD
        else if (viewType.value!! == ViewType.RECORD)
            viewType.value = ViewType.PAUSE
    }

    fun onClickStop() { if (viewType.value!! != ViewType.STOP) viewType.value = ViewType.STOP }

    enum class ViewType {
        STOP,
        RECORD,
        PAUSE
    }
}