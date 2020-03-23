package com.gunwoo.karaoke.singsangsung.di.module

import com.gunwoo.karaoke.singsangsung.view.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.gunwoo.karaoke.singsangsung.di.scope.PerFragment

@Module
abstract class FragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingFavoritesFragment(): FavoritesFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingHomeFragment(): HomeFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingListFragment(): ListFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingRecordFragment(): RecordFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingSearchFragment(): SearchFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingThemeFragment(): ThemeFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingOfflineListFragment(): OfflineListFragment
}