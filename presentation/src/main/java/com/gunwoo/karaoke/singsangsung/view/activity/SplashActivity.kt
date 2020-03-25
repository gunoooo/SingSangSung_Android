package com.gunwoo.karaoke.singsangsung.view.activity

import android.os.Bundle
import com.gunwoo.karaoke.singsangsung.base.BaseActivity
import com.gunwoo.karaoke.singsangsung.databinding.ActivitySplashBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.SplashViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.checkPermission
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.startActivityWithFinish

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel
        get() = getViewModel()

    override fun observerViewModel() {
        with(mViewModel) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()

        startActivityWithFinish(MainActivity::class.java)
    }
}