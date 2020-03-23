package com.gunwoo.karaoke.singsangsung.di.module

import com.gunwoo.karaoke.data.repository.*
import com.gunwoo.karaoke.domain.repository.*
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

    @Singleton
    @Provides
    fun provideDownloadRepository(downloadRepositoryImpl: DownloadRepositoryImpl): DownloadRepository = downloadRepositoryImpl

    @Singleton
    @Provides
    fun provideFavoritesRepository(favoritesRepositoryImpl: FavoritesRepositoryImpl): FavoritesRepository = favoritesRepositoryImpl

    @Singleton
    @Provides
    fun provideHidingRepository(hidingRepositoryImpl: HidingRepositoryImpl): HidingRepository = hidingRepositoryImpl
}