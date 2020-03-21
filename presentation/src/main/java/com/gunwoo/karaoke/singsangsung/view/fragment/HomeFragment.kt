package com.gunwoo.karaoke.singsangsung.view.fragment

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.UserRecoverableAuthException
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentHomeBinding
import com.gunwoo.karaoke.singsangsung.view.activity.PlayerActivity
import com.gunwoo.karaoke.singsangsung.viewmodel.HomeViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.HomeViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.putImage
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_chart.view.*
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    override val viewModel: HomeViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            popularityList.observe(this@HomeFragment, Observer { setChartList(popularity_chart, it) })

            recentlyList.observe(this@HomeFragment, Observer { setChartList(recently_chart, it) })

            onClickPopularityChartEvent.observe(this@HomeFragment, Observer {
                val list = YoutubeDataList()
                list.addAll(popularityList.value!!)
                val title = "인기차트"
                val type = ListFragment.CHART_TYPE
                val action = HomeFragmentDirections.actionHomeFragmentToListFragment(list, title, type)
                this@HomeFragment.findNavController().navigate(action)
            })

            onClickRecentlyChartEvent.observe(this@HomeFragment, Observer {
                val list = YoutubeDataList()
                list.addAll(recentlyList.value!!)
                val title = "최신차트"
                val type = ListFragment.CHART_TYPE
                val action = HomeFragmentDirections.actionHomeFragmentToListFragment(list, title, type)
                this@HomeFragment.findNavController().navigate(action)
            })

            onErrorEvent.observe(this@HomeFragment, Observer {
                if (it is UserRecoverableAuthException) {
                    startActivityForResult(
                        it.intent,
                        REQUEST_AUTHORIZATION
                    )
                }
                else {
                    shortToast(it.message)
                }
            })
        }
    }

    private fun setChartList(view: View, youtubeDataList: List<YoutubeData>) {
        view.apply {
            val videoList = YoutubeDataList()
            videoList.addAll(youtubeDataList)

            try {
                ranking_1.text = "1"
                title_1.text = youtubeDataList[0].videoTitle
                thumbnail_1.putImage(youtubeDataList[0].thumbnailUrl)

                ranking_2.text = "2"
                title_2.text = youtubeDataList[1].videoTitle
                thumbnail_2.putImage(youtubeDataList[1].thumbnailUrl)

                ranking_3.text = "3"
                title_3.text = youtubeDataList[2].videoTitle
                thumbnail_3.putImage(youtubeDataList[2].thumbnailUrl)
            }
            catch(ignore: IndexOutOfBoundsException) { }

            item_1.setOnClickListener { v ->
                startActivity(Intent(this@HomeFragment.context!!.applicationContext, PlayerActivity::class.java)
                    .putExtra(PlayerActivity.EXTRA_VIDEO_ID, youtubeDataList[0].videoId)
                    .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, videoList))
            }
            item_2.setOnClickListener { v ->
                startActivity(Intent(this@HomeFragment.context!!.applicationContext, PlayerActivity::class.java)
                    .putExtra(PlayerActivity.EXTRA_VIDEO_ID, youtubeDataList[1].videoId)
                    .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, videoList))
            }
            item_3.setOnClickListener { v ->
                startActivity(Intent(this@HomeFragment.context!!.applicationContext, PlayerActivity::class.java)
                    .putExtra(PlayerActivity.EXTRA_VIDEO_ID, youtubeDataList[2].videoId)
                    .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, videoList))
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        val isOK = resultCode == DaggerAppCompatActivity.RESULT_OK
        when (requestCode) {
            REQUEST_AUTHORIZATION ->
                if (isOK) mViewModel.setPlaylist()
        }
    }

    companion object {
        private const val REQUEST_AUTHORIZATION = 0
    }
}