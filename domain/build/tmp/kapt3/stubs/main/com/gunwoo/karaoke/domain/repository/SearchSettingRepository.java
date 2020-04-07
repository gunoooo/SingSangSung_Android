package com.gunwoo.karaoke.domain.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u0007H&J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u0007H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\bH&\u00a8\u0006\u000e"}, d2 = {"Lcom/gunwoo/karaoke/domain/repository/SearchSettingRepository;", "", "deleteSearchSetting", "Lio/reactivex/Completable;", "searchSetting", "Lcom/gunwoo/karaoke/domain/model/SearchSetting;", "getNotSelectedSearchSettingList", "Lio/reactivex/Single;", "", "getSearchSettingList", "insertSearchSetting", "updateSearchSetting", "updateSearchSettingList", "searchSettingList", "domain"})
public abstract interface SearchSettingRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<java.util.List<com.gunwoo.karaoke.domain.model.SearchSetting>> getSearchSettingList();
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<java.util.List<com.gunwoo.karaoke.domain.model.SearchSetting>> getNotSelectedSearchSettingList();
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable insertSearchSetting(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.SearchSetting searchSetting);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable deleteSearchSetting(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.SearchSetting searchSetting);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable updateSearchSetting(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.SearchSetting searchSetting);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable updateSearchSettingList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.gunwoo.karaoke.domain.model.SearchSetting> searchSettingList);
}