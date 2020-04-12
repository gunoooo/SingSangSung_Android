package com.gunwoo.karaoke.singsangsung.widget.recyclerview.music

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.ItemMusicBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent

class MusicListAdapter :
    Adapter<MusicListAdapter.MusicItemViewHolder>(),
    MusicNavigator {

    private lateinit var youtubeDataList: List<YoutubeData>
    private lateinit var viewType: ViewType

    val onClickItemEvent = SingleLiveEvent<YoutubeData>()
    val onAddFavoritesEvent = SingleLiveEvent<YoutubeData>()
    val onDeleteFavoritesEvent = SingleLiveEvent<YoutubeData>()
    val onHideEvent = SingleLiveEvent<YoutubeData>()
    val onDeleteHidingEvent = SingleLiveEvent<YoutubeData>()
    val onOpenYoutubeEvent = SingleLiveEvent<YoutubeData>()
    val onShareEvent = SingleLiveEvent<YoutubeData>()

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

    fun setViewType(viewType: ViewType) {
        this.viewType = viewType
    }

    override fun onClickItem(youtubeData: YoutubeData) {
        onClickItemEvent.value = youtubeData
    }

    override fun addFavorites(youtubeData: YoutubeData) {
        onAddFavoritesEvent.value = youtubeData
    }

    override fun deleteFavorites(youtubeData: YoutubeData) {
        onDeleteFavoritesEvent.value = youtubeData
    }

    override fun hide(youtubeData: YoutubeData) {
        onHideEvent.value = youtubeData
    }

    override fun deleteHiding(youtubeData: YoutubeData) {
        onDeleteHidingEvent.value = youtubeData
    }

    override fun openYoutube(youtubeData: YoutubeData) {
        onOpenYoutubeEvent.value = youtubeData
    }

    override fun share(youtubeData: YoutubeData) {
        onShareEvent.value = youtubeData
    }

    override fun getItemCount(): Int {
        return if(::youtubeDataList.isInitialized) youtubeDataList.size else 0
    }

    inner class MusicItemViewHolder(val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = MusicItemViewModel()

        fun bind(data: YoutubeData) {
            viewModel.bind(data, viewType)
            viewModel.setNavigator(this@MusicListAdapter)
            binding.viewModel = viewModel
        }
    }

    enum class ViewType {
        FAVORITES,
        OTHER
    }
}