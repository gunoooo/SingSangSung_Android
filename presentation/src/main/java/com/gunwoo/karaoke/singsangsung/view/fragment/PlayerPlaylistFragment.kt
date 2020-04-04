package com.gunwoo.karaoke.singsangsung.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentPlayerPlaylistBinding
import com.gunwoo.karaoke.singsangsung.view.activity.PlayerActivity
import com.gunwoo.karaoke.singsangsung.viewmodel.PlayerPlaylistViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.PlayerPlaylistViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import javax.inject.Inject

class PlayerPlaylistFragment : BaseFragment<FragmentPlayerPlaylistBinding, PlayerPlaylistViewModel>() {

    @Inject
    lateinit var vewModelFactory: PlayerPlaylistViewModelFactory

    override val viewModel: PlayerPlaylistViewModel
        get() = getViewModel(vewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            with(musicListAdapter) {
                onClickItem.observe(this@PlayerPlaylistFragment, Observer {
                    startActivity(
                        Intent(this@PlayerPlaylistFragment.context!!.applicationContext, PlayerActivity::class.java)
                            .putExtra(PlayerActivity.EXTRA_VIDEO, it)
                            .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, youtubeDataList))

                    activity!!.finish()
                })

                onDownloadEvent.observe(this@PlayerPlaylistFragment, Observer {
                    mViewModel.download(it, this@PlayerPlaylistFragment.context!!.applicationContext.applicationContext)
                })

                onAddFavoritesEvent.observe(this@PlayerPlaylistFragment, Observer {
                    mViewModel.addFavorites(it)
                })

                onDeleteFavoritesEvent.observe(this@PlayerPlaylistFragment, Observer {
                    mViewModel.deleteFavorites(it, null)
                })

                onHideEvent.observe(this@PlayerPlaylistFragment, Observer {
                    mViewModel.hide(it)
                })
            }

            onSuccessDownloadEvent.observe(this@PlayerPlaylistFragment, Observer {
                shortToast(R.string.message_download_complete)
            })

            onSuccessAddFavoritesEvent.observe(this@PlayerPlaylistFragment, Observer {
                shortToast(R.string.message_add_favorites_complete)
            })

            onSuccessDeleteFavoritesEvent.observe(this@PlayerPlaylistFragment, Observer {
                shortToast(R.string.message_delete_favorites_complete)
            })

            onSuccessHideEvent.observe(this@PlayerPlaylistFragment, Observer {
                shortToast(R.string.message_hide_complete)
            })

            onErrorEvent.observe(this@PlayerPlaylistFragment, Observer {
                shortToast(it.message)
            })
        }
    }

    lateinit var youtubeDataList: List<YoutubeData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.setYoutubeDataList(youtubeDataList)
    }
}