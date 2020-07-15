package com.gunwoo.karaoke.domain.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/gunwoo/karaoke/domain/repository/ExtractRepository;", "", "extract", "Lio/reactivex/Single;", "", "videoId", "domain"})
public abstract interface ExtractRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<java.lang.String> extract(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId);
}