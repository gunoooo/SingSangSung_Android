package com.gunwoo.karaoke.singsangsung.view.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentOfflineListBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.OfflineListViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import kotlinx.android.synthetic.main.fragment_offline_list.*

class OfflineListFragment : BaseFragment<FragmentOfflineListBinding, OfflineListViewModel>() {

    override val viewModel: OfflineListViewModel
        get() = getViewModel()

    override fun observerViewModel() {
        with(mViewModel) {

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