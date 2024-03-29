package com.gunwoo.karaoke.singsangsung.widget.recyclerview.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gunwoo.karaoke.domain.model.Playlist
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.ItemPlaylistBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent

class PlaylistListAdapter : Adapter<PlaylistListAdapter.PlaylistItemViewHolder>(),
    PlaylistNavigator {

    private lateinit var playlistList: List<Playlist>

    val onClickItemEvent = SingleLiveEvent<Playlist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        return PlaylistItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_playlist, parent, false))
    }

    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        holder.bind(playlistList[position])
    }

    fun setPlaylistList(list: List<Playlist>) {
        if(::playlistList.isInitialized) return
        playlistList = list
    }

    override fun onClickItem(playlist: Playlist) {
        onClickItemEvent.value = playlist
    }

    override fun getItemCount(): Int {
        return if(::playlistList.isInitialized) playlistList.size else 0
    }

    inner class PlaylistItemViewHolder(val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel =
            PlaylistItemViewModel()

        fun bind(data: Playlist) {
            viewModel.bind(data)
            viewModel.setNavigator(this@PlaylistListAdapter)
            binding.viewModel = viewModel
        }
    }
}