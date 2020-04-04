package com.gunwoo.karaoke.singsangsung.widget.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gunwoo.karaoke.singsangsung.view.fragment.PlayerControllerFragment
import com.gunwoo.karaoke.singsangsung.view.fragment.PlayerPlaylistFragment

class PlayerViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val PAGE_NUMBER = 2

    val playerPlaylistFragment = PlayerPlaylistFragment()
    val playerControllerFragment = PlayerControllerFragment()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> playerControllerFragment
            1 -> playerPlaylistFragment
            else -> playerControllerFragment
        }
    }

    override fun getItemCount(): Int {
        return PAGE_NUMBER
    }
}