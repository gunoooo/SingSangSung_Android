package com.gunwoo.karaoke.singsangsung.di.module

import com.gunwoo.karaoke.singsangsung.streaming.service.SingSangSungFirebaseMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector




@Module
abstract class ServiceBindingModule {

    @ContributesAndroidInjector
    abstract fun bindingSingSangSungFirebaseMessagingService(): SingSangSungFirebaseMessagingService
}