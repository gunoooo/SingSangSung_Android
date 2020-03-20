package com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.ItemMusicBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.MusicNavigator
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel.MusicItemViewModel

class MusicListAdapter : Adapter<MusicListAdapter.MusicItemViewHolder>(), MusicNavigator {

    private lateinit var youtubeDataList: List<YoutubeData>

    val onClickItem = SingleLiveEvent<YoutubeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicItemViewHolder {
        return MusicItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_music, parent, false))
    }

    override fun onBindViewHolder(holder: MusicItemViewHolder, position: Int) {
        holder.bind(youtubeDataList[position])
    }

    fun setYoutubeDataList(list: List<YoutubeData>) {
        if(::youtubeDataList.isInitialized) return
        youtubeDataList = list
    }

    override fun onClickItem(youtubeData: YoutubeData) {
        onClickItem.value = youtubeData
    }

    override fun getItemCount(): Int {
        return if(::youtubeDataList.isInitialized) youtubeDataList.size else 0
    }

    inner class MusicItemViewHolder(val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = MusicItemViewModel()

        fun bind(data: YoutubeData) {
            viewModel.bind(data)
            viewModel.setNavigator(this@MusicListAdapter)
            binding.viewModel = viewModel
        }
    }
}