package com.gunwoo.karaoke.data.datasource

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.util.SparseArray
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.AsyncSubject
import javax.inject.Inject

class ExtractDataSource @Inject constructor(val context: Context) {

    private val YOUTUBE_ITAG_22 = 22      // mp4 - stereo, 44.1 KHz 192 Kbps (aac)
    private val YOUTUBE_ITAG_18 = 18      // mp4 - stereo, 44.1 KHz 96 Kbps (aac)
    private val YOUTUBE_ITAG_36 = 36      // mp4 - stereo, 44.1 KHz 32 Kbps (aac)
    private val YOUTUBE_ITAG_17 = 17      // mp4 - stereo, 44.1 KHz 24 Kbps (aac)

    @SuppressLint("StaticFieldLeak")
    fun extract(videoId: String): Observable<String> {
        val streamUrl = AsyncSubject.create<String>()
        object: YouTubeExtractor(context) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {
                    val url = getBestStream(ytFiles)?.url
                    if (url != null) {
                        streamUrl.onNext(url)
                    }
                    else {
                        streamUrl.onError(Exception())
                    }
                }
                else {
                    streamUrl.onError(Exception())
                }
                streamUrl.onComplete()
            }
        }.extract("http://youtube.com/watch?v=${videoId}", true, false)
        return streamUrl
    }

    private fun getBestStream(ytFiles: SparseArray<YtFile>): YtFile? {
        when {
            ytFiles.get(YOUTUBE_ITAG_18) != null -> {
                Log.i(ContentValues.TAG, " gets YOUTUBE_ITAG_18")
                return ytFiles.get(YOUTUBE_ITAG_18)
            }
            ytFiles.get(YOUTUBE_ITAG_22) != null -> {
                Log.i(ContentValues.TAG, " gets YOUTUBE_ITAG_22")
                return ytFiles.get(YOUTUBE_ITAG_22)
            }
            ytFiles.get(YOUTUBE_ITAG_36) != null -> {
                Log.i(ContentValues.TAG, " gets YOUTUBE_ITAG_36")
                return ytFiles.get(YOUTUBE_ITAG_36)
            }
            ytFiles.get(YOUTUBE_ITAG_17) != null -> {
                Log.i(ContentValues.TAG, " gets YOUTUBE_ITAG_17")
                return ytFiles.get(YOUTUBE_ITAG_17)
            }
            else -> {
                Log.e(ContentValues.TAG, " NOT FOUNT YOUTUBE URL")
                return null
            }
        }
    }
}