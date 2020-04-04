package com.gunwoo.karaoke.singsangsung.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentPlayerControllerBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.PlayerControllerViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import kotlinx.android.synthetic.main.fragment_player_controller.*

class PlayerControllerFragment : BaseFragment<FragmentPlayerControllerBinding, PlayerControllerViewModel>() {

    override val viewModel: PlayerControllerViewModel
        get() = getViewModel()

    val onPitchUpEvent = SingleLiveEvent<Unit>()
    val onPitchDownEvent = SingleLiveEvent<Unit>()
    val onTempoUpEvent = SingleLiveEvent<Unit>()
    val onTempoDownEvent = SingleLiveEvent<Unit>()

    override fun observerViewModel() {
        with(mViewModel) {
            onPitchUpEvent.observe(this@PlayerControllerFragment, Observer {
                this@PlayerControllerFragment.onPitchUpEvent.call()
            })

            onPitchDownEvent.observe(this@PlayerControllerFragment, Observer {
                this@PlayerControllerFragment.onPitchDownEvent.call()
            })

            onTempoUpEvent.observe(this@PlayerControllerFragment, Observer {
                this@PlayerControllerFragment.onTempoUpEvent.call()
            })

            onTempoDownEvent.observe(this@PlayerControllerFragment, Observer {
                this@PlayerControllerFragment.onTempoDownEvent.call()
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAds()
    }

    private fun initAds() {
        MobileAds.initialize(this.activity, getString(R.string.admob_app_id))
        val adRequest: AdRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }
}