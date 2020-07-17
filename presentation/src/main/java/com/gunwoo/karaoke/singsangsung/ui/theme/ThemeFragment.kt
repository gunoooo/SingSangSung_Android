package com.gunwoo.karaoke.singsangsung.ui.theme

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.FragmentThemeBinding
import com.gunwoo.karaoke.singsangsung.ui.list.ListFragment
import com.gunwoo.karaoke.singsangsung.ui.main.MainChildFragment
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import kotlinx.android.synthetic.main.fragment_theme.*
import javax.inject.Inject

class ThemeFragment : MainChildFragment<FragmentThemeBinding, ThemeViewModel>() {

    @Inject
    lateinit var viewModelFactory: ThemeViewModelFactory

    override val viewModel: ThemeViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            genrePlaylistListAdapter.onClickItemEvent.observe(this@ThemeFragment, Observer {
                setPlaylistItemList(it)
            })

            programPlaylistListAdapter.onClickItemEvent.observe(this@ThemeFragment, Observer {
                setPlaylistItemList(it)
            })

            moodPlaylistListAdapter.onClickItemEvent.observe(this@ThemeFragment, Observer {
                setPlaylistItemList(it)
            })

            playlistItemList.observe(this@ThemeFragment, Observer {
                val list = YoutubeDataList()
                list.addAll(it.playlistItemList)
                val action = ThemeFragmentDirections.actionThemeFragmentToListFragment(list, it.playlist.title, ListFragment.PLAYLIST_TYPE)
                this@ThemeFragment.findNavController().navigate(action)
            })

            onErrorEvent.observe(this@ThemeFragment, Observer {
                shortToast(R.string.error_playlist_empty)
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        genre_playlist_recyclerview.layoutManager = GridLayoutManager(this.context, 2)
        program_playlist_recyclerview.layoutManager = GridLayoutManager(this.context, 2)
        mood_playlist_recyclerview.layoutManager = GridLayoutManager(this.context, 2)
    }
}