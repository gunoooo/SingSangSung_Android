package com.gunwoo.karaoke.singsangsung.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.gunwoo.karaoke.singsangsung.di.scope.PerFragment
import com.gunwoo.karaoke.singsangsung.ui.favorites.FavoritesAddDialog
import com.gunwoo.karaoke.singsangsung.ui.favorites.FavoritesBottomSheetDialog
import com.gunwoo.karaoke.singsangsung.ui.favorites.FavoritesFixDialog
import com.gunwoo.karaoke.singsangsung.ui.favorites.FavoritesFragment
import com.gunwoo.karaoke.singsangsung.ui.home.HomeFragment
import com.gunwoo.karaoke.singsangsung.ui.list.ListFragment
import com.gunwoo.karaoke.singsangsung.ui.player.PlayerControllerFragment
import com.gunwoo.karaoke.singsangsung.ui.player.PlayerPlaylistFragment
import com.gunwoo.karaoke.singsangsung.ui.record.RecordFragment
import com.gunwoo.karaoke.singsangsung.ui.search.SearchFragment
import com.gunwoo.karaoke.singsangsung.ui.search.SearchSettingFragment
import com.gunwoo.karaoke.singsangsung.ui.theme.ThemeFragment
import com.gunwoo.karaoke.singsangsung.ui.search.SearchChannelAddDialog

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
    abstract fun bindingChannelAddDialog(): SearchChannelAddDialog

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingFavoritesBottomSheetDialog(): FavoritesBottomSheetDialog

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingFavoritesAddDialog(): FavoritesAddDialog

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingFavoritesFixDialog(): FavoritesFixDialog
}