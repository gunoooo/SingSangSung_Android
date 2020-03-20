package com.gunwoo.karaoke.singsangsung.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseActivity
import com.gunwoo.karaoke.singsangsung.databinding.ActivityMainBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.MainViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.listener.OnHomeButtonClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel
        get() = getViewModel()

    override fun observerViewModel() {

    }

    private lateinit var navController: NavController

    lateinit var onHomeButtonClickListener: OnHomeButtonClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.page_fragment)
        bottom_navigation_view.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onHomeButtonClickListener.onClick()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}