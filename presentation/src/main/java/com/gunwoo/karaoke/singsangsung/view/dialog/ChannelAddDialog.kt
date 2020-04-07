package com.gunwoo.karaoke.singsangsung.view.dialog

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.singsangsung.base.BaseDialog
import com.gunwoo.karaoke.singsangsung.databinding.DialogChannelAddBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.ChannelAddViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.ChannelAddViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import javax.inject.Inject

class ChannelAddDialog : BaseDialog<DialogChannelAddBinding, ChannelAddViewModel>() {

    @Inject
    lateinit var viewModelFactory: ChannelAddViewModelFactory

    override val viewModel: ChannelAddViewModel
        get() = getViewModel(viewModelFactory)

    val onSuccessAddChannelEvent = SingleLiveEvent<SearchSetting>()

    override fun observerViewModel() {
        with(mViewModel) {
            channelListAdapter.onClickItemEvent.observe(this@ChannelAddDialog, Observer {
                insertSearchSetting(it)
            })

            onSuccessInsertSearchSettingEvent.observe(this@ChannelAddDialog, Observer {
                this@ChannelAddDialog.onSuccessAddChannelEvent.value = it
                dismiss()
            })

            onBackEvent.observe(this@ChannelAddDialog, Observer {
                dismiss()
            })
        }
    }

    override fun onStart() {
        super.onStart()
        mViewModel.setSearchSettingList()
    }

    fun show(fragmentManager: FragmentManager?) {
        super.show(fragmentManager!!, tag)
    }
}