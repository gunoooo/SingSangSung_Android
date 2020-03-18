package com.gunwoo.karaoke.singsangsung.view.fragment

import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentFavoritesBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.FavoritesViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>() {

    override val viewModel: FavoritesViewModel
        get() = getViewModel()

    override fun observerViewModel() {

    }
}