package com.gunwoo.karaoke.singsangsung.ui.favorites

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentFavoritesBinding
import com.gunwoo.karaoke.singsangsung.ui.list.ListFragment
import com.gunwoo.karaoke.singsangsung.ui.player.PlayerActivity
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

            with(favoritesListAdapter) {
                onClickEvent.observe(this@FavoritesFragment, Observer {
                    if (it.favoritesItemList.isNullOrEmpty()) {
                        shortToast(R.string.error_favorites_empty)
                        return@Observer
                    }
                    val list = YoutubeDataList()
                    list.addAll(it.favoritesItemList!!)
                    val title = it.title
                    val type = ListFragment.FAVORITES_TYPE
                    val action = FavoritesFragmentDirections.actionFavoritesFragmentToListFragment(list, title, type, it.id!!)
                    this@FavoritesFragment.findNavController().navigate(action)
                })

                onPlayEvent.observe(this@FavoritesFragment, Observer {
                    if (it.favoritesItemList.isNullOrEmpty()) {
                        shortToast(R.string.error_favorites_empty)
                        return@Observer
                    }
                    startActivity(Intent(this@FavoritesFragment.context!!.applicationContext, PlayerActivity::class.java)
                        .putExtra(PlayerActivity.EXTRA_VIDEO, it.favoritesItemList!![0])
                        .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, ArrayList(it.favoritesItemList!!)))
                })

                onFixTitleEvent.observe(this@FavoritesFragment, Observer {
                    val favoritesFixDialog = FavoritesFixDialog(it)
                    favoritesFixDialog.show(fragmentManager!!)
                    favoritesFixDialog.onSuccessUpdateEvent.observe(this@FavoritesFragment, Observer {
                        setFavoritesList()
                    })
                })

                onDeleteEvent.observe(this@FavoritesFragment, Observer {
                    mViewModel.deleteFavorites(it)
                })
            }

            onClickRecentListEvent.observe(this@FavoritesFragment, Observer {

            })

            onOpenFavoritesAddViewEvent.observe(this@FavoritesFragment, Observer {
                val favoritesAddDialog = FavoritesAddDialog()
                favoritesAddDialog.show(fragmentManager!!)
                favoritesAddDialog.onSuccessInsertEvent.observe(this@FavoritesFragment, Observer {
                    setFavoritesList()
                })
            })

            onErrorEvent.observe(this@FavoritesFragment, Observer {
                shortToast(it.message)
            })
        }
    }

    override fun onStart() {
        super.onStart()
        mViewModel.setRecentList()
        mViewModel.setFavoritesList()
    }
}