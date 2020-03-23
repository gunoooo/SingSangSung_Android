package com.gunwoo.karaoke.singsangsung.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.MediaController
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseActivity
import com.gunwoo.karaoke.singsangsung.databinding.ActivityOfflinePlayerBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.OfflinePlayerViewModel
import com.gunwoo.karaoke.singsangsung.viewmodel.PlayerViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.OfflinePlayerViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.SingSangSungVideoView
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.putImage
import com.gunwoo.karaoke.singsangsung.widget.extension.setImageTint
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import kotlinx.android.synthetic.main.activity_offline_player.*
import javax.inject.Inject


class OfflinePlayerActivity : BaseActivity<ActivityOfflinePlayerBinding, OfflinePlayerViewModel>() {

    @Inject
    lateinit var viewModelFactory: OfflinePlayerViewModelFactory

    override val viewModel: OfflinePlayerViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            viewType.observe(this@OfflinePlayerActivity, Observer {
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

            onStoppedRecording.observe(this@OfflinePlayerActivity, Observer { shortToast(R.string.message_stopped_recoding) })

            with(offlineMusicListAdapter) {
                onClickItem.observe(this@OfflinePlayerActivity, Observer {
                    startActivity(
                        Intent(this@OfflinePlayerActivity.applicationContext, OfflinePlayerActivity::class.java)
                            .putExtra(EXTRA_VIDEO, it)
                            .putExtra(EXTRA_VIDEO_LIST, downloadList))

                    finish()
                })

                onDeleteDownloadEvent.observe(this@OfflinePlayerActivity, Observer {
                    mViewModel.deleteDownload(it)
                })
            }

            onSuccessDeleteDownloadEvent.observe(this@OfflinePlayerActivity, Observer {
                shortToast(R.string.message_show_complete)
            })

            onErrorEvent.observe(this@OfflinePlayerActivity, Observer {
                shortToast(it.message)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!checkPermission()) mViewModel.viewType.value = OfflinePlayerViewModel.ViewType.PERMISSION
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
        mViewModel.video = video as Download

        val downloadList = intent.getSerializableExtra(EXTRA_VIDEO_LIST) ?: return
        mViewModel.setDownloadList(downloadList as ArrayList<Download>)

        initVideo()
    }

    private fun initVideo() {
        video_view.setVideoURI(Uri.fromFile(mViewModel.video.video))

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
            if (viewType.value!! == OfflinePlayerViewModel.ViewType.PERMISSION) return

            if (viewType.value!! != OfflinePlayerViewModel.ViewType.STOP) {
                viewType.value = OfflinePlayerViewModel.ViewType.STOP
                stopRecord()
            }
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