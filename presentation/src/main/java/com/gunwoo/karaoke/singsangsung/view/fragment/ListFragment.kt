package com.gunwoo.karaoke.singsangsung.view.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentListBinding
import com.gunwoo.karaoke.singsangsung.view.activity.MainActivity
import com.gunwoo.karaoke.singsangsung.viewmodel.ListViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.listener.OnHomeButtonClickListener
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>(), OnHomeButtonClickListener {

    override val viewModel: ListViewModel
        get() = getViewModel()

    override fun observerViewModel() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).onHomeButtonClickListener = this

        val youtubeDataList = ListFragmentArgs.fromBundle(arguments!!).list
        val title = ListFragmentArgs.fromBundle(arguments!!).title
        val type = ListFragmentArgs.fromBundle(arguments!!).type

        initUI(title)

        mViewModel.setYoutubeDataList(youtubeDataList, type)
    }

    private fun initUI(title: String) {
        collapsing_layout.setCollapsedTitleTextAppearance(R.style.AppTheme_CollapsedAppBar)
        collapsing_layout.setExpandedTitleTextAppearance(R.style.AppTheme_ExpandedAppBar)

        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            this.title = title
        }
    }

    override fun onClick() {
        val action =
            when (mViewModel.type) {
                SEARCH_TYPE -> ListFragmentDirections.actionListFragmentToSearchFragment()
                CHART_TYPE, CHARACTER_TYPE -> ListFragmentDirections.actionListFragmentToHomeFragment()
                PLAYLIST_TYPE -> ListFragmentDirections.actionListFragmentToThemeFragment()
                else -> return
            }

        findNavController().navigate(action)
    }

    companion object {
        const val SEARCH_TYPE = "검색 결과"
        const val CHART_TYPE = "차트"
        const val CHARACTER_TYPE = "인물"
        const val PLAYLIST_TYPE = "재생 목록"
    }
}