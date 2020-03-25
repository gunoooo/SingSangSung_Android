package com.gunwoo.karaoke.domain.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/gunwoo/karaoke/domain/usecase/DeleteAllPlaylistUseCase;", "Lcom/gunwoo/karaoke/domain/base/BaseUseCase;", "Lio/reactivex/Completable;", "playlistRepository", "Lcom/gunwoo/karaoke/domain/repository/PlaylistRepository;", "(Lcom/gunwoo/karaoke/domain/repository/PlaylistRepository;)V", "buildUseCaseObservable", "domain"})
public final class DeleteAllPlaylistUseCase extends com.gunwoo.karaoke.domain.base.BaseUseCase<io.reactivex.Completable> {
    private final com.gunwoo.karaoke.domain.repository.PlaylistRepository playlistRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable buildUseCaseObservable() {
        return null;
    }
    
    @javax.inject.Inject()
    public DeleteAllPlaylistUseCase(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.repository.PlaylistRepository playlistRepository) {
        super();
    }
}