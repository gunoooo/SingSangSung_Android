package com.gunwoo.karaoke.singsangsung.di.module

import com.gunwoo.karaoke.singsangsung.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.gunwoo.karaoke.singsangsung.di.scope.PerActivity
import com.gunwoo.karaoke.singsangsung.view.activity.OfflinePlayerActivity
import com.gunwoo.karaoke.singsangsung.view.activity.PlayerActivity
import com.gunwoo.karaoke.singsangsung.view.activity.SplashActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingSplashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingPlayerAcitivity(): PlayerActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingOfflinePlayerActivity(): OfflinePlayerActivity
}