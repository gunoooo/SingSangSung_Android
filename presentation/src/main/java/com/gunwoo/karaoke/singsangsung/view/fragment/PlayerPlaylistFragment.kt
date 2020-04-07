package com.gunwoo.karaoke.singsangsung.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentPlayerPlaylistBinding
import com.gunwoo.karaoke.singsangsung.view.activity.PlayerActivity
import com.gunwoo.karaoke.singsangsung.viewmodel.PlayerPlaylistViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel

class PlayerPlaylistFragment : BaseFragment<FragmentPlayerPlaylistBinding, PlayerPlaylistViewModel>() {

    override val viewModel: PlayerPlaylistViewModel
        get() = getViewModel()

    val onAddFavoritesEvent = SingleLiveEvent<YoutubeData>()
    val onDeleteFavoritesEvent = SingleLiveEvent<YoutubeData>()
    val onHideEvent = SingleLiveEvent<YoutubeData>()
    val onOpenYoutubeEvent = SingleLiveEvent<YoutubeData>()
    val onShareEvent = SingleLiveEvent<YoutubeData>()

    override fun observerViewModel() {
        with(mViewModel) {
            with(musicListAdapter) {
                onClickItemEvent.observe(this@PlayerPlaylistFragment, Observer {
                    startActivity(
                        Intent(this@PlayerPlaylistFragment.context!!.applicationContext, PlayerActivity::class.java)
                            .putExtra(PlayerActivity.EXTRA_VIDEO, it)
                            .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, youtubeDataList))

                    activity!!.finish()
                })

                onAddFavoritesEvent.observe(this@PlayerPlaylistFragment, Observer {
                    mViewModel.addFavorites(it)
                })

                onDeleteFavoritesEvent.observe(this@PlayerPlaylistFragment, Observer {
                    mViewModel.deleteFavorites(it)
                })

                onHideEvent.observe(this@PlayerPlaylistFragment, Observer {
                    mViewModel.hide(it)
                })

                onOpenYoutubeEvent.observe(this@PlayerPlaylistFragment, Observer {
                    this@PlayerPlaylistFragment.onOpenYoutubeEvent.value = it
                })

                onShareEvent.observe(this@PlayerPlaylistFragment, Observer {
                    this@PlayerPlaylistFragment.onShareEvent.value = it
                })
            }

            onAddFavoritesEvent.observe(this@PlayerPlaylistFragment, Observer {
                this@PlayerPlaylistFragment.onAddFavoritesEvent.value = it
            })

            onDeleteFavoritesEvent.observe(this@PlayerPlaylistFragment, Observer {
                this@PlayerPlaylistFragment.onDeleteFavoritesEvent.value = it
            })

            onHideEvent.observe(this@PlayerPlaylistFragment, Observer {
                this@PlayerPlaylistFragment.onHideEvent.value = it
            })
        }
    }

    lateinit var youtubeDataList: List<YoutubeData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.setYoutubeDataList(youtubeDataList)
    }
}