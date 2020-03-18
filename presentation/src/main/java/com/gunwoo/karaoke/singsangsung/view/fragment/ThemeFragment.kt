package com.gunwoo.karaoke.singsangsung.view.fragment

import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentThemeBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.ThemeViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel

class ThemeFragment : BaseFragment<FragmentThemeBinding, ThemeViewModel>() {

    override val viewModel: ThemeViewModel
        get() = getViewModel()

    override fun observerViewModel() {

    }
}