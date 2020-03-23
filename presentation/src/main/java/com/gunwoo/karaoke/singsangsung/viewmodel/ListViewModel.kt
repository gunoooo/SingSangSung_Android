package com.gunwoo.karaoke.singsangsung.viewmodel

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
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
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.domain.usecase.InsertDownloadUseCase
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter.MusicListAdapter
import io.reactivex.observers.DisposableCompletableObserver
import java.io.*
import java.nio.channels.FileChannel
import java.util.*
import kotlin.collections.ArrayList


class ListViewModel(
    private val insertDownloadUseCase: InsertDownloadUseCase
) : BaseViewModel() {

    val youtubeDataList = ArrayList<YoutubeData>()
    lateinit var type: String

    val thumbnail = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val musicListAdapter = MusicListAdapter()

    val onSuccessDownloadEvent = SingleLiveEvent<Unit>()

    init { musicListAdapter.setYoutubeDataList(youtubeDataList) }

    fun setData(youtubeDataList: YoutubeDataList, type: String) {
        this.type = type
        thumbnail.value = youtubeDataList[0].thumbnailUrl
        description.value = "총 ${youtubeDataList.size}곡  |  $type"
        this.youtubeDataList.clear()
        this.youtubeDataList.addAll(youtubeDataList)
        musicListAdapter.notifyDataSetChanged()
    }

    fun download(youtubeData: YoutubeData, context: Context) {
       val youtubeExtractor = @SuppressLint("StaticFieldLeak")
           object : YouTubeExtractor(context) {
                override fun onExtractionComplete(ytFiles: SparseArray<YtFile>, vMeta: VideoMeta) {
                    val tag = ytFiles.keyAt(0)
                    val downloadUrl = ytFiles[tag].url
                    val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager?
                    val uri: Uri = Uri.parse(downloadUrl)
                    val request = DownloadManager.Request(uri)
                    request.setDescription("싱생송 다운로드").setTitle(youtubeData.videoTitle)
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    val videoFileName = "SSS_${Random().nextInt(Int.MAX_VALUE)}.mp4"
                    request.setDestinationInExternalPublicDir(
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
                                val filePath = Constants.DIRECTORY_DOWNLOAD
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

                                val video = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/$videoFileName")
                                insertDownload(youtubeData.videoId!!, youtubeData.videoTitle, fileOutput, video)
                            }
                        })
                }
            }

        youtubeExtractor.extract("http://youtube.com/watch?v=${youtubeData.videoId}", true, false)
    }

    private fun insertDownload(videoId: String, title: String, thumbnail: File?, video: File) {
        addDisposable(insertDownloadUseCase.buildUseCaseObservable(InsertDownloadUseCase.Params(videoId, title, thumbnail, video)),
            object : DisposableCompletableObserver() {
                override fun onComplete() { }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun addFavorites(youtubeData: YoutubeData) {

    }

    fun hide(youtubeData: YoutubeData) {

    }
}