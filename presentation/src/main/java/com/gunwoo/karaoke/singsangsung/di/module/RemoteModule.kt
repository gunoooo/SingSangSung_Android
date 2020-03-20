package com.gunwoo.karaoke.singsangsung.di.module

import com.gunwoo.karaoke.data.network.remote.PlaylistRemote
import com.gunwoo.karaoke.data.network.remote.SearchRemote
import com.gunwoo.karaoke.data.network.service.PlaylistService
import com.gunwoo.karaoke.data.network.service.SearchService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideSearchRemote(retrofit: Retrofit): SearchRemote =
        SearchRemote(retrofit.create(SearchService::class.java))

    @Singleton
    @Provides
    fun providePlaylistRemote(retrofit: Retrofit): PlaylistRemote =
        PlaylistRemote(retrofit.create(PlaylistService::class.java))
}