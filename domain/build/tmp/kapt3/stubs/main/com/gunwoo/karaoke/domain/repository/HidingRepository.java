package com.gunwoo.karaoke.domain.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u0007H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\n"}, d2 = {"Lcom/gunwoo/karaoke/domain/repository/HidingRepository;", "", "deleteHiding", "Lio/reactivex/Completable;", "youtubeData", "Lcom/gunwoo/karaoke/domain/model/YoutubeData;", "getHidingList", "Lio/reactivex/Single;", "", "insertHiding", "domain"})
public abstract interface HidingRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<java.util.List<com.gunwoo.karaoke.domain.model.YoutubeData>> getHidingList();
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable insertHiding(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.YoutubeData youtubeData);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable deleteHiding(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.YoutubeData youtubeData);
}