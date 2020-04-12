package com.gunwoo.karaoke.singsangsung.ui.list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentListBinding
import com.gunwoo.karaoke.singsangsung.ui.favorites.FavoritesBottomSheetDialog
import com.gunwoo.karaoke.singsangsung.ui.player.PlayerActivity
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.IndexOutOfBoundsException
import javax.inject.Inject

class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>() {

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    override val viewModel: ListViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            with(musicListAdapter) {
                onClickItemEvent.observe(this@ListFragment, Observer {
                    startActivity(
                        Intent(this@ListFragment.context!!.applicationContext, PlayerActivity::class.java)
                            .putExtra(PlayerActivity.EXTRA_VIDEO, it)
                            .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, youtubeDataList))
                })

                onAddFavoritesEvent.observe(this@ListFragment, Observer {
                    FavoritesBottomSheetDialog(it).show(fragmentManager!!)
                })

                onDeleteFavoritesEvent.observe(this@ListFragment, Observer {
                    mViewModel.deleteFavoritesItem(it)
                })

                onHideEvent.observe(this@ListFragment, Observer {
                    mViewModel.hide(it)
                })

                onDeleteHidingEvent.observe(this@ListFragment, Observer {
                    mViewModel.deleteHiding(it)
                })

                onOpenYoutubeEvent.observe(this@ListFragment, Observer {
                    this@ListFragment.openYoutube(it)
                })

                onShareEvent.observe(this@ListFragment, Observer {
                    this@ListFragment.share(it)
                })
            }

            onSuccessDeleteFavoritesEvent.observe(this@ListFragment, Observer {
                resetListInfo()
                shortToast(R.string.message_delete_favorites_complete)
            })

            onSuccessHideEvent.observe(this@ListFragment, Observer {
                resetListInfo()
                shortToast(R.string.message_hide_complete)
            })

            onSuccessDeleteHidingEvent.observe(this@ListFragment, Observer {
                resetListInfo()
                shortToast(R.string.message_show_complete)
            })
        }
    }

    private fun resetListInfo() {
        try {
            mViewModel.thumbnail.value = mViewModel.youtubeDataList[0].thumbnailUrl
            mViewModel.description.value = "총 ${mViewModel.youtubeDataList.size}곡  |  ${mViewModel.type}"
        } catch (e: IndexOutOfBoundsException) {
            this@ListFragment.activity?.onBackPressed()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val youtubeDataList = ListFragmentArgs.fromBundle(arguments!!).list
        val title = ListFragmentArgs.fromBundle(arguments!!).title
        val type = ListFragmentArgs.fromBundle(arguments!!).type
        val favoritesId = ListFragmentArgs.fromBundle(arguments!!).favoritesId

        initUI(title)

        mViewModel.setData(youtubeDataList, type, title, favoritesId)
    }

    private fun initUI(title: String) {
        collapsing_layout.setCollapsedTitleTextAppearance(R.style.AppTheme_CollapsedAppBar)
        collapsing_layout.setExpandedTitleTextAppearance(R.style.AppTheme_ExpandedAppBar)

        @Suppress("CAST_NEVER_SUCCEEDS")
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            this.title = title
        }
    }

    companion object {
        const val SEARCH_TYPE = "검색 결과"
        const val CHART_TYPE = "차트"
        const val CHARACTER_TYPE = "인물"
        const val PLAYLIST_TYPE = "재생 목록"
        const val FAVORITES_TYPE = "즐겨찾기"
    }
}