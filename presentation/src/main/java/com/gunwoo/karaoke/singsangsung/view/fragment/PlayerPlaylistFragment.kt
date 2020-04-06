package com.gunwoo.karaoke.singsangsung.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentPlayerPlaylistBinding
import com.gunwoo.karaoke.singsangsung.view.activity.PlayerActivity
import com.gunwoo.karaoke.singsangsung.viewmodel.PlayerPlaylistViewModel
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast

class PlayerPlaylistFragment : BaseFragment<FragmentPlayerPlaylistBinding, PlayerPlaylistViewModel>() {

    override val viewModel: PlayerPlaylistViewModel
        get() = getViewModel()

    val onDownloadEvent = SingleLiveEvent<YoutubeData>()
    val onDeleteDownloadEvent = SingleLiveEvent<YoutubeData>()
    val onAddFavoritesEvent = SingleLiveEvent<YoutubeData>()
    val onDeleteFavoritesEvent = SingleLiveEvent<YoutubeData>()
    val onHideEvent = SingleLiveEvent<YoutubeData>()

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

                onDownloadEvent.observe(this@PlayerPlaylistFragment, Observer {
                    mViewModel.insertDownload(it)
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

                onDeleteDownloadEvent.observe(this@PlayerPlaylistFragment, Observer {
                    mViewModel.deleteDownload(it)
                })
            }

            onDownloadEvent.observe(this@PlayerPlaylistFragment, Observer {
                this@PlayerPlaylistFragment.onDownloadEvent.value = it
            })

            onAddFavoritesEvent.observe(this@PlayerPlaylistFragment, Observer {
                this@PlayerPlaylistFragment.onAddFavoritesEvent.value = it
            })

            onDeleteFavoritesEvent.observe(this@PlayerPlaylistFragment, Observer {
                this@PlayerPlaylistFragment.onDeleteFavoritesEvent.value = it
            })

            onDeleteDownloadEvent.observe(this@PlayerPlaylistFragment, Observer {
                this@PlayerPlaylistFragment.onDeleteDownloadEvent.value = it
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