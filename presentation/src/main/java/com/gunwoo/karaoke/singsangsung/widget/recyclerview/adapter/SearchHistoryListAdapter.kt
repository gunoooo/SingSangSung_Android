package com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.ItemSearchHistoryBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.SearchHistoryNavigator
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel.SearchHistoryItemViewModel

class SearchHistoryListAdapter : Adapter<SearchHistoryListAdapter.SearchHistoryItemViewHolder>(), SearchHistoryNavigator {

    private lateinit var searchHistoryList: List<String>

    val onClickItemEvent = SingleLiveEvent<String>()
    val onClickRemoveEvent = SingleLiveEvent<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryItemViewHolder {
        return SearchHistoryItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_search_history, parent, false))
    }

    override fun onBindViewHolder(holder: SearchHistoryItemViewHolder, position: Int) {
        holder.bind(searchHistoryList[position])
    }

    fun setYoutubeDataList(list: List<String>) {
        if(::searchHistoryList.isInitialized) return
        searchHistoryList = list
    }

    override fun onClickItem(search: String) {
        onClickItemEvent.value = search
    }

    override fun onClickRemove(search: String) {
        onClickRemoveEvent.value = search
    }

    override fun getItemCount(): Int {
        return if(::searchHistoryList.isInitialized) searchHistoryList.size else 0
    }

    inner class SearchHistoryItemViewHolder(val binding: ItemSearchHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = SearchHistoryItemViewModel()

        fun bind(data: String) {
            viewModel.bind(data)
            viewModel.setNavigator(this@SearchHistoryListAdapter)
            binding.viewModel = viewModel
        }
    }
}