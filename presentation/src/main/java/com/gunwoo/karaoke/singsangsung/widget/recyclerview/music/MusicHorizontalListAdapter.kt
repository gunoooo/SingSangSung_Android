package com.gunwoo.karaoke.singsangsung.widget.recyclerview.music

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.ItemMusicHorizontalBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent

class MusicHorizontalListAdapter : Adapter<MusicHorizontalListAdapter.MusicHorizontalItemViewHolder>(),
    MusicHorizontalNavigator {

    private lateinit var youtubeDataList: List<YoutubeData>

    val onClickItemEvent = SingleLiveEvent<YoutubeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHorizontalItemViewHolder {
        return MusicHorizontalItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_music_horizontal, parent, false))
    }

    override fun onBindViewHolder(holder: MusicHorizontalItemViewHolder, position: Int) {
        holder.bind(youtubeDataList[position])
    }

    fun setYoutubeDataList(list: List<YoutubeData>) {
        if(::youtubeDataList.isInitialized) return
        youtubeDataList = list
    }

    override fun onClickItem(youtubeData: YoutubeData) {
        onClickItemEvent.value = youtubeData
    }

    override fun getItemCount(): Int {
        return if(::youtubeDataList.isInitialized) youtubeDataList.size else 0
    }

    inner class MusicHorizontalItemViewHolder(val binding: ItemMusicHorizontalBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = MusicHorizontalItemViewModel()

        fun bind(data: YoutubeData) {
            viewModel.bind(data)
            viewModel.setNavigator(this@MusicHorizontalListAdapter)
            binding.viewModel = viewModel
        }
    }
}