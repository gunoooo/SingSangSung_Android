package com.gunwoo.karaoke.singsangsung.viewmodel

import android.media.MediaRecorder
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.data.util.Constants
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.DeleteDownloadUseCase
import com.gunwoo.karaoke.domain.usecase.InsertRecordUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.MusicListAdapter
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.OfflineMusicListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import java.io.File
import java.util.*


class OfflinePlayerViewModel(
    private val insertRecordUseCase: InsertRecordUseCase,
    private val deleteDownloadUseCase: DeleteDownloadUseCase
) : BaseViewModel() {

    lateinit var video: Download
    val downloadList = ArrayList<Download>()

    private var recorder: MediaRecorder? = null
    private var file: File
    private var path: String

    val offlineMusicListAdapter = OfflineMusicListAdapter()

    val viewType = MutableLiveData<ViewType>(ViewType.STOP)

    val onStoppedRecording = SingleLiveEvent<Unit>()
    val onSuccessDeleteDownloadEvent = SingleLiveEvent<Unit>()

    init {
        offlineMusicListAdapter.setDownloadList(downloadList)

        path = Constants.DIRECTORY_RECORD

        file = File(path)

        if (!file.exists())
            file.mkdirs()
        else if (!file.isDirectory && file.canWrite()){
            file.delete()
            file.mkdirs()
        }
    }

    fun setDownloadList(downloadList: List<Download>) {
        this.downloadList.clear()
        this.downloadList.addAll(downloadList)
        offlineMusicListAdapter.notifyDataSetChanged()
    }

    fun deleteDownload(download: Download) {
        addDisposable(deleteDownloadUseCase.buildUseCaseObservable(DeleteDownloadUseCase.Params(download)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    val position = downloadList.indexOf(download)
                    downloadList.remove(download)
                    offlineMusicListAdapter.notifyItemRemoved(position)
                    onSuccessDeleteDownloadEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    private fun startRecord() {
        val name = "/SSS_${Random().nextInt(Int.MAX_VALUE)}.mp3"
        val fileName = path + name

        val fileCacheItem = File(fileName)
        fileCacheItem.createNewFile()

        addDisposable(insertRecordUseCase.buildUseCaseObservable(
            InsertRecordUseCase.Params(
                video.title,
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