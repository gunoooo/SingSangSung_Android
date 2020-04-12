package com.gunwoo.karaoke.domain.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H&J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bH&J\u001c\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\rH&J\u0018\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H&J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u000fH&\u00a8\u0006\u0015"}, d2 = {"Lcom/gunwoo/karaoke/domain/repository/FavoritesRepository;", "", "deleteFavorites", "Lio/reactivex/Completable;", "id", "", "deleteFavoritesItem", "youtubeData", "Lcom/gunwoo/karaoke/domain/model/YoutubeData;", "favoritesId", "getFavoritesList", "Lio/reactivex/Single;", "", "Lcom/gunwoo/karaoke/domain/model/Favorites;", "videoId", "", "insertFavorites", "favorites", "insertFavoritesItem", "updateFavorites", "title", "domain"})
public abstract interface FavoritesRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<java.util.List<com.gunwoo.karaoke.domain.model.Favorites>> getFavoritesList();
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<java.util.List<com.gunwoo.karaoke.domain.model.Favorites>> getFavoritesList(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable insertFavorites(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.Favorites favorites);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable updateFavorites(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String title);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable deleteFavorites(int id);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable insertFavoritesItem(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.YoutubeData youtubeData, int favoritesId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable deleteFavoritesItem(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.YoutubeData youtubeData, int favoritesId);
}