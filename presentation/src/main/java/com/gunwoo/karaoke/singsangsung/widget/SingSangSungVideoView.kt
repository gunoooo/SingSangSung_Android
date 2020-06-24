package com.gunwoo.karaoke.singsangsung.widget

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.gunwoo.karaoke.singsangsung.R
import kotlinx.android.synthetic.main.item_singsangsung_video.view.*
import java.io.IOException
import java.util.*


class SingSangSungVideoView : FrameLayout,
    SurfaceHolder.Callback,
    MediaPlayer.OnPreparedListener,
    MediaPlayer.OnCompletionListener,
    MediaPlayer.OnErrorListener {

    private var mContext: Context? = null
    private var attrs: AttributeSet? = null
    private var styleAttr: Int? = null
    private var view = View.inflate(context, R.layout.item_singsangsung_video, null)

    private var fullscreenListener: FullscreenListener? = null
    private var moreListener: MoreListener? = null
    private var backListener: BackListener? = null
    private var playPauseListener: PlayPauseListener? = null
    private var pitchSpeedListener: PitchSpeedListener? = null
    private var jumpListener: JumpListener? = null

    private var player: MediaPlayer? = null
    private var videoPath: String? = null
    private var holder: SurfaceHolder? = null

    var isFullscreen: Boolean = false
    private var isScreenFocused: Boolean = false
    private var viewType: ViewType = ViewType.PLAY

    private var frameVisibilityHandler: Handler = Handler()
    private var frameInvisibleRunnable: Runnable = Runnable { setFrameVisibility(View.INVISIBLE) }

    interface FullscreenListener {
        fun fullscreenOn()
        fun fullscreenOff()
    }

    interface MoreListener {
        fun onClickMore()
    }

    interface BackListener {
        fun finish()
    }

    interface PlayPauseListener {
        fun play()
        fun pause()
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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        setMeasuredDimension(width, width / 16 * 9)
    }

    fun setFullscreenListener(listener: FullscreenListener) {
        fullscreenListener = listener
    }

    fun fullscreenOff() {
        if (isFullscreen) {
            fullscreenListener?.fullscreenOff()
            fullscreen_btn.setImageDrawable(context.getDrawable(R.drawable.ic_fullscreen))
            back_btn.setImageDrawable(context.getDrawable(R.drawable.ic_left))
            isFullscreen = !isFullscreen
        }
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

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) {
        this.mContext = context
        this.attrs = attrs
        this.styleAttr = defStyleAttr

        addView(view)

        surface_view.holder.addCallback(this)
        surface_view.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
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
        more_btn.visibility = visibility
        video_play_btn.visibility = visibility
        fullscreen_btn.visibility = visibility
        video_seekbar.visibility = visibility
        cover.visibility = visibility
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        player?.setDisplay(holder)
    }

    override fun surfaceCreated(holder: SurfaceHolder) { this.holder = holder }

    override fun surfaceDestroyed(holder: SurfaceHolder) { }

    override fun onCompletion(mp: MediaPlayer?) {
        video_play_btn.setImageDrawable(context.getDrawable(R.drawable.ic_replay))
        viewType = ViewType.REPLAY
        playPauseListener?.pause()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        Toast.makeText(context, what, Toast.LENGTH_LONG).show()
        Log.e("MediaPlayer", what.toString())
        return true
    }

    override fun onPrepared(mp: MediaPlayer) {
        mp.start()

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
                fullscreen_btn.setImageDrawable(context.getDrawable(R.drawable.ic_fullscreen))
                back_btn.setImageDrawable(context.getDrawable(R.drawable.ic_left))
                fullscreenListener?.fullscreenOff()
            } else {
                backListener?.finish()
            }
            isFullscreen = !isFullscreen
        }

        more_btn.setOnClickListener {
            moreListener?.onClickMore()
        }

        fullscreen_btn.setOnClickListener {
            if (isFullscreen) {
                fullscreen_btn.setImageDrawable(context.getDrawable(R.drawable.ic_fullscreen))
                back_btn.setImageDrawable(context.getDrawable(R.drawable.ic_left))
                fullscreenListener?.fullscreenOff()
            } else {
                fullscreen_btn.setImageDrawable(context.getDrawable(R.drawable.ic_fullscreen_exit))
                back_btn.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_down))
                fullscreenListener?.fullscreenOn()
            }
            isFullscreen = !isFullscreen
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
            override fun run() {
                try {
                    video_seekbar.progress = player?.currentPosition ?: return
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                }
            }
        }, 0, 1000)

        video_seekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        player?.seekTo(progress.toLong(), MediaPlayer.SEEK_CLOSEST)
                    }
                    else {
                        player?.seekTo(progress)
                    }
                    video_seekbar.progress = progress
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
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (holder == null) {
            Handler().postDelayed({ startVideo(videoPath) }, 1000)
        }
        else {
            player?.setDisplay(holder)

            player?.setOnPreparedListener(this)
            player?.setOnCompletionListener(this)

            player?.playbackParams = player?.playbackParams?.setPitch(pitch)!!
            player?.playbackParams = player?.playbackParams?.setSpeed(speed)!!

            playPauseListener?.play()
        }
    }

    private fun reStartVideo() {
        player?.start()
        playPauseListener?.play()
    }

    private fun pauseVideo() {
        player?.pause()
        playPauseListener?.pause()
    }

    private fun stopVideo() {
        player?.stop()
        player?.release()
        player = null
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
        jumpSpot = player?.currentPosition ?: return
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
    }
}