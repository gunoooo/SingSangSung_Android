package com.gunwoo.karaoke.singsangsung.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentListBinding
import com.gunwoo.karaoke.singsangsung.view.activity.PlayerActivity
import com.gunwoo.karaoke.singsangsung.viewmodel.ListViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.ListViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import kotlinx.android.synthetic.main.fragment_list.*
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

                onDownloadEvent.observe(this@ListFragment, Observer {
                    mViewModel.download(it)
                })

                onAddFavoritesEvent.observe(this@ListFragment, Observer {
                    mViewModel.addFavorites(it)
                })

                onDeleteFavoritesEvent.observe(this@ListFragment, Observer {
                    mViewModel.deleteFavorites(it)
                })

                onHideEvent.observe(this@ListFragment, Observer {
                    mViewModel.hide(it)
                })

                onDeleteHidingEvent.observe(this@ListFragment, Observer {
                    mViewModel.deleteHiding(it)
                })

                onDeleteDownloadEvent.observe(this@ListFragment, Observer {
                    mViewModel.deleteDownload(it)
                })
            }

            onSuccessDownloadEvent.observe(this@ListFragment, Observer {
                shortToast(R.string.message_download_complete)
            })

            onSuccessAddFavoritesEvent.observe(this@ListFragment, Observer {
                shortToast(R.string.message_add_favorites_complete)
            })

            onSuccessDeleteFavoritesEvent.observe(this@ListFragment, Observer {
                thumbnail.value = youtubeDataList[0].thumbnailUrl
                description.value = "총 ${youtubeDataList.size}곡  |  $type"
                shortToast(R.string.message_delete_favorites_complete)
            })

            onSuccessHideEvent.observe(this@ListFragment, Observer {
                thumbnail.value = youtubeDataList[0].thumbnailUrl
                description.value = "총 ${youtubeDataList.size}곡  |  $type"
                shortToast(R.string.message_hide_complete)
            })

            onSuccessDeleteDownloadEvent.observe(this@ListFragment, Observer {
                thumbnail.value = youtubeDataList[0].thumbnailUrl
                description.value = "총 ${youtubeDataList.size}곡  |  $type"
                shortToast(R.string.message_delete_download_complete)
            })

            onSuccessDeleteHidingEvent.observe(this@ListFragment, Observer {
                thumbnail.value = youtubeDataList[0].thumbnailUrl
                description.value = "총 ${youtubeDataList.size}곡  |  $type"
                shortToast(R.string.message_show_complete)
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val youtubeDataList = ListFragmentArgs.fromBundle(arguments!!).list
        val title = ListFragmentArgs.fromBundle(arguments!!).title
        val type = ListFragmentArgs.fromBundle(arguments!!).type

        initUI(title)

        mViewModel.setData(youtubeDataList, type, title)
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
        const val STORAGE_TYPE = "보관함"
    }
}