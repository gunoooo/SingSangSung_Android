package com.gunwoo.karaoke.singsangsung.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentHomeBinding
import com.gunwoo.karaoke.singsangsung.ui.player.PlayerActivity
import com.gunwoo.karaoke.singsangsung.ui.list.ListFragment
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.putImage
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.imageslider.ImageSliderAdapter
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
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

            onOpenListFragmentEvent.observe(this@HomeFragment, Observer {
                val list = YoutubeDataList()
                list.addAll(videoList)
                val action = HomeFragmentDirections.actionHomeFragmentToListFragment(list, playlistTitle!!, playlistType!!)
                this@HomeFragment.findNavController().navigate(action)
            })

            onErrorEvent.observe(this@HomeFragment, Observer {
                shortToast(it.message)
            })

            recommendListAdapter.onClickItemEvent.observe(this@HomeFragment, Observer {
                startActivity(Intent(this@HomeFragment.context!!.applicationContext, PlayerActivity::class.java)
                    .putExtra(PlayerActivity.EXTRA_VIDEO, it)
                    .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, recommendList))
            })

            recommendPlaylistListAdapter.onClickItemEvent.observe(this@HomeFragment, Observer {
                playlistTitle = it.title
                playlistType = ListFragment.PLAYLIST_TYPE
                setVideoList(it.playlistId)
            })

            characterListAdapter.onClickItemEvent.observe(this@HomeFragment, Observer {
                playlistTitle = it.name
                playlistType = ListFragment.CHARACTER_TYPE
                setVideoList(it.playlistId)
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
                    .putExtra(PlayerActivity.EXTRA_VIDEO, youtubeDataList[0])
                    .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, videoList))
            }
            item_2.setOnClickListener { v ->
                startActivity(Intent(this@HomeFragment.context!!.applicationContext, PlayerActivity::class.java)
                    .putExtra(PlayerActivity.EXTRA_VIDEO, youtubeDataList[1])
                    .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, videoList))
            }
            item_3.setOnClickListener { v ->
                startActivity(Intent(this@HomeFragment.context!!.applicationContext, PlayerActivity::class.java)
                    .putExtra(PlayerActivity.EXTRA_VIDEO, youtubeDataList[2])
                    .putExtra(PlayerActivity.EXTRA_VIDEO_LIST, videoList))
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initAds()
    }

    override fun onStart() {
        super.onStart()
        mViewModel.setPlaylistList()
    }

    private fun initAds() {
        MobileAds.initialize(this.activity, getString(R.string.admob_app_id))
        val adRequest: AdRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        smallAdView.loadAd(adRequest)
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        recommend_playlist_recyclerview.layoutManager = GridLayoutManager(this.context, 2)

        image_slider.apply {
            setSliderAdapter(ImageSliderAdapter())
            setIndicatorAnimation(IndicatorAnimations.WORM)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            startAutoCycle()
        }
    }
}