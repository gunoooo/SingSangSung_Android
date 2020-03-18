package com.gunwoo.karaoke.singsangsung.view.fragment

import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentRecordBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.RecordViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel

class RecordFragment : BaseFragment<FragmentRecordBinding, RecordViewModel>() {

    override val viewModel: RecordViewModel
        get() = getViewModel()

    override fun observerViewModel() {

    }
}