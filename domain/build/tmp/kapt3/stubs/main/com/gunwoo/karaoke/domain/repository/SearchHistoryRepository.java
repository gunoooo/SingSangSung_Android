package com.gunwoo.karaoke.domain.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\bH&\u00a8\u0006\n"}, d2 = {"Lcom/gunwoo/karaoke/domain/repository/SearchHistoryRepository;", "", "deleteAll", "Lio/reactivex/Completable;", "deleteSearchHistory", "search", "", "getSearchHistoryList", "Lio/reactivex/Single;", "", "domain"})
public abstract interface SearchHistoryRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<java.util.List<java.lang.String>> getSearchHistoryList();
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable deleteSearchHistory(@org.jetbrains.annotations.NotNull()
    java.lang.String search);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable deleteAll();
}