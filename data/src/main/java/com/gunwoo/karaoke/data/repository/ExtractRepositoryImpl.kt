package com.gunwoo.karaoke.data.repository

import com.gunwoo.karaoke.data.datasource.ExtractDataSource
import com.gunwoo.karaoke.domain.repository.ExtractRepository
import io.reactivex.Single
import javax.inject.Inject

class ExtractRepositoryImpl @Inject constructor(
    private val extractDataSource: ExtractDataSource
) : ExtractRepository {

    override fun extract(videoId: String): Single<String> {
        return Single.fromObservable(extractDataSource.extract(videoId))
    }
}