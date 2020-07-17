package com.gunwoo.karaoke.singsangsung.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getColor
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.widget.extension.getParentActivity
import com.gunwoo.karaoke.singsangsung.widget.extension.millisecondsToMinutes
import kotlinx.android.synthetic.main.item_singsangsung_video.view.*
import kr.co.prnd.YouTubePlayerView
import java.io.IOException
import java.util.*


@SuppressLint("HandlerLeak")
class SingSangSungVideoView : FrameLayout,
    SurfaceHolder.Callback,
    MediaPlayer.OnPreparedListener,
    MediaPlayer.OnCompletionListener,
    MediaPlayer.OnErrorListener {

    private var mContext: Context? = null
    private var attrs: AttributeSet? = null
    private var styleAttr: Int? = null
    private var view = View.inflate(context, R.layout.item_singsangsung_video, null)

    private var moreListener: MoreListener? = null
    private var backListener: BackListener? = null
    private var playPauseListener: PlayPauseListener? = null
    private var pitchSpeedListener: PitchSpeedListener? = null
    private var jumpListener: JumpListener? = null

    private var youTubePlayer: YouTubePlayer? = null
    private var player: MediaPlayer? = null
    private var videoPath: String? = null
    private var holder: SurfaceHolder? = null

    var isFullscreen: Boolean = false
    private var isScreenFocused: Boolean = false
    private var viewType: ViewType = ViewType.PLAY

    private var frameVisibilityHandler: Handler = Handler()
    private var frameInvisibleRunnable: Runnable = Runnable { setFrameVisibility(View.INVISIBLE) }
    private val timeTextHandler: Handler = object : Handler() {
        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message?) {
            if (player != null)
                time_text.text = "${player!!.currentPosition.millisecondsToMinutes()} / ${player!!.duration.millisecondsToMinutes()}"
        }
    }

    interface MoreListener {
        fun onClickMore()
    }

    interface BackListener {
        fun finish()
    }

    interface PlayPauseListener {
        fun onPlay()
        fun onPause()
        fun onError()
        fun onYouTubeError()
    }

    interface PitchSpeedListener {
        fun onChangedPitch(pitchCount: Int)
        fun onChangedTempo(speedCount: Int)
    }
    
    interface JumpListener {
        fun onSetJumpSpot(jumpSpot: Int)
    }

    private enum class ViewType {
        PLAY,
        PAUSE,
        REPLAY
    }

    fun setMoreListener(listener: MoreListener) {
        moreListener = listener
    }

    fun setBackListener(listener: BackListener) {
        backListener = listener
    }

    fun setPlayPauseListener(listener: PlayPauseListener) {
        playPauseListener = listener
    }

    fun setPitchSpeedListener(listener: PitchSpeedListener) {
        pitchSpeedListener = listener
    }

    fun setJumpListener(listener: JumpListener) {
        jumpListener = listener
    }

    constructor(context: Context) : super(context) {
        init(context, null, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, null)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopVideo()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(width, width * 9 / 16)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) {
        this.mContext = context
        this.attrs = attrs
        this.styleAttr = defStyleAttr

        addView(view)

        youtube_player_view.visibility = View.GONE
        surface_view.holder.addCallback(this)
        surface_view.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
        progress_bar.visibility = View.VISIBLE
    }

    private fun setFrameVisibility(visibility: Int) {
        val transition: Transition = Fade()
        transition.duration = 300
        transition.addTarget(R.id.back_btn)
        transition.addTarget(R.id.more_btn)
        transition.addTarget(R.id.video_play_btn)
        transition.addTarget(R.id.fullscreen_btn)
        transition.addTarget(R.id.seekbar)
        transition.addTarget(R.id.cover)

        TransitionManager.beginDelayedTransition(rootView as ViewGroup, transition)

        back_btn.visibility = visibility
        more_btn.visibility = if (isFullscreen) View.INVISIBLE else visibility
        video_play_btn.visibility = visibility
        fullscreen_btn.visibility = visibility
        video_seekbar.visibility = visibility
        cover.visibility = visibility
        time_text.visibility = visibility
    }

    @SuppressLint("SourceLockedOrientationActivity")
    fun changeScreenState() {
        if (isFullscreen) {
            fullscreen_btn.setImageDrawable(context.getDrawable(R.drawable.ic_fullscreen))
            back_btn.setImageDrawable(context.getDrawable(R.drawable.ic_left))
            more_btn.visibility = View.VISIBLE
            getParentActivity()?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            getParentActivity()?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            getParentActivity()?.window?.navigationBarColor = getColor(context, R.color.colorPrimary)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                0
            )
        } else {
            fullscreen_btn.setImageDrawable(context.getDrawable(R.drawable.ic_fullscreen_exit))
            back_btn.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_down))
            more_btn.visibility = View.INVISIBLE
            getParentActivity()?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            getParentActivity()?.window?.decorView?.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE
                    )
            getParentActivity()?.window?.navigationBarColor = getColor(context, R.color.colorFilter)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        }
        isFullscreen = !isFullscreen
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        player?.setDisplay(holder)
    }

    override fun surfaceCreated(holder: SurfaceHolder) { this.holder = holder }

    override fun surfaceDestroyed(holder: SurfaceHolder) { }

    override fun onCompletion(mp: MediaPlayer?) {
        video_play_btn.setImageDrawable(context.getDrawable(R.drawable.ic_replay))
        viewType = ViewType.REPLAY
        playPauseListener?.onPause()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        Log.e("MediaPlayer", what.toString())
        playPauseListener?.onError()
        return true
    }

    override fun onPrepared(mp: MediaPlayer) {
        mp.start()
        progress_bar.visibility = View.GONE
        playPauseListener?.onPlay()

        surface_view.setOnClickListener {
            if (isScreenFocused) {
                setFrameVisibility(View.INVISIBLE)
            } else {
                setFrameVisibility(View.VISIBLE)
                frameVisibilityHandler.removeCallbacks(frameInvisibleRunnable)
                frameVisibilityHandler.postDelayed(frameInvisibleRunnable,5000)
            }
            isScreenFocused = !isScreenFocused
        }

        back_btn.setOnClickListener {
            if (isFullscreen) {
                changeScreenState()
            } else {
                backListener?.finish()
            }
        }

        more_btn.setOnClickListener {
            moreListener?.onClickMore()
        }

        fullscreen_btn.setOnClickListener {
            changeScreenState()
        }

        video_play_btn.setOnClickListener {
            when(viewType.name) {
                ViewType.PLAY.name -> {
                    pauseVideo()
                    video_play_btn.setImageDrawable(context.getDrawable(R.drawable.ic_play))
                    viewType = ViewType.PAUSE
                }
                ViewType.PAUSE.name -> {
                    reStartVideo()
                    video_play_btn.setImageDrawable(context.getDrawable(R.drawable.ic_pause))
                    viewType = ViewType.PLAY
                }
                ViewType.REPLAY.name -> {
                    stopVideo()
                    startVideo(videoPath!!)
                    video_play_btn.setImageDrawable(context.getDrawable(R.drawable.ic_pause))
                    viewType = ViewType.PLAY
                }
            }
        }

        video_seekbar.max = player!!.duration

        Timer().scheduleAtFixedRate(object : TimerTask() {
            @SuppressLint("SetTextI18n")
            override fun run() {
                try {
                    video_seekbar.progress = player?.currentPosition ?: return
                    timeTextHandler.obtainMessage(1).sendToTarget()
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                }
            }
        }, 0, 1000)

        video_seekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    setFrameVisibility(View.VISIBLE)
                    frameVisibilityHandler.removeCallbacks(frameInvisibleRunnable)
                    frameVisibilityHandler.postDelayed(frameInvisibleRunnable,5000)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        player?.seekTo(progress.toLong(), MediaPlayer.SEEK_CLOSEST)
                    }
                    else {
                        player?.seekTo(progress)
                    }
                    video_seekbar.progress = progress
                    time_text.text = "${player!!.currentPosition.millisecondsToMinutes()} / ${player!!.duration.millisecondsToMinutes()}"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) { }

            override fun onStopTrackingTouch(seekBar: SeekBar) { }
        })
    }

    fun startVideo(videoPath: String) {
        this.videoPath = videoPath
        this.player = MediaPlayer()

        try {
            player?.setDataSource(videoPath)
            player?.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            player?.prepare()

            player?.setDisplay(holder)

            player?.setOnPreparedListener(this)
            player?.setOnCompletionListener(this)

            player?.playbackParams = player?.playbackParams?.setPitch(pitch)!!
            player?.playbackParams = player?.playbackParams?.setSpeed(speed)!!

            countDown()
        } catch (e: IllegalArgumentException) {
            playPauseListener?.onError()
        } catch (e: IllegalStateException) {
            playPauseListener?.onError()
        } catch (e: IOException) {
            playPauseListener?.onError()
        }
    }

    fun openYouTubePlayerView(videoId: String) {
        stopVideo()
        surface_view.visibility = View.GONE
        back_btn.visibility = View.GONE
        more_btn.visibility = View.GONE
        video_play_btn.visibility = View.GONE
        fullscreen_btn.visibility = View.GONE
        video_seekbar.visibility = View.GONE
        cover.visibility = View.GONE
        youtube_player_view.visibility = View.VISIBLE

        youtube_player_view.play(videoId, object : YouTubePlayerView.OnInitializedListener {
            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider,
                result: YouTubeInitializationResult
            ) {
                playPauseListener?.onYouTubeError()
            }

            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                player: YouTubePlayer,
                wasRestored: Boolean
            ) {
                youTubePlayer = player
                youTubePlayer?.setPlaybackEventListener(object : YouTubePlayer.PlaybackEventListener {
                    override fun onSeekTo(p0: Int) { }

                    override fun onBuffering(p0: Boolean) { }

                    override fun onPlaying() {
                        playPauseListener?.onPlay()
                    }

                    override fun onStopped() {
                        playPauseListener?.onPause()
                    }

                    override fun onPaused() {
                        playPauseListener?.onPause()
                    }
                })
                youTubePlayer?.loadVideo(videoId)
            }
        })
    }

    private fun countDown() { // timeout 체크
        object : CountDownTimer(10000, 1000) { // 10초동안 로딩이 지속될 경우
            override fun onFinish() {
                if (progress_bar.visibility == View.VISIBLE) {
                    playPauseListener?.onError()
                }
            }

            override fun onTick(millisUntilFinished: Long) { }
        }.start()
    }

    private fun reStartVideo() {
        player?.start()
        playPauseListener?.onPlay()
    }

    private fun pauseVideo() {
        player?.pause()
        playPauseListener?.onPause()
    }

    private fun stopVideo() {
        player?.stop()
        player?.release()
        player = null
        progress_bar.visibility = View.GONE
    }

    private var pitch = 1.0f
    private var speed = 1.0f

    private var pitchCount = 0
    private var speedCount = 0

    fun pitchUp() {
        if (player == null) return

        if (player!!.isPlaying && pitchCount < 10) {
            pitch += 0.0125f
            pitchCount++
            player?.playbackParams = player?.playbackParams?.setPitch(pitch)!!
            pitchSpeedListener?.onChangedPitch(pitchCount)
        }
    }

    fun pitchDown() {
        if (player == null) return

        if (player!!.isPlaying && pitchCount > -10) {
            pitch -= 0.0125f
            pitchCount--
            player?.playbackParams = player?.playbackParams?.setPitch(pitch)!!
            pitchSpeedListener?.onChangedPitch(pitchCount)
        }
    }

    fun tempoUp() {
        if (player == null) return

        if (player!!.isPlaying && speedCount < 10) {
            speed += 0.025f
            speedCount++
            player?.playbackParams = player?.playbackParams?.setSpeed(speed)!!
            pitchSpeedListener?.onChangedTempo(speedCount)
        }
    }

    fun tempoDown() {
        if (player == null) return

        if (player!!.isPlaying && speedCount > -10) {
            speed -= 0.025f
            speedCount--
            player?.playbackParams = player?.playbackParams?.setSpeed(speed)!!
            pitchSpeedListener?.onChangedTempo(speedCount)
        }
    }

    private var jumpSpot: Int = 0

    fun setJumpSpot(jumpSpot: Int) {
        this.jumpSpot = jumpSpot
        jumpListener?.onSetJumpSpot(jumpSpot)
    }

    fun setJumpSpot() {
        jumpSpot = player?.currentPosition ?: youTubePlayer?.currentTimeMillis ?: return
        jumpListener?.onSetJumpSpot(jumpSpot)
    }

    fun jump() {
        video_seekbar.progress = jumpSpot
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            player?.seekTo(jumpSpot.toLong(), MediaPlayer.SEEK_CLOSEST)
        }
        else {
            player?.seekTo(jumpSpot)
        }
        youTubePlayer?.seekToMillis(jumpSpot)
    }
}