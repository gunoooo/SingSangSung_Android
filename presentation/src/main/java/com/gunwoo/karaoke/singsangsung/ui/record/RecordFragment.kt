package com.gunwoo.karaoke.singsangsung.ui.record

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentRecordBinding
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import kotlinx.android.synthetic.main.fragment_record.*
import javax.inject.Inject


class RecordFragment : BaseFragment<FragmentRecordBinding, RecordViewModel>() {

    @Inject
    lateinit var viewModelFactory: RecordViewModelFactory

    override val viewModel: RecordViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            recordListAdapter.onClickItemEvent.observe(this@RecordFragment, Observer {
                viewType.value = RecordViewModel.ViewType.PLAY
                startAudio(it)
                seekbar.max = player!!.duration / 1000
            })

            viewType.observe(this@RecordFragment, Observer {
                when(it.name) {
                    RecordViewModel.ViewType.PLAY.name -> {
                        player_layout.visibility = View.VISIBLE

                        pause_btn.setImageDrawable(getDrawable(this@RecordFragment.context!!, R.drawable.ic_pause))
                    }
                    RecordViewModel.ViewType.PAUSE.name -> {
                        player_layout.visibility = View.VISIBLE

                        pause_btn.setImageDrawable(getDrawable(this@RecordFragment.context!!, R.drawable.ic_play))
                    }
                    RecordViewModel.ViewType.STOP.name -> {
                        player_layout.visibility = View.GONE
                    }
                    RecordViewModel.ViewType.REPLAY.name -> {
                        player_layout.visibility = View.VISIBLE

                        pause_btn.setImageDrawable(getDrawable(this@RecordFragment.context!!, R.drawable.ic_replay))
                    }
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSeekbar()
    }

    private fun initSeekbar() {
        val mHandler = Handler()

        this.activity?.runOnUiThread(object : Runnable {
            override fun run() {
                if (mViewModel.player != null) {
                    val mCurrentPosition: Int = mViewModel.player!!.currentPosition / 1000
                    seekbar.progress = mCurrentPosition
                }
                mHandler.postDelayed(this, 1000)
            }
        })
    }

    override fun onPause() {
        super.onPause()

        with(mViewModel) {
            if (viewType.value!! != RecordViewModel.ViewType.STOP) {
                viewType.value = RecordViewModel.ViewType.STOP
                stopAudio()
            }
        }
    }
}