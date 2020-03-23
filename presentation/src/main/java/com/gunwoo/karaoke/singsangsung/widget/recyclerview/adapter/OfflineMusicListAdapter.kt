package com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gunwoo.karaoke.domain.model.Download
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.ItemOfflineMusicBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.OfflineMusicNavigator
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel.OfflineMusicItemViewModel

class OfflineMusicListAdapter : Adapter<OfflineMusicListAdapter.MusicItemViewHolder>(), OfflineMusicNavigator {

    private lateinit var downloadList: List<Download>

    val onClickItem = SingleLiveEvent<Download>()
    val onDeleteDownloadEvent = SingleLiveEvent<Download>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicItemViewHolder {
        return MusicItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_offline_music, parent, false))
    }

    override fun onBindViewHolder(holder: MusicItemViewHolder, position: Int) {
        holder.bind(downloadList[position])
    }

    fun setDownloadList(list: List<Download>) {
        if(::downloadList.isInitialized) return
        downloadList = list
    }

    override fun onClickItem(download: Download) {
        onClickItem.value = download
    }

    override fun deleteDownload(download: Download) {
        onDeleteDownloadEvent.value = download
    }

    override fun getItemCount(): Int {
        return if(::downloadList.isInitialized) downloadList.size else 0
    }

    inner class MusicItemViewHolder(val binding: ItemOfflineMusicBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = OfflineMusicItemViewModel()

        fun bind(data: Download) {
            viewModel.bind(data)
            viewModel.setNavigator(this@OfflineMusicListAdapter)
            binding.viewModel = viewModel
        }
    }
}