package com.gunwoo.karaoke.singsangsung.view.fragment

import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentHomeBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.HomeViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel
        get() = getViewModel()

    override fun observerViewModel() {

    }

}