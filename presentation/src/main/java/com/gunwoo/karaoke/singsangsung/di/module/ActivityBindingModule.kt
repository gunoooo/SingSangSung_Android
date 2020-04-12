package com.gunwoo.karaoke.singsangsung.di.module

import com.gunwoo.karaoke.singsangsung.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.gunwoo.karaoke.singsangsung.di.scope.PerActivity
import com.gunwoo.karaoke.singsangsung.ui.player.PlayerActivity
import com.gunwoo.karaoke.singsangsung.ui.splash.SplashActivity

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
    abstract fun bindingPlayerActivity(): PlayerActivity
}