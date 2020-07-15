package com.gunwoo.karaoke.singsangsung.ui.player

import android.media.MediaRecorder
import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.data.util.Constants
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.favorites.InsertFavoritesItemUseCase
import com.gunwoo.karaoke.domain.usecase.hiding.InsertHidingUseCase
import com.gunwoo.karaoke.domain.usecase.recent.InsertRecentUseCase
import com.gunwoo.karaoke.domain.usecase.record.InsertRecordUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.viewpager.PlayerViewPagerAdapter
import io.reactivex.observers.DisposableCompletableObserver
import java.io.File
import java.util.*


class PlayerViewModel(
    private val insertRecordUseCase: InsertRecordUseCase,
    private val insertRecentUseCase: InsertRecentUseCase
) : BaseViewModel() {

    lateinit var video: YoutubeData
    lateinit var videoList: List<YoutubeData>

    private var recorder: MediaRecorder? = null
    private var path: String = Constants.DIRECTORY_RECORD
    private var file: File

    val viewType = MutableLiveData<ViewType>(ViewType.STOP)

    val onSuccessAddFavoritesEvent = SingleLiveEvent<Unit>()
    val onSuccessDeleteFavoritesEvent = SingleLiveEvent<Unit>()
    val onSuccessHideEvent = SingleLiveEvent<Unit>()
    val onStoppedRecording = SingleLiveEvent<Unit>()

    init {
        file = File(path)

        if (!file.exists())
            file.mkdirs()
        else if (!file.isDirectory && file.canWrite()){
            file.delete()
            file.mkdirs()
        }
    }

    fun insertRecent() {
        addDisposable(insertRecentUseCase.buildUseCaseObservable(InsertRecentUseCase.Params(video)),
            object : DisposableCompletableObserver() {
                override fun onComplete() { }

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
            viewType.value =
                ViewType.RECORD
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
            viewType.value =
                ViewType.PAUSE
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