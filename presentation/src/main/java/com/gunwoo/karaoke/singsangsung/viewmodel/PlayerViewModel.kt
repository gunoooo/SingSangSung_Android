package com.gunwoo.karaoke.singsangsung.viewmodel

import android.media.MediaRecorder
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.data.util.Constants
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.InsertRecordUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.MusicListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import java.io.File
import java.util.*


class PlayerViewModel(
    private val insertRecordUseCase: InsertRecordUseCase
) : BaseViewModel() {

    lateinit var video: YoutubeData
    val youtubeDataList = ArrayList<YoutubeData>()

    private var recorder: MediaRecorder? = null
    private var file: File
    private var path: String

    val musicListAdapter = MusicListAdapter()

    val viewType = MutableLiveData<ViewType>(ViewType.STOP)

    val onStoppedRecording = SingleLiveEvent<Unit>()

    init {
        musicListAdapter.setYoutubeDataList(youtubeDataList)

        path = Constants.DIRECTORY_RECORD

        file = File(path)

        if (!file.exists())
            file.mkdirs()
        else if (!file.isDirectory && file.canWrite()){
            file.delete()
            file.mkdirs()
        }
    }

    fun setMusicList(youtubeDataList: List<YoutubeData>) {
        this.youtubeDataList.clear()
        this.youtubeDataList.addAll(youtubeDataList)
        musicListAdapter.notifyDataSetChanged()
    }

    private fun startRecord() {
        val name = "/SSS_${Random().nextInt(Int.MAX_VALUE)}.mp3"
        val fileName = path + name

        val fileCacheItem = File(fileName)
        fileCacheItem.createNewFile()

        addDisposable(insertRecordUseCase.buildUseCaseObservable(
            InsertRecordUseCase.Params(
                video.videoTitle,
                video.thumbnailUrl!!,
                fileCacheItem)
        ), object : DisposableCompletableObserver() {
            override fun onComplete() {
                recorder = MediaRecorder()

                recorder?.apply {
                    setAudioSource(MediaRecorder.AudioSource.MIC)
                    setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                    setAudioEncoder(MediaRecorder.AudioEncoder.AAC)

                    setOutputFile(fileName)
                }

                recorder?.prepare()
                recorder?.start()
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = Exception(Constants.SERVER_ERROR_MESSAGE)
            }
        })
    }

    private fun pauseRecord() {
        recorder?.pause()
    }

    private fun reStartRecord() {
        recorder?.resume()
    }

    fun stopRecord() {
        recorder?.stop()
        recorder?.release()
        recorder = null
        onStoppedRecording.call()
    }

    fun onClickRecord() {
        if (viewType.value!! == ViewType.PERMISSION) return

        if (viewType.value!! == ViewType.STOP) {
            viewType.value = ViewType.RECORD
            startRecord()
        }
    }

    fun onClickPause() {
        if (viewType.value!! == ViewType.PERMISSION) return

        if (viewType.value!! == ViewType.PAUSE) {
            viewType.value = ViewType.RECORD
            reStartRecord()
        }
        else if (viewType.value!! == ViewType.RECORD) {
            viewType.value = ViewType.PAUSE
            pauseRecord()
        }
    }

    fun onClickStop() {
        if (viewType.value!! == ViewType.PERMISSION) return

        if (viewType.value!! != ViewType.STOP) {
            viewType.value = ViewType.STOP
            stopRecord()
        }
    }

    enum class ViewType {
        PERMISSION,
        STOP,
        RECORD,
        PAUSE
    }
}