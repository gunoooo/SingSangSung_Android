package com.gunwoo.karaoke.singsangsung.viewmodel

import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent

class PlayerControllerViewModel : BaseViewModel() {

    val onPitchUpEvent = SingleLiveEvent<Unit>()
    val onPitchDownEvent = SingleLiveEvent<Unit>()
    val onTempoUpEvent = SingleLiveEvent<Unit>()
    val onTempoDownEvent = SingleLiveEvent<Unit>()

    fun onClickPitchUp() { onPitchUpEvent.call() }

    fun onClickPitchDown() { onPitchDownEvent.call() }

    fun onClickTempoUp() { onTempoUpEvent.call() }

    fun onClickTempoDown() { onTempoDownEvent.call() }
}