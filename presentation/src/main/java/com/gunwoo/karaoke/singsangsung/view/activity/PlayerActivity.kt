package com.gunwoo.karaoke.singsangsung.view.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.MediaController
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseActivity
import com.gunwoo.karaoke.singsangsung.databinding.ActivityPlayerBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.PlayerViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.PlayerViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.SingSangSungVideoView
import com.gunwoo.karaoke.singsangsung.widget.extension.*
import kotlinx.android.synthetic.main.activity_player.*
import kr.co.prnd.YouTubePlayerView
import javax.inject.Inject


class PlayerActivity : BaseActivity<ActivityPlayerBinding, PlayerViewModel>(),
    YouTubePlayerView.OnInitializedListener,
    YouTubePlayer.OnFullscreenListener {

    @Inject
    lateinit var viewModelFactory: PlayerViewModelFactory

    override val viewModel: PlayerViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            viewType.observe(this@PlayerActivity, Observer {
                when(it.name) {
                    PlayerViewModel.ViewType.STOP.name -> {
                        play_btn.setImageDrawable(getDrawable(R.drawable.ic_pause))

                        record_btn.setImageTint(R.color.colorRed)
                        stop_btn.setImageTint(R.color.colorBlackTransparent)
                        play_btn.setImageTint(R.color.colorBlackTransparent)

                        record_sound.setImageDrawable(null)
                    }
                    PlayerViewModel.ViewType.RECORD.name -> {
                        play_btn.setImageDrawable(getDrawable(R.drawable.ic_pause))

                        record_btn.setImageTint(R.color.colorRedTransparent)
                        stop_btn.setImageTint(R.color.colorPrimary)
                        play_btn.setImageTint(R.color.colorPrimary)

                        setMusicGif(record_sound)
                    }
                    PlayerViewModel.ViewType.PAUSE.name -> {
                        play_btn.setImageDrawable(getDrawable(R.drawable.ic_play))

                        record_btn.setImageTint(R.color.colorRedTransparent)
                        stop_btn.setImageTint(R.color.colorPrimary)
                        play_btn.setImageTint(R.color.colorPrimary)

                        record_sound.setImageDrawable(null)
                    }
                }
            })

            onStoppedRecording.observe(this@PlayerActivity, Observer { shortToast(R.string.message_stopped_recoding) })

            musicListAdapter.onClickItem.observe(this@PlayerActivity, Observer {
                startActivity(
                    Intent(this@PlayerActivity.applicationContext, PlayerActivity::class.java)
                        .putExtra(EXTRA_VIDEO, it)
                        .putExtra(EXTRA_VIDEO_LIST, youtubeDataList))

                finish()
            })

            with(musicListAdapter) {
                onClickItem.observe(this@PlayerActivity, Observer {
                    startActivity(
                        Intent(this@PlayerActivity.applicationContext, PlayerActivity::class.java)
                            .putExtra(EXTRA_VIDEO, it)
                            .putExtra(EXTRA_VIDEO_LIST, youtubeDataList))

                    finish()
                })

                onDownloadEvent.observe(this@PlayerActivity, Observer {
                    mViewModel.download(it, this@PlayerActivity.applicationContext)
                })

                onAddFavoritesEvent.observe(this@PlayerActivity, Observer {
                    mViewModel.addFavorites(it)
                })

                onDeleteFavoritesEvent.observe(this@PlayerActivity, Observer {
                    mViewModel.deleteFavorites(it, null)
                })

                onHideEvent.observe(this@PlayerActivity, Observer {
                    mViewModel.hide(it)
                })
            }

            onSuccessDownloadEvent.observe(this@PlayerActivity, Observer {
                shortToast(R.string.message_download_complete)
            })

            onSuccessAddFavoritesEvent.observe(this@PlayerActivity, Observer {
                shortToast(R.string.message_add_favorites_complete)
            })

            onSuccessDeleteFavoritesEvent.observe(this@PlayerActivity, Observer {
                shortToast(R.string.message_delete_favorites_complete)
            })

            onSuccessHideEvent.observe(this@PlayerActivity, Observer {
                shortToast(R.string.message_hide_complete)
            })

            onErrorEvent.observe(this@PlayerActivity, Observer {
                shortToast(it.message)
            })
        }
    }

    private lateinit var player: YouTubePlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!checkPermission()) mViewModel.viewType.value = PlayerViewModel.ViewType.PERMISSION
        initIntent()
    }

    private fun checkPermission() = ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED

    private fun setMusicGif(view: ImageView) { view.putImage(R.drawable.music) }

    @Suppress("UNCHECKED_CAST")
    private fun initIntent() {
        val video = intent.getSerializableExtra(EXTRA_VIDEO)
        if (video == null) {
            finish()
            return
        }
        mViewModel.video = video as YoutubeData
        initVideo()
        mViewModel.insertRecent()

        val videoList = intent.getSerializableExtra(EXTRA_VIDEO_LIST) ?: return
        mViewModel.setMusicList(videoList as ArrayList<YoutubeData>)
    }

    private fun initVideo() {
        val youtubeExtractor =
            @SuppressLint("StaticFieldLeak")
            object : YouTubeExtractor(this) {
                override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta) {
                    val tag = ytFiles?.keyAt(0) ?: return
                    val url = ytFiles[tag].url
                    initVideoView(url)
                }
            }

        youtubeExtractor.extract("http://youtube.com/watch?v=${mViewModel.video.videoId}", true, false)
    }

    private fun initVideoView(videoUrl: String) {
        video_view.setVideoPath(videoUrl)

        val controller = MediaController(this)
        controller.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
        (controller.parent as ViewGroup).removeView(controller)
        controller.visibility = View.INVISIBLE
        video_frame_layout.addView(controller)

        video_view.setMediaController(controller)
        controller.setAnchorView(video_view)
        video_view.start()

        video_view.setOnClickListener {
            if (controller.visibility == View.VISIBLE)
                controller.visibility = View.INVISIBLE
            else
                controller.visibility = View.VISIBLE
        }

        video_view.setPlayPauseListener(object : SingSangSungVideoView.PlayPauseListener {
            override fun onPlay() {
                setMusicGif(music_sound)
            }

            override fun onPause() {
                music_sound.setImageDrawable(null)
            }
        })

        video_view.setOnCompletionListener {
            music_sound.setImageDrawable(null)
            stop()
        }
    }

    private fun stop() {
        with(mViewModel) {
            if (viewType.value!! == PlayerViewModel.ViewType.PERMISSION) return

            if (viewType.value!! != PlayerViewModel.ViewType.STOP) {
                viewType.value = PlayerViewModel.ViewType.STOP
                stopRecord()
            }
        }
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
        this.player = player
        this.player.loadVideo(mViewModel.video.videoId!!, 0)
        this.player.setOnFullscreenListener(this)
        this.player.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener {
            override fun onAdStarted() { }

            override fun onLoading() { }

            override fun onVideoStarted() { }

            override fun onLoaded(p0: String?) { }

            override fun onVideoEnded() { stop() }

            override fun onError(p0: YouTubePlayer.ErrorReason?) { }
        })
        this.player.setPlaybackEventListener(object : YouTubePlayer.PlaybackEventListener {
            override fun onSeekTo(p0: Int) { }

            override fun onBuffering(p0: Boolean) { }

            override fun onPlaying() { setMusicGif(music_sound) }

            override fun onStopped() { music_sound.setImageDrawable(null) }

            override fun onPaused() { music_sound.setImageDrawable(null) }
        })
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider, result: YouTubeInitializationResult) { }

    private var videoIsFullScreen: Boolean? = null

    override fun onFullscreen(fullscreen: Boolean) {
        videoIsFullScreen = fullscreen

        requestedOrientation = if (fullscreen) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    override fun onBackPressed() {
        if (videoIsFullScreen == null) {
            super.onBackPressed()
            return
        }

        if (::player.isInitialized && videoIsFullScreen!!) {
            player.setFullscreen(false)
        } else {
            super.onBackPressed()
        }
    }

    override fun finish() {
        stop()
        super.finish()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    companion object {
        const val EXTRA_VIDEO = "video"
        const val EXTRA_VIDEO_LIST = "videoList"
    }
}