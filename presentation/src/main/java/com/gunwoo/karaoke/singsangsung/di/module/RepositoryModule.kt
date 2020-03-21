package com.gunwoo.karaoke.singsangsung.di.module

import com.gunwoo.karaoke.data.repository.PlaylistRepositoryImpl
import com.gunwoo.karaoke.data.repository.RecordRepositoryImpl
import com.gunwoo.karaoke.data.repository.SearchRepositoryImpl
import com.gunwoo.karaoke.domain.repository.PlaylistRepository
import com.gunwoo.karaoke.domain.repository.RecordRepository
import com.gunwoo.karaoke.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository = searchRepositoryImpl

    @Singleton
    @Provides
    fun providePlaylistRepository(playlistRepositoryImpl: PlaylistRepositoryImpl): PlaylistRepository = playlistRepositoryImpl

    @Singleton
    @Provides
    fun provideRecordRepository(recordRepositoryImpl: RecordRepositoryImpl): RecordRepository = recordRepositoryImpl
}