package com.gunwoo.karaoke.singsangsung.view.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentThemeBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.ThemeViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import kotlinx.android.synthetic.main.fragment_theme.*

class ThemeFragment : BaseFragment<FragmentThemeBinding, ThemeViewModel>() {

    override val viewModel: ThemeViewModel
        get() = getViewModel()

    override fun observerViewModel() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        genre_playlist_recyclerview.layoutManager = GridLayoutManager(this.context, 2)
        program_playlist_recyclerview.layoutManager = GridLayoutManager(this.context, 2)
        mood_playlist_recyclerview.layoutManager = GridLayoutManager(this.context, 2)
    }
}