package com.gunwoo.karaoke.singsangsung.viewmodel

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.SparseArray
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
import com.gunwoo.karaoke.domain.usecase.DeleteFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.InsertDownloadUseCase
import com.gunwoo.karaoke.domain.usecase.InsertFavoritesUseCase
import com.gunwoo.karaoke.domain.usecase.InsertHidingUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.view.fragment.ListFragment
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.MusicListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import java.io.*
import java.nio.channels.FileChannel
import java.util.*

abstract class MusicViewModel(
    private val insertDownloadUseCase: InsertDownloadUseCase,
    private val insertFavoritesUseCase: InsertFavoritesUseCase,
    private val insertHidingUseCase: InsertHidingUseCase,
    private val deleteFavoritesUseCase: DeleteFavoritesUseCase
) : BaseViewModel() {

    val youtubeDataList = ArrayList<YoutubeData>()

    val musicListAdapter = MusicListAdapter()

    val onSuccessDownloadEvent = SingleLiveEvent<Unit>()
    val onSuccessAddFavoritesEvent = SingleLiveEvent<Unit>()
    val onSuccessDeleteFavoritesEvent = SingleLiveEvent<Unit>()
    val onSuccessHideEvent = SingleLiveEvent<Unit>()

    init { musicListAdapter.setYoutubeDataList(youtubeDataList) }

    fun download(youtubeData: YoutubeData, context: Context) {
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
                override fun onComplete() { }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun addFavorites(youtubeData: YoutubeData) {
        addDisposable(insertFavoritesUseCase.buildUseCaseObservable(InsertFavoritesUseCase.Params(youtubeData)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    val position = youtubeDataList.indexOf(youtubeData)
                    youtubeDataList[position].state = YoutubeData.State.FAVORITES
                    musicListAdapter.notifyItemChanged(position)
                    onSuccessAddFavoritesEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun deleteFavorites(youtubeData: YoutubeData, title: String?) {
        addDisposable(deleteFavoritesUseCase.buildUseCaseObservable(DeleteFavoritesUseCase.Params(youtubeData)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    val position = youtubeDataList.indexOf(youtubeData)
                    if (title == "즐겨찾기목록") {
                        youtubeDataList.remove(youtubeData)
                        musicListAdapter.notifyItemRemoved(position)
                    }
                    else {
                        youtubeDataList[position].state = YoutubeData.State.NONE
                        musicListAdapter.notifyItemChanged(position)
                    }
                    onSuccessDeleteFavoritesEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun hide(youtubeData: YoutubeData) {
        addDisposable(insertHidingUseCase.buildUseCaseObservable(InsertHidingUseCase.Params(youtubeData)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    val position = youtubeDataList.indexOf(youtubeData)
                    youtubeDataList.remove(youtubeData)
                    musicListAdapter.notifyItemRemoved(position)

                    onSuccessHideEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }
}