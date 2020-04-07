package com.gunwoo.karaoke.singsangsung.view.fragment

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentFavoritesBinding
import com.gunwoo.karaoke.singsangsung.view.activity.PlayerActivity
import com.gunwoo.karaoke.singsangsung.viewmodel.FavoritesViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.FavoritesViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import javax.inject.Inject

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>() {

    @Inject
    lateinit var viewModelFactory: FavoritesViewModelFactory

    override val viewModel: FavoritesViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            recentListAdapter.onClickItemEvent.observe(this@FavoritesFragment, Observer {
                startActivity(
                    Intent(this@FavoritesFragment.context!!.applicationContext, PlayerActivity::class.java)
                        .putExtra(PlayerActivity.EXTRA_VIDEO, it)
                        .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, recentList))
            })

            onErrorEvent.observe(this@FavoritesFragment, Observer {
                shortToast(it.message)
            })

            onOpenFavoritesListFragmentEvent.observe(this@FavoritesFragment, Observer {
                val list = YoutubeDataList()
                list.addAll(videoList)
                val title = "즐겨찾기 목록"
                val type = ListFragment.STORAGE_TYPE
                val action = FavoritesFragmentDirections.actionFavoritesFragmentToListFragment(list, title, type)
                this@FavoritesFragment.findNavController().navigate(action)
            })

            onOpenHidingListFragmentEvent.observe(this@FavoritesFragment, Observer {
                val list = YoutubeDataList()
                list.addAll(videoList)
                val title = "숨김 목록"
                val type = ListFragment.STORAGE_TYPE
                val action = FavoritesFragmentDirections.actionFavoritesFragmentToListFragment(list, title, type)
                this@FavoritesFragment.findNavController().navigate(action)
            })
        }
    }

    override fun onStart() {
        super.onStart()
        mViewModel.setRecentList()
    }
}