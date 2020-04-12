package com.gunwoo.karaoke.singsangsung.ui.search

import androidx.lifecycle.Observer
import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.singsangsung.base.dialog.BaseDialog
import com.gunwoo.karaoke.singsangsung.databinding.DialogSearchChannelAddBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import javax.inject.Inject

class SearchChannelAddDialog : BaseDialog<DialogSearchChannelAddBinding, SearchChannelAddViewModel>() {

    @Inject
    lateinit var viewModelFactory: SearchChannelAddViewModelFactory

    override val viewModel: SearchChannelAddViewModel
        get() = getViewModel(viewModelFactory)

    val onSuccessAddChannelEvent = SingleLiveEvent<SearchSetting>()

    override fun observerViewModel() {
        with(mViewModel) {
            channelListAdapter.onClickItemEvent.observe(this@SearchChannelAddDialog, Observer {
                insertSearchSetting(it)
            })

            onSuccessInsertSearchSettingEvent.observe(this@SearchChannelAddDialog, Observer {
                this@SearchChannelAddDialog.onSuccessAddChannelEvent.value = it
                dismiss()
            })

            onBackEvent.observe(this@SearchChannelAddDialog, Observer {
                dismiss()
            })
        }
    }

    override fun onStart() {
        super.onStart()
        mViewModel.setSearchSettingList()
    }
}