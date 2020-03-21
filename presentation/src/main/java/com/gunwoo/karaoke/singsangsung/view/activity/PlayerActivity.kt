package com.gunwoo.karaoke.singsangsung.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseActivity
import com.gunwoo.karaoke.singsangsung.databinding.ActivityPlayerBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.PlayerViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.*
import kotlinx.android.synthetic.main.activity_player.*
import kr.co.prnd.YouTubePlayerView


class PlayerActivity : BaseActivity<ActivityPlayerBinding, PlayerViewModel>(),
    YouTubePlayerView.OnInitializedListener,
    YouTubePlayer.OnFullscreenListener {

    override val viewModel: PlayerViewModel
        get() = getViewModel()

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
                        .putExtra(EXTRA_VIDEO_ID, it.videoId)
                        .putExtra(EXTRA_VIDEO_LIST, youtubeDataList))

                finish()
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
        val videoId = intent.getStringExtra(EXTRA_VIDEO_ID)
        if (videoId == null) {
            finish()
            return
        }
        mViewModel.videoId = videoId
        youtube_player_view.play(mViewModel.videoId, this)

        val videoList = intent.getSerializableExtra(EXTRA_VIDEO_LIST) ?: return
        mViewModel.setMusicList(videoList as ArrayList<YoutubeData>)
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
        this.player.loadVideo(mViewModel.videoId, 0)
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
        const val EXTRA_VIDEO_ID = "videoId"
        const val EXTRA_VIDEO_LIST = "videoList"
    }
}