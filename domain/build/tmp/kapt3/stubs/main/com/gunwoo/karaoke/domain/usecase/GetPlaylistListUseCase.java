package com.gunwoo.karaoke.domain.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0001:\u0001\u000bB\u000f\b\u0007\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/gunwoo/karaoke/domain/usecase/GetPlaylistListUseCase;", "Lcom/gunwoo/karaoke/domain/base/ParamsUseCase;", "Lcom/gunwoo/karaoke/domain/usecase/GetPlaylistListUseCase$Params;", "Lio/reactivex/Single;", "", "Lcom/gunwoo/karaoke/domain/model/YoutubeData;", "playlistRepository", "Lcom/gunwoo/karaoke/domain/repository/PlaylistRepository;", "(Lcom/gunwoo/karaoke/domain/repository/PlaylistRepository;)V", "buildUseCaseObservable", "params", "Params", "domain"})
public final class GetPlaylistListUseCase extends com.gunwoo.karaoke.domain.base.ParamsUseCase<com.gunwoo.karaoke.domain.usecase.GetPlaylistListUseCase.Params, io.reactivex.Single<java.util.List<? extends com.gunwoo.karaoke.domain.model.YoutubeData>>> {
    private final com.gunwoo.karaoke.domain.repository.PlaylistRepository playlistRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<java.util.List<com.gunwoo.karaoke.domain.model.YoutubeData>> buildUseCaseObservable(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.usecase.GetPlaylistListUseCase.Params params) {
        return null;
    }
    
    @javax.inject.Inject()
    public GetPlaylistListUseCase(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.repository.PlaylistRepository playlistRepository) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/gunwoo/karaoke/domain/usecase/GetPlaylistListUseCase$Params;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "domain"})
    public static final class Params {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String id = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getId() {
            return null;
        }
        
        public Params(@org.jetbrains.annotations.NotNull()
        java.lang.String id) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.gunwoo.karaoke.domain.usecase.GetPlaylistListUseCase.Params copy(@org.jetbrains.annotations.NotNull()
        java.lang.String id) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
    }
}