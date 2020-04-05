package com.gunwoo.karaoke.singsangsung.viewmodel

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.media.MediaRecorder
import android.net.Uri
import android.os.Environment
import android.util.SparseArray
import androidx.lifecycle.MutableLiveData
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.gunwoo.karaoke.data.util.Constants
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.usecase.*
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.viewpager.PlayerViewPagerAdapter
import io.reactivex.observers.DisposableCompletableObserver
import java.io.*
import java.nio.channels.FileChannel
import java.util.*


class PlayerViewModel(
    private val insertRecordUseCase: InsertRecordUseCase,
    private val insertRecentUseCase: InsertRecentUseCase,
    private val insertDownloadUseCase: InsertDownloadUseCase,
    private val insertFavoritesUseCase: InsertFavoritesUseCase,
    private val insertHidingUseCase: InsertHidingUseCase,
    private val deleteFavoritesUseCase: DeleteFavoritesUseCase,
    private val deleteDownloadByVideoIdUseCase: DeleteDownloadByVideoIdUseCase,
    private val context: Context
) : BaseViewModel() {

    lateinit var playerViewPagerAdapter: PlayerViewPagerAdapter
    lateinit var video: YoutubeData
    lateinit var videoList: List<YoutubeData>

    private var recorder: MediaRecorder? = null
    private var path: String = Constants.DIRECTORY_RECORD
    private var file: File

    val viewType = MutableLiveData<ViewType>(ViewType.STOP)

    val onSuccessDownloadEvent = SingleLiveEvent<Unit>()
    val onSuccessDeleteDownloadEvent = SingleLiveEvent<Unit>()
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

    fun download(youtubeData: YoutubeData = video) {
        val youtubeExtractor =
            @SuppressLint("StaticFieldLeak")
            object : YouTubeExtractor(context) {
                override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta) {
                    val tag = ytFiles?.keyAt(0) ?: return
                    val downloadUrl = ytFiles[tag].url
                    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
                    val uri: Uri = Uri.parse(downloadUrl)
                    val request = DownloadManager.Request(uri)
                    request.setDescription("싱생송 다운로드").setTitle(youtubeData.videoTitle)
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    val videoFileName = "SSS_${Random().nextInt(Int.MAX_VALUE)}.mp4"
                    request.setDestinationInExternalFilesDir(
                        context,
                        Environment.DIRECTORY_DOWNLOADS,
                        videoFileName
                    )
                    downloadManager!!.enqueue(request)

                    Glide.with(context)
                        .asFile()
                        .load(youtubeData.thumbnailUrl)
                        .apply {
                            RequestOptions()
                                .format(DecodeFormat.PREFER_ARGB_8888)
                                .override(Target.SIZE_ORIGINAL)
                        }.into(object : SimpleTarget<File>() {
                            override fun onResourceReady(resource: File, transition: Transition<in File>?) {
                                val filenameArray: Array<String> = youtubeData.thumbnailUrl?.split(".")?.toTypedArray() ?: return
                                val fileExtension= filenameArray[filenameArray.size - 1]
                                val filePath = Constants.getDownloadThumbnailDirectory(context)
                                val fileName = "/SSS_${Random().nextInt(Int.MAX_VALUE)}.$fileExtension"
                                val file = File(filePath)

                                if (!file.exists())
                                    file.mkdirs()
                                else if (!file.isDirectory && file.canWrite()){
                                    file.delete()
                                    file.mkdirs()
                                }

                                val fileOutput = File(filePath + fileName)

                                try {
                                    val output = FileOutputStream(fileOutput)
                                    val input = FileInputStream(resource)
                                    val inputChannel: FileChannel = input.channel
                                    val outputChannel: FileChannel = output.channel
                                    inputChannel.transferTo(0, inputChannel.size(), outputChannel)
                                    output.close()
                                    input.close()

                                    context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(fileOutput)))
                                } catch (e: FileNotFoundException) {
                                    e.printStackTrace()
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }

                                val video = File("${Constants.getDownloadVideoDirectory(context)}/$videoFileName")
                                insertDownload(Download(youtubeData.videoId!!, youtubeData.videoTitle, fileOutput, youtubeData.thumbnailUrl, video))
                            }
                        })
                }
            }

        youtubeExtractor.extract("http://youtube.com/watch?v=${youtubeData.videoId}", true, false)
    }

    private fun insertDownload(download: Download) {
        addDisposable(insertDownloadUseCase.buildUseCaseObservable(InsertDownloadUseCase.Params(download)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onSuccessDownloadEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun deleteDownload(youtubeData: YoutubeData = video) {
        addDisposable(deleteDownloadByVideoIdUseCase.buildUseCaseObservable(DeleteDownloadByVideoIdUseCase.Params(youtubeData.videoId!!)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onSuccessDeleteDownloadEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun addFavorites(youtubeData: YoutubeData = video) {
        addDisposable(insertFavoritesUseCase.buildUseCaseObservable(InsertFavoritesUseCase.Params(youtubeData)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onSuccessAddFavoritesEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun deleteFavorites(youtubeData: YoutubeData = video) {
        addDisposable(deleteFavoritesUseCase.buildUseCaseObservable(DeleteFavoritesUseCase.Params(youtubeData)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onSuccessDeleteFavoritesEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun hide(youtubeData: YoutubeData = video) {
        addDisposable(insertHidingUseCase.buildUseCaseObservable(InsertHidingUseCase.Params(youtubeData)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onSuccessHideEvent.call()
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