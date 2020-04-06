package com.gunwoo.karaoke.singsangsung.viewmodel

import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.domain.model.Record
import com.gunwoo.karaoke.domain.usecase.record.GetRecordListUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.minuteFormat
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.RecordListAdapter
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import kotlin.collections.ArrayList

class RecordViewModel(
    private val getRecordListUseCase: GetRecordListUseCase
) : BaseViewModel() {

    private val recordList = ArrayList<Record>()

    var player: MediaPlayer? = null
    private var record: Record? = null

    val recordListAdapter = RecordListAdapter()

    val viewType = MutableLiveData<ViewType>(ViewType.STOP)
    val isChronological = MutableLiveData<Boolean>(true)
    val isEmpty = MutableLiveData<Boolean>()
    val title = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val thumbnail = MutableLiveData<String>()

    init {
        recordListAdapter.setYoutubeDataList(recordList)
        setRecordList()
    }

    private fun setRecordList() {
        addDisposable(getRecordListUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<Record>>() {
                override fun onSuccess(t: List<Record>) {
                    if (t.isEmpty()) {
                        isEmpty.value = true
                        return
                    }
                    isEmpty.value = false
                    recordList.clear()
                    recordList.addAll(t)
                    recordListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun startAudio(record: Record) {
        stopAudio()
        this.record = record
        title.value = record.title
        thumbnail.value = record.thumbnail
        player = MediaPlayer()
        player?.setOnCompletionListener { viewType.value = ViewType.REPLAY }
        player?.setDataSource(record.file.absolutePath)
        player?.prepare()
        player?.start()
        time.value = Date(player!!.duration.toLong()).minuteFormat()
    }

    private fun reStartAudio() {
        player?.start()
    }

    private fun pauseAudio() {
        player?.pause()
    }

    fun stopAudio() {
        player?.stop()
        player?.release()
        player = null
    }

    fun onClickPause() {
        when {
            viewType.value!! == ViewType.PAUSE -> {
                viewType.value = ViewType.PLAY
                reStartAudio()
            }
            viewType.value!! == ViewType.PLAY -> {
                viewType.value = ViewType.PAUSE
                pauseAudio()
            }
            viewType.value!! == ViewType.REPLAY -> {
                viewType.value = ViewType.PLAY
                startAudio(record!!)
            }
        }
    }

    fun onClickStop() {
        if (viewType.value!! != ViewType.STOP) {
            viewType.value = ViewType.STOP
            stopAudio()
        }
    }

    fun onClickChronological() {
        if (isChronological.value!!) return

        recordList.reverse()
        recordListAdapter.notifyDataSetChanged()

        isChronological.value = true
    }

    fun onClickReverse() {
        if (!isChronological.value!!) return

        recordList.reverse()
        recordListAdapter.notifyDataSetChanged()

        isChronological.value = false
    }

    enum class ViewType {
        STOP,
        PLAY,
        PAUSE,
        REPLAY
    }
}