package com.gunwoo.karaoke.singsangsung.widget.recyclerview.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gunwoo.karaoke.domain.model.Favorites
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.ItemFavoritesBinding
import com.gunwoo.karaoke.singsangsung.databinding.ItemFavoritesCheckBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent

class FavoritesListAdapter(private val favoritesList: List<Favorites>) :
    Adapter<FavoritesListAdapter.FavoritesItemViewHolder>(),
    FavoritesNavigator {

    val onClickEvent = SingleLiveEvent<Favorites>()
    val onPlayEvent = SingleLiveEvent<Favorites>()
    val onFixTitleEvent = SingleLiveEvent<Favorites>()
    val onDeleteEvent = SingleLiveEvent<Favorites>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesItemViewHolder {
        return FavoritesItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_favorites, parent, false))
    }

    override fun onBindViewHolder(holder: FavoritesItemViewHolder, position: Int) {
        holder.bind(favoritesList[position])
    }

    override fun onClickItem(favorites: Favorites) {
        onClickEvent.value = favorites
    }

    override fun play(favorites: Favorites) {
        onPlayEvent.value = favorites
    }

    override fun fixTitle(favorites: Favorites) {
        onFixTitleEvent.value = favorites
    }

    override fun delete(favorites: Favorites) {
        onDeleteEvent.value = favorites
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    inner class FavoritesItemViewHolder(val binding: ItemFavoritesBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = FavoritesItemViewModel()

        fun bind(data: Favorites) {
            viewModel.bind(data)
            viewModel.setNavigator(this@FavoritesListAdapter)
            binding.viewModel = viewModel
        }
    }
}