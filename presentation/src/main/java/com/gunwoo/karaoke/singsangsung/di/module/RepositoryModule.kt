package com.gunwoo.karaoke.singsangsung.di.module

import com.gunwoo.karaoke.data.repository.SearchRepositoryImpl
import com.gunwoo.karaoke.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository = searchRepositoryImpl
}