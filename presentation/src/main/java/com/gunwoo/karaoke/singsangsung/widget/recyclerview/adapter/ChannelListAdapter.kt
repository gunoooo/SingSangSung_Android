package com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.ItemChannelBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.ChannelNavigator
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel.ChannelItemViewModel

class ChannelListAdapter : Adapter<ChannelListAdapter.ChannelItemViewHolder>(), ChannelNavigator {

    private lateinit var searchSettingList: List<SearchSetting>
    private lateinit var viewType: ViewType

    val onClickItemEvent = SingleLiveEvent<SearchSetting>()
    val onDeleteSearchSettingEvent = SingleLiveEvent<SearchSetting>()
    val onUpdateSearchSettingEvent = SingleLiveEvent<SearchSetting>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelItemViewHolder {
        return ChannelItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_channel, parent, false))
    }

    override fun onBindViewHolder(holder: ChannelItemViewHolder, position: Int) {
        holder.bind(searchSettingList[position], viewType)
    }

    override fun onClickItem(searchSetting: SearchSetting) {
        onClickItemEvent.value = searchSetting
    }

    override fun deleteSearchSetting(searchSetting: SearchSetting) {
        onDeleteSearchSettingEvent.value = searchSetting
    }

    override fun updateSearchSetting(searchSetting: SearchSetting) {
        onUpdateSearchSettingEvent.value = searchSetting
    }

    fun setSearchSettingList(list: List<SearchSetting>) {
        if(::searchSettingList.isInitialized) return
        searchSettingList = list
    }

    fun setViewType(viewType: ViewType) {
        this.viewType = viewType
    }

    override fun getItemCount(): Int {
        return if(::searchSettingList.isInitialized) searchSettingList.size else 0
    }

    inner class ChannelItemViewHolder(val binding: ItemChannelBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = ChannelItemViewModel()

        fun bind(data: SearchSetting, viewType: ViewType) {
            viewModel.bind(data, viewType)
            viewModel.setNavigator(this@ChannelListAdapter)
            binding.viewModel = viewModel
        }
    }

    enum class ViewType {
        SELECTED_CHANNEL,
        NOT_SELECTED_CHANNEL
    }
}