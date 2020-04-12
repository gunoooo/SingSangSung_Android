package com.gunwoo.karaoke.singsangsung.ui.player

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentPlayerControllerBinding
import com.gunwoo.karaoke.singsangsung.widget.SingSangSungVideoView
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.millisecondsToMinutes
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.fragment_player_controller.*

class PlayerControllerFragment : BaseFragment<FragmentPlayerControllerBinding, PlayerControllerViewModel>() {

    override val viewModel: PlayerControllerViewModel
        get() = getViewModel()

    override fun observerViewModel() {
        with(mViewModel) {
            onPitchUpEvent.observe(this@PlayerControllerFragment, Observer {
                activity!!.video_view.pitchUp()
            })

            onPitchDownEvent.observe(this@PlayerControllerFragment, Observer {
                activity!!.video_view.pitchDown()
            })

            onTempoUpEvent.observe(this@PlayerControllerFragment, Observer {
                activity!!.video_view.tempoUp()
            })

            onTempoDownEvent.observe(this@PlayerControllerFragment, Observer {
                activity!!.video_view.tempoDown()
            })

            onClickSetJumpSpotEvent.observe(this@PlayerControllerFragment, Observer {
                activity!!.video_view.setJumpSpot()
            })

            onClickJumpEvent.observe(this@PlayerControllerFragment, Observer {
                activity!!.video_view.jump()
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAds()
        initVideoViewJumpListener()
    }

    private fun initAds() {
        MobileAds.initialize(this.activity, getString(R.string.admob_app_id))
        val adRequest: AdRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    private fun initVideoViewJumpListener() {
        activity!!.video_view.setJumpListener(object : SingSangSungVideoView.JumpListener {
            override fun onSetJumpSpot(jumpSpot: Int) {
                mViewModel.jumpSpot.value = jumpSpot.millisecondsToMinutes()
            }
        })
    }
}