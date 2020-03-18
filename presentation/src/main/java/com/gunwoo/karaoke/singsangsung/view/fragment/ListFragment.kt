package com.gunwoo.karaoke.singsangsung.view.fragment

import android.os.Bundle
import android.view.View
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentListBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.ListViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel

class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>() {

    override val viewModel: ListViewModel
        get() = getViewModel()

    override fun observerViewModel() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val youtubeDataList = ListFragmentArgs.fromBundle(savedInstanceState!!).list
        mViewModel.setYoutubeDataList(youtubeDataList)
    }
}