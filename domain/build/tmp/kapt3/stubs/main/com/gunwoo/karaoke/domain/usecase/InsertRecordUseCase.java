package com.gunwoo.karaoke.domain.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\tB\u000f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/gunwoo/karaoke/domain/usecase/InsertRecordUseCase;", "Lcom/gunwoo/karaoke/domain/base/ParamsUseCase;", "Lcom/gunwoo/karaoke/domain/usecase/InsertRecordUseCase$Params;", "Lio/reactivex/Completable;", "recordRepository", "Lcom/gunwoo/karaoke/domain/repository/RecordRepository;", "(Lcom/gunwoo/karaoke/domain/repository/RecordRepository;)V", "buildUseCaseObservable", "params", "Params", "domain"})
public final class InsertRecordUseCase extends com.gunwoo.karaoke.domain.base.ParamsUseCase<com.gunwoo.karaoke.domain.usecase.InsertRecordUseCase.Params, io.reactivex.Completable> {
    private final com.gunwoo.karaoke.domain.repository.RecordRepository recordRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable buildUseCaseObservable(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.usecase.InsertRecordUseCase.Params params) {
        return null;
    }
    
    @javax.inject.Inject()
    public InsertRecordUseCase(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.repository.RecordRepository recordRepository) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lcom/gunwoo/karaoke/domain/usecase/InsertRecordUseCase$Params;", "", "title", "", "thumbnail", "file", "Ljava/io/File;", "(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V", "getFile", "()Ljava/io/File;", "getThumbnail", "()Ljava/lang/String;", "getTitle", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "domain"})
    public static final class Params {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String thumbnail = null;
        @org.jetbrains.annotations.NotNull()
        private final java.io.File file = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getThumbnail() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.io.File getFile() {
            return null;
        }
        
        public Params(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String thumbnail, @org.jetbrains.annotations.NotNull()
        java.io.File file) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.io.File component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.gunwoo.karaoke.domain.usecase.InsertRecordUseCase.Params copy(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String thumbnail, @org.jetbrains.annotations.NotNull()
        java.io.File file) {
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