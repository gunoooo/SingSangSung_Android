package com.gunwoo.karaoke.singsangsung.widget.recyclerview.channel

import androidx.lifecycle.MutableLiveData
import com.gunwoo.karaoke.data.util.Constants.CHILD_CHANNEL_ID
import com.gunwoo.karaoke.data.util.Constants.FORU_CHANNEL_ID
import com.gunwoo.karaoke.data.util.Constants.KY_CHANNEL_ID
import com.gunwoo.karaoke.data.util.Constants.LALA_CHANNEL_ID
import com.gunwoo.karaoke.data.util.Constants.MAGIC_CHANNEL_ID
import com.gunwoo.karaoke.data.util.Constants.MO_CHANNEL_ID
import com.gunwoo.karaoke.data.util.Constants.SINGIT_CHANNEL_ID
import com.gunwoo.karaoke.data.util.Constants.SSING_CHANNEL_ID
import com.gunwoo.karaoke.data.util.Constants.ZZANG_CHANNEL_ID
import com.gunwoo.karaoke.domain.model.SearchSetting
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.viewmodel.BaseItemViewModel
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.channel.ChannelListAdapter
import com.gunwoo.karaoke.singsangsung.widget.recyclerview.channel.ChannelNavigator


class ChannelItemViewModel : BaseItemViewModel<ChannelNavigator>() {

    private lateinit var searchSetting: SearchSetting

    val channelProfile = MutableLiveData<Int>()
    val channelTitle = MutableLiveData<String>()
    val maxResultsPosition = MutableLiveData<Int>()
    val isSelectedChannel = MutableLiveData<Boolean>(true)

    fun bind(data: SearchSetting, viewType: ChannelListAdapter.ViewType) {
        searchSetting = data

        channelProfile.value =
            when(searchSetting.channelId) {
                KY_CHANNEL_ID -> R.drawable.profile_channel_ky
                ZZANG_CHANNEL_ID -> R.drawable.profile_channel_zzang
                MO_CHANNEL_ID -> R.drawable.profile_channel_mopaly
                LALA_CHANNEL_ID -> R.drawable.profile_channel_lala
                MAGIC_CHANNEL_ID -> R.drawable.profile_channel_magicsing
                SSING_CHANNEL_ID -> R.drawable.profile_channel_ssing
                SINGIT_CHANNEL_ID -> R.drawable.profile_channel_singit
                FORU_CHANNEL_ID -> R.drawable.profile_channel_foru
                CHILD_CHANNEL_ID -> R.drawable.profile_channel_child
                else -> R.color.colorLightGrey
            }

        channelTitle.value = searchSetting.channelTitle
        maxResultsPosition.value = searchSetting.maxResults - 1
        isSelectedChannel.value = viewType == ChannelListAdapter.ViewType.SELECTED_CHANNEL
    }

    fun onClickDelete() { if (isSelectedChannel.value!!) getNavigator().deleteSearchSetting(searchSetting) }

    fun onSelectItem(position: Int) {
        if (isSelectedChannel.value!!) {
            maxResultsPosition.value = position
            searchSetting.maxResults = position + 1

            getNavigator().updateSearchSetting(searchSetting)
        }
    }

    fun onClickItem() { if (!isSelectedChannel.value!!) getNavigator().onClickItem(searchSetting) }
}