package com.gunwoo.karaoke.domain.usecase.favorites;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\tB\u000f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/gunwoo/karaoke/domain/usecase/favorites/DeleteFavoritesItemUseCase;", "Lcom/gunwoo/karaoke/domain/base/ParamsUseCase;", "Lcom/gunwoo/karaoke/domain/usecase/favorites/DeleteFavoritesItemUseCase$Params;", "Lio/reactivex/Completable;", "favoritesRepository", "Lcom/gunwoo/karaoke/domain/repository/FavoritesRepository;", "(Lcom/gunwoo/karaoke/domain/repository/FavoritesRepository;)V", "buildUseCaseObservable", "params", "Params", "domain"})
public final class DeleteFavoritesItemUseCase extends com.gunwoo.karaoke.domain.base.ParamsUseCase<com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesItemUseCase.Params, io.reactivex.Completable> {
    private final com.gunwoo.karaoke.domain.repository.FavoritesRepository favoritesRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable buildUseCaseObservable(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesItemUseCase.Params params) {
        return null;
    }
    
    @javax.inject.Inject()
    public DeleteFavoritesItemUseCase(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.repository.FavoritesRepository favoritesRepository) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/gunwoo/karaoke/domain/usecase/favorites/DeleteFavoritesItemUseCase$Params;", "", "youtubeData", "Lcom/gunwoo/karaoke/domain/model/YoutubeData;", "favoritesId", "", "(Lcom/gunwoo/karaoke/domain/model/YoutubeData;I)V", "getFavoritesId", "()I", "getYoutubeData", "()Lcom/gunwoo/karaoke/domain/model/YoutubeData;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "domain"})
    public static final class Params {
        @org.jetbrains.annotations.NotNull()
        private final com.gunwoo.karaoke.domain.model.YoutubeData youtubeData = null;
        private final int favoritesId = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final com.gunwoo.karaoke.domain.model.YoutubeData getYoutubeData() {
            return null;
        }
        
        public final int getFavoritesId() {
            return 0;
        }
        
        public Params(@org.jetbrains.annotations.NotNull()
        com.gunwoo.karaoke.domain.model.YoutubeData youtubeData, int favoritesId) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.gunwoo.karaoke.domain.model.YoutubeData component1() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.gunwoo.karaoke.domain.usecase.favorites.DeleteFavoritesItemUseCase.Params copy(@org.jetbrains.annotations.NotNull()
        com.gunwoo.karaoke.domain.model.YoutubeData youtubeData, int favoritesId) {
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