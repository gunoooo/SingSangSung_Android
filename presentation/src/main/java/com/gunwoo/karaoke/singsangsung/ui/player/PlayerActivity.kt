package com.gunwoo.karaoke.singsangsung.ui.player

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.SparseArray
import android.util.TypedValue
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.material.tabs.TabLayoutMediator
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseActivity
import com.gunwoo.karaoke.singsangsung.databinding.ActivityPlayerBinding
import com.gunwoo.karaoke.singsangsung.widget.SingSangSungVideoView
import com.gunwoo.karaoke.singsangsung.widget.extension.*
import com.gunwoo.karaoke.singsangsung.widget.viewpager.PlayerViewPagerAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.fragment_player_controller.*
import javax.inject.Inject


class PlayerActivity : BaseActivity<ActivityPlayerBinding, PlayerViewModel>() {

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

            onStartVideoEvent.observe(this@PlayerActivity, Observer {
                video_view.startVideo(streamingUrl)
            })

            onStoppedRecording.observe(this@PlayerActivity, Observer { shortToast(R.string.message_stopped_recoding) })

            onSuccessAddFavoritesEvent.observe(this@PlayerActivity, Observer {
                shortToast(R.string.message_add_favorites_complete)
            })

            onSuccessDeleteFavoritesEvent.observe(this@PlayerActivity, Observer {
                shortToast(R.string.message_delete_favorites_complete)
            })

            onSuccessHideEvent.observe(this@PlayerActivity, Observer {
                shortToast(R.string.message_hide_complete)
            })

            onSuccessHideEvent.observe(this@PlayerActivity, Observer {
                shortToast(R.string.message_hide_complete)
            })

            onErrorEvent.observe(this@PlayerActivity, Observer {
                shortToast(it.message)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!checkPermission()) mViewModel.viewType.value = PlayerViewModel.ViewType.PERMISSION
        initVideo()
        initIntent()
        initViewPager()
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
        mViewModel.setVideo(video as YoutubeData)
        mViewModel.insertRecent()

        val videoList = intent.getSerializableExtra(EXTRA_VIDEO_LIST)
        if (videoList == null) {
            finish()
            return
        }
        mViewModel.videoList = videoList as ArrayList<YoutubeData>
    }

    private val tabTitles = listOf("재생목록", "설정")

    private fun initViewPager() {
        val playerViewPagerAdapter = PlayerViewPagerAdapter(this)
        playerViewPagerAdapter.playerPlaylistFragment.youtubeDataList = mViewModel.videoList
        view_pager.adapter = playerViewPagerAdapter
        view_pager.offscreenPageLimit = 2
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = tabTitles[position]
            view_pager.setCurrentItem(tab.position, true)
        }.attach()
    }

    private fun initVideo() {
        video_view.setBackListener(object : SingSangSungVideoView.BackListener {
            override fun finish() {
                this@PlayerActivity.finish()
            }
        })

        video_view.setFullscreenListener(object : SingSangSungVideoView.FullscreenListener {
            @SuppressLint("SourceLockedOrientationActivity")
            override fun fullscreenOn() {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
                window.navigationBarColor = Color.BLACK

                video_view.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
            }

            @SuppressLint("SourceLockedOrientationActivity")
            override fun fullscreenOff() {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                window.navigationBarColor = getColor(R.color.colorPrimary)

                val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250f, resources.displayMetrics).toInt()
                video_view.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, height)
            }
        })

        video_view.setMoreListener(object : SingSangSungVideoView.MoreListener {
            override fun onClickMore() {
                PlayerBottomSheetDialog(mViewModel.getVideo()).show(supportFragmentManager)
            }
        })

        video_view.setPlayPauseListener(object : SingSangSungVideoView.PlayPauseListener {
            override fun onPlay() {
                setMusicGif(music_sound)
            }

            override fun onPause() {
                music_sound.setImageDrawable(null)
            }

            override fun onError() {
                longToast(R.string.error_video_loading)
                video_view.openYouTubePlayerView(mViewModel.getVideo().videoId!!)
            }

            override fun onYouTubeError() {
                finish()
            }
        })

        video_view.setPitchSpeedListener(object : SingSangSungVideoView.PitchSpeedListener {
            @SuppressLint("SetTextI18n")
            override fun onChangedPitch(pitchCount: Int) {
                text_pitch.text = "음정 $pitchCount"
            }

            @SuppressLint("SetTextI18n")
            override fun onChangedTempo(speedCount: Int) {
                text_tempo.text = "템포 $speedCount"
            }
        })
    }

    private fun stopRecord() {
        with(mViewModel) {
            if (viewType.value!! == PlayerViewModel.ViewType.PERMISSION) return

            if (viewType.value!! != PlayerViewModel.ViewType.STOP) {
                viewType.value = PlayerViewModel.ViewType.STOP
                stopRecord()
            }
        }
    }

    override fun onBackPressed() {
        if (video_view.isFullscreen)
            video_view.fullscreenOff()
        else
            super.onBackPressed()

    }

    override fun finish() {
        stopRecord()
        super.finish()
    }

    companion object {
        const val EXTRA_VIDEO = "video"
        const val EXTRA_VIDEO_LIST = "videoList"
    }
}