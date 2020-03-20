package com.gunwoo.karaoke.singsangsung.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gunwoo.karaoke.domain.model.Character
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.ItemCharacterBinding
import com.gunwoo.karaoke.singsangsung.widget.SingleLiveEvent
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.navigator.CharacterNavigator
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.viewmodel.CharacterItemViewModel

class CharacterListAdapter : Adapter<CharacterListAdapter.MusicItemViewHolder>(), CharacterNavigator {

    private lateinit var characterList: List<Character>

    val onClickItem = SingleLiveEvent<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicItemViewHolder {
        return MusicItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: MusicItemViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    fun setCharacterList(list: List<Character>) {
        if(::characterList.isInitialized) return
        characterList = list
    }

    override fun onClickItem(character: Character) {
        onClickItem.value = character
    }

    override fun getItemCount(): Int {
        return if(::characterList.isInitialized) characterList.size else 0
    }

    inner class MusicItemViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = CharacterItemViewModel()

        fun bind(data: Character) {
            viewModel.bind(data)
            viewModel.setNavigator(this@CharacterListAdapter)
            binding.viewModel = viewModel
        }
    }
}