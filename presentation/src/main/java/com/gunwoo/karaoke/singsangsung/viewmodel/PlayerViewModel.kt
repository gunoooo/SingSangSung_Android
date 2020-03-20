package com.gunwoo.karaoke.singsangsung.viewmodel

import android.media.MediaRecorder
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.data.util.Constants
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import java.io.File
import java.util.*


class PlayerViewModel : BaseViewModel() {

    lateinit var videoId: String

    private var recorder: MediaRecorder? = null
    private var fileName: String

    val viewType = MutableLiveData<ViewType>(ViewType.STOP)

    init {
        val path = Constants.DIRECTORY_RECORD
        val name = "/SSS_${Random().nextInt(Int.MAX_VALUE)}.mp3"
        val file = File(path)

        if (!file.exists())
            file.mkdirs()
        else if (!file.isDirectory && file.canWrite()){
            file.delete()
            file.mkdirs()
        }

        val fileCacheItem = File(path + name)
        fileCacheItem.createNewFile()

        fileName = fileCacheItem.absolutePath
    }

    private fun startRecord() {
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

    private fun pauseRecord() {
        recorder?.pause()
    }

    private fun reStartRecord() {
        recorder?.resume()
    }

    private fun stopRecord() {
        recorder?.stop()
        recorder?.release()
        recorder = null
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