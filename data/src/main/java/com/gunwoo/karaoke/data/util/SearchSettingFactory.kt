package com.gunwoo.karaoke.data.util

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

object SearchSettingFactory {

    fun getBaseSearchSetting(): List<SearchSetting> {
        return listOf(
            SearchSetting(
                KY_CHANNEL_ID,
                "금영 노래방",
                5
            ),
            SearchSetting(
                ZZANG_CHANNEL_ID,
                "짱가라오케",
                5
            ),
            SearchSetting(
                MO_CHANNEL_ID,
                "MoPlay노래방",
                5
            ),
            SearchSetting(
                LALA_CHANNEL_ID,
                "라라노래방",
                5
            )

        )
    }

    fun getAllSearchSetting(): List<SearchSetting> {
        return listOf(
            SearchSetting(
                KY_CHANNEL_ID,
                "금영 노래방",
                5
            ),
            SearchSetting(
                ZZANG_CHANNEL_ID,
                "짱가라오케",
                5
            ),
            SearchSetting(
                MO_CHANNEL_ID,
                "MoPlay노래방",
                5
            ),
            SearchSetting(
                LALA_CHANNEL_ID,
                "라라노래방",
                5
            ),
            SearchSetting(
                MAGIC_CHANNEL_ID,
                "MAGICSING",
                5
            ),
            SearchSetting(
                SSING_CHANNEL_ID,
                "SSing Karaoke",
                5
            ),
            SearchSetting(
                SINGIT_CHANNEL_ID,
                "싱잇 노래방",
                5
            ),
            SearchSetting(
                FORU_CHANNEL_ID,
                "Music For U",
                5
            ),
            SearchSetting(
                CHILD_CHANNEL_ID,
                "동요 노래방",
                5
            )
        )
    }
}