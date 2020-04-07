package com.gunwoo.karaoke.singsangsung.view.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentSearchSettingBinding
import com.gunwoo.karaoke.singsangsung.view.dialog.ChannelAddDialog
import com.gunwoo.karaoke.singsangsung.viewmodel.SearchSettingViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.SearchSettingViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import kotlinx.android.synthetic.main.fragment_search_setting.*
import java.util.*
import javax.inject.Inject


class SearchSettingFragment : BaseFragment<FragmentSearchSettingBinding, SearchSettingViewModel>() {

    @Inject
    lateinit var viewModelFactory: SearchSettingViewModelFactory

    override val viewModel: SearchSettingViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            onOpenChannelAddViewEvent.observe(this@SearchSettingFragment, Observer {
                val channelAddDialog = ChannelAddDialog()
                channelAddDialog.show(this@SearchSettingFragment.fragmentManager)
                channelAddDialog.onSuccessAddChannelEvent.observe(this@SearchSettingFragment, Observer {
                    insertSearchSetting(it)
                })
            })

            onErrorListOverEvent.observe(this@SearchSettingFragment, Observer {
                shortToast(R.string.error_search_setting_list_over)
            })

            with(channelListAdapter) {
                onUpdateSearchSettingEvent.observe(this@SearchSettingFragment, Observer {
                    mViewModel.updateSearchSetting(it)
                })

                onDeleteSearchSettingEvent.observe(this@SearchSettingFragment, Observer {
                    if (mViewModel.searchSettingList.size < 2)
                        shortToast(R.string.error_search_setting_list_remove)
                    else
                        mViewModel.deleteSearchSetting(it)
                })
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
    }

    private fun initToolbar() {
        @Suppress("CAST_NEVER_SUCCEEDS")
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = "검색 설정"
        }
    }
}