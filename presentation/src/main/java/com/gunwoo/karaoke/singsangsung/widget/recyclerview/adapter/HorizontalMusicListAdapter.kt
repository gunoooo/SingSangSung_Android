package com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.ItemHorizontalMusicBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.HorizontalMusicNavigator
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel.HorizontalMusicItemViewModel

class HorizontalMusicListAdapter : Adapter<HorizontalMusicListAdapter.HorizontalMusicItemViewHolder>(), HorizontalMusicNavigator {

    private lateinit var youtubeDataList: List<YoutubeData>

    val onClickItem = SingleLiveEvent<YoutubeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalMusicItemViewHolder {
        return HorizontalMusicItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_horizontal_music, parent, false))
    }

    override fun onBindViewHolder(holder: HorizontalMusicItemViewHolder, position: Int) {
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

    inner class HorizontalMusicItemViewHolder(val binding: ItemHorizontalMusicBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = HorizontalMusicItemViewModel()

        fun bind(data: YoutubeData) {
            viewModel.bind(data)
            viewModel.setNavigator(this@HorizontalMusicListAdapter)
            binding.viewModel = viewModel
        }
    }
}