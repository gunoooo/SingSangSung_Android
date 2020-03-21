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
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>() {

    override val viewModel: ListViewModel
        get() = getViewModel()

    override fun observerViewModel() {
        with(mViewModel) {
            musicListAdapter.onClickItem.observe(this@ListFragment, Observer {
                startActivity(
                    Intent(this@ListFragment.context!!.applicationContext, PlayerActivity::class.java)
                        .putExtra(PlayerActivity.EXTRA_VIDEO_ID, it.videoId)
                        .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, youtubeDataList))
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val youtubeDataList = ListFragmentArgs.fromBundle(arguments!!).list
        val title = ListFragmentArgs.fromBundle(arguments!!).title
        val type = ListFragmentArgs.fromBundle(arguments!!).type

        initUI(title)

        mViewModel.setData(youtubeDataList, type)
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
    }
}