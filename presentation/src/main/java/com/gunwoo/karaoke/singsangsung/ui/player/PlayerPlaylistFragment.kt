package com.gunwoo.karaoke.singsangsung.ui.player

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentPlayerPlaylistBinding
import com.gunwoo.karaoke.singsangsung.ui.favorites.FavoritesBottomSheetDialog
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import javax.inject.Inject

class PlayerPlaylistFragment : BaseFragment<FragmentPlayerPlaylistBinding, PlayerPlaylistViewModel>() {

    @Inject
    lateinit var viewModelFactory: PlayerPlaylistViewModelFactory

    override val viewModel: PlayerPlaylistViewModel
        get() = getViewModel(viewModelFactory)

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
                    FavoritesBottomSheetDialog(it).show(fragmentManager!!)
                })

                onHideEvent.observe(this@PlayerPlaylistFragment, Observer {
                    mViewModel.hide(it)
                })

                onOpenYoutubeEvent.observe(this@PlayerPlaylistFragment, Observer {
                    this@PlayerPlaylistFragment.openYoutube(it)
                })

                onShareEvent.observe(this@PlayerPlaylistFragment, Observer {
                    this@PlayerPlaylistFragment.share(it)
                })
            }
        }
    }

    private fun openYoutube(youtubeData: YoutubeData) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://youtube.com/watch?v=${youtubeData.videoId}")
            )
        )
    }

    private fun share(youtubeData: YoutubeData) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "http://youtube.com/watch?v=${youtubeData.videoId}")
        startActivity(Intent.createChooser(intent, "싱생송 - 무료 노래방"))
    }

    lateinit var youtubeDataList: List<YoutubeData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.setYoutubeDataList(youtubeDataList)
    }
}