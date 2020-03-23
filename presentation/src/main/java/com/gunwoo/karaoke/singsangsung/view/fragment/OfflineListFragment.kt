package com.gunwoo.karaoke.singsangsung.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentOfflineListBinding
import com.gunwoo.karaoke.singsangsung.view.activity.OfflinePlayerActivity
import com.gunwoo.karaoke.singsangsung.viewmodel.OfflineListViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.OfflineListViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import kotlinx.android.synthetic.main.fragment_offline_list.*
import javax.inject.Inject

class OfflineListFragment : BaseFragment<FragmentOfflineListBinding, OfflineListViewModel>() {

    @Inject
    lateinit var viewModelFactory: OfflineListViewModelFactory

    override val viewModel: OfflineListViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            with(offlineMusicListAdapter) {
                onClickItem.observe(this@OfflineListFragment, Observer {
                    startActivity(
                        Intent(this@OfflineListFragment.context!!.applicationContext, OfflinePlayerActivity::class.java)
                            .putExtra(OfflinePlayerActivity.EXTRA_VIDEO, it)
                            .putExtra(OfflinePlayerActivity.EXTRA_VIDEO_LIST, downloadList))
                })

                onDeleteDownloadEvent.observe(this@OfflineListFragment, Observer {
                    mViewModel.deleteDownload(it)
                })
            }

            onSuccessDeleteDownloadEvent.observe(this@OfflineListFragment, Observer {
                shortToast(R.string.message_delete_download_complete)
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val downloadList = OfflineListFragmentArgs.fromBundle(arguments!!).list
        mViewModel.setData(downloadList)

        initUI()
    }

    private fun initUI() {
        collapsing_layout.setCollapsedTitleTextAppearance(R.style.AppTheme_CollapsedAppBar)
        collapsing_layout.setExpandedTitleTextAppearance(R.style.AppTheme_ExpandedAppBar)

        @Suppress("CAST_NEVER_SUCCEEDS")
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            this.title = "다운로드 목록"
        }
    }

}