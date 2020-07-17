package com.gunwoo.karaoke.singsangsung.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.gunwoo.karaoke.singsangsung.BR
import androidx.databinding.ViewDataBinding
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import dagger.android.support.DaggerFragment
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseViewModel
import com.gunwoo.karaoke.singsangsung.ui.main.MainActivity
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

abstract class MainChildFragment<VB : ViewDataBinding, VM : BaseViewModel> : BaseFragment<VB, VM>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getIsLoading().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (activity is MainActivity) {
                (activity as MainActivity).getMViewModel().setIsLoading(it)
            }
        })
    }
}