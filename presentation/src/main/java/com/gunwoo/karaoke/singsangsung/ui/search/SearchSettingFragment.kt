package com.gunwoo.karaoke.singsangsung.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.FragmentSearchSettingBinding
import com.gunwoo.karaoke.singsangsung.ui.main.MainChildFragment
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import kotlinx.android.synthetic.main.fragment_search_setting.*
import javax.inject.Inject


class SearchSettingFragment : MainChildFragment<FragmentSearchSettingBinding, SearchSettingViewModel>() {

    @Inject
    lateinit var viewModelFactory: SearchSettingViewModelFactory

    override val viewModel: SearchSettingViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            onOpenChannelAddViewEvent.observe(this@SearchSettingFragment, Observer {
                val channelAddDialog = SearchChannelAddDialog()
                channelAddDialog.show(this@SearchSettingFragment.fragmentManager!!)
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