package com.gunwoo.karaoke.singsangsung.di.module

import com.gunwoo.karaoke.singsangsung.view.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.gunwoo.karaoke.singsangsung.di.scope.PerFragment
import com.gunwoo.karaoke.singsangsung.view.dialog.ChannelAddDialog

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
    abstract fun bindingPlayerPlaylistFragment(): PlayerPlaylistFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingPlayerControllerFragment(): PlayerControllerFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingSearchSettingFragment(): SearchSettingFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingChannelAddDialog(): ChannelAddDialog
}