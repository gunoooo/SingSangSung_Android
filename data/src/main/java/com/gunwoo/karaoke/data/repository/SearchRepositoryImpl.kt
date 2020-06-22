package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.*
import com.gunwoo.karaoke.data.exception.ListEmptyException
import com.gunwoo.karaoke.domain.model.YoutubeData
import com.gunwoo.karaoke.domain.repository.SearchRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
    private val searchSettingDataSource: SearchSettingDataSource,
    private val searchHistoryDataSource: SearchHistoryDataSource,
    private val hidingDataSource: HidingDataSource
) : SearchRepository {

    override fun getSearchList(search: String): Single<List<YoutubeData>> {
        return searchSettingDataSource.getSearchSettingList().flatMap {
            val list = LinkedList<YoutubeData>()
            val settingSize = it.size
            return@flatMap searchDataSource.getSearchList(search, it[0].channelId, it[0].maxResults).flatMap { response_1 -> list.addAll(response_1)
                if (settingSize < 2) Single.just(list)
                else {
            searchDataSource.getSearchList(search, it[1].channelId, it[1].maxResults).flatMap { response_2 -> list.addAll(response_2)
                if (settingSize < 3) Single.just(list)
                else {
            searchDataSource.getSearchList(search, it[2].channelId, it[2].maxResults).flatMap { response_3 -> list.addAll(response_3)
                if (settingSize < 4) Single.just(list)
                else {
            searchDataSource.getSearchList(search, it[3].channelId, it[3].maxResults).flatMap { response_4 -> list.addAll(response_4)
                if (settingSize < 5) Single.just(list)
                else {
            searchDataSource.getSearchList(search, it[4].channelId, it[4].maxResults).flatMap { response_5 -> list.addAll(response_5)
                if (settingSize < 6) Single.just(list)
                else {
            searchDataSource.getSearchList(search, it[5].channelId, it[5].maxResults).flatMap { response_6 -> list.addAll(response_6)
                if (settingSize < 7) Single.just(list)
                else {
            searchDataSource.getSearchList(search, it[6].channelId, it[6].maxResults).flatMap { response_7 -> list.addAll(response_7)
                if (settingSize < 8) Single.just(list)
                else {
            searchDataSource.getSearchList(search, it[7].channelId, it[7].maxResults).flatMap { response_8 -> list.addAll(response_8)
                if (settingSize < 9) Single.just(list)
                else {
            searchDataSource.getSearchList(search, it[8].channelId, it[8].maxResults).flatMap { response_9 -> list.addAll(response_9)
                Single.just(list)
            }}}}}}}}}}}}}}}}}
        }.flatMap { searchList ->
            hidingDataSource.getHidingList().flatMap { hidingList ->
                searchHistoryDataSource.insertSearchHistory(search).toSingleDefault(getResultSearchList(searchList, hidingList))
            }
        }.map { if (it.isEmpty()) throw ListEmptyException("검색 결과가 없습니다") else it }
    }


    override fun deleteAllSearch(): Completable {
        return searchDataSource.deleteAllPlaylist()
    }

    private fun getResultSearchList(searchList: List<YoutubeData>, hidingList: List<YoutubeData>): List<YoutubeData> {
        val list = ArrayList<YoutubeData>()

        searchList.map { searchItem ->
            var isHidden = false

            hidingList.forEach { hiding ->
                if (hiding.videoId == searchItem.videoId)
                    isHidden = true
            }

            if (!isHidden)
                list.add(searchItem)
        }

        return list
    }
}