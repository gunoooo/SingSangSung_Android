package com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gunwoo.karaoke.domain.model.Record
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.ItemRecordBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.RecordNavigator
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel.RecordItemViewModel

class RecordListAdapter : Adapter<RecordListAdapter.RecordItemViewHolder>(), RecordNavigator {

    private lateinit var youtubeDataList: List<Record>

    val onClickItem = SingleLiveEvent<Record>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordItemViewHolder {
        return RecordItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_record, parent, false))
    }

    override fun onBindViewHolder(holder: RecordItemViewHolder, position: Int) {
        holder.bind(youtubeDataList[position])
    }

    fun setYoutubeDataList(list: List<Record>) {
        if(::youtubeDataList.isInitialized) return
        youtubeDataList = list
    }

    override fun onClickItem(record: Record) {
        onClickItem.value = record
    }

    override fun getItemCount(): Int {
        return if(::youtubeDataList.isInitialized) youtubeDataList.size else 0
    }

    inner class RecordItemViewHolder(val binding: ItemRecordBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = RecordItemViewModel()

        fun bind(data: Record) {
            viewModel.bind(data)
            viewModel.setNavigator(this@RecordListAdapter)
            binding.viewModel = viewModel
        }
    }
}