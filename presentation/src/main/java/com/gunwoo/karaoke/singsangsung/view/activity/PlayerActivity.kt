package com.gunwoo.karaoke.singsangsung.view.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseActivity
import com.gunwoo.karaoke.singsangsung.databinding.ActivityPlayerBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.PlayerViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.putImage
import com.gunwoo.karaoke.singsangsung.widget.extension.setImageTint
import kotlinx.android.synthetic.main.activity_player.*
import kr.co.prnd.YouTubePlayerView

class PlayerActivity : BaseActivity<ActivityPlayerBinding, PlayerViewModel>(), YouTubePlayerView.OnInitializedListener {

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

                        music.setImageDrawable(null)
                    }
                    PlayerViewModel.ViewType.RECORD.name -> {
                        play_btn.setImageDrawable(getDrawable(R.drawable.ic_pause))

                        record_btn.setImageTint(R.color.colorRedTransparent)
                        stop_btn.setImageTint(R.color.colorPrimary)
                        play_btn.setImageTint(R.color.colorPrimary)

                        setMusicGif()
                    }
                    PlayerViewModel.ViewType.PAUSE.name -> {
                        play_btn.setImageDrawable(getDrawable(R.drawable.ic_play))

                        record_btn.setImageTint(R.color.colorRedTransparent)
                        stop_btn.setImageTint(R.color.colorPrimary)
                        play_btn.setImageTint(R.color.colorPrimary)

                        music.setImageDrawable(null)
                    }
                }
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

    private fun setMusicGif() { music.putImage(R.drawable.music) }

    private fun initIntent() {
        val videoId = intent?.getStringExtra(EXTRA_VIDEO_ID)
        if (videoId == null) {
            finish()
            return
        }
        mViewModel.videoId = videoId
        youtube_player_view.play(mViewModel.videoId, this)
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
        this.player = player
        this.player.loadVideo(mViewModel.videoId, 0)
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider, result: YouTubeInitializationResult) { }

    companion object {
        const val EXTRA_VIDEO_ID = "videoId"
    }
}