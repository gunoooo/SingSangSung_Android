package com.gunwoo.karaoke.singsangsung.ui.player

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseActivity
import com.gunwoo.karaoke.singsangsung.databinding.ActivityPlayerBinding
import com.gunwoo.karaoke.singsangsung.widget.SingSangSungVideoView
import com.gunwoo.karaoke.singsangsung.widget.extension.*
import com.gunwoo.karaoke.singsangsung.widget.viewpager.PlayerViewPagerAdapter
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

                        record_sound.putImage(R.drawable.music)
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

    private fun initVideo() {
        window.decorView.setOnSystemUiVisibilityChangeListener {
            if (video_view.isFullscreen) {
                val handler = Handler()
                handler.postDelayed({
                    if (video_view.isFullscreen) {
                        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                    }
                }, 5000) // 전체화면일때 Status Bar가 포커싱 되면 3초후 다시 Status Bar를 올려준다
            }
        }

        video_view.apply {
            setBackListener(object : SingSangSungVideoView.BackListener {
                override fun finish() {
                    this@PlayerActivity.finish()
                }
            })

            setMoreListener(object : SingSangSungVideoView.MoreListener {
                override fun onClickMore() {
                    PlayerBottomSheetDialog(mViewModel.getVideo()).show(supportFragmentManager)
                }
            })

            setPlayPauseListener(object : SingSangSungVideoView.PlayPauseListener {
                override fun onPlay() {
                    music_sound.putImage(R.drawable.music)
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

            setPitchSpeedListener(object : SingSangSungVideoView.PitchSpeedListener {
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
    }

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
            video_view.changeScreenState()
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