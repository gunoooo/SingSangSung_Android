package com.gunwoo.karaoke.domain.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/gunwoo/karaoke/domain/usecase/GetRecentListUseCase;", "Lcom/gunwoo/karaoke/domain/base/BaseUseCase;", "Lio/reactivex/Single;", "", "Lcom/gunwoo/karaoke/domain/model/YoutubeData;", "recentRepository", "Lcom/gunwoo/karaoke/domain/repository/RecentRepository;", "(Lcom/gunwoo/karaoke/domain/repository/RecentRepository;)V", "buildUseCaseObservable", "domain"})
public final class GetRecentListUseCase extends com.gunwoo.karaoke.domain.base.BaseUseCase<io.reactivex.Single<java.util.List<? extends com.gunwoo.karaoke.domain.model.YoutubeData>>> {
    private final com.gunwoo.karaoke.domain.repository.RecentRepository recentRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<java.util.List<com.gunwoo.karaoke.domain.model.YoutubeData>> buildUseCaseObservable() {
        return null;
    }
    
    @javax.inject.Inject()
    public GetRecentListUseCase(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.repository.RecentRepository recentRepository) {
        super();
    }
}