package com.gunwoo.karaoke.domain.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001*B3\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tBO\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\rJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\bH\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003Jc\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u00d6\u0003J\t\u0010\'\u001a\u00020(H\u00d6\u0001J\t\u0010)\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000f\u00a8\u0006+"}, d2 = {"Lcom/gunwoo/karaoke/domain/model/YoutubeData;", "Ljava/io/Serializable;", "videoId", "", "thumbnailUrl", "videoTitle", "channelTitle", "state", "Lcom/gunwoo/karaoke/domain/model/YoutubeData$State;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/gunwoo/karaoke/domain/model/YoutubeData$State;)V", "playlistId", "channelId", "search", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/gunwoo/karaoke/domain/model/YoutubeData$State;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannelId", "()Ljava/lang/String;", "getChannelTitle", "getPlaylistId", "getSearch", "getState", "()Lcom/gunwoo/karaoke/domain/model/YoutubeData$State;", "setState", "(Lcom/gunwoo/karaoke/domain/model/YoutubeData$State;)V", "getThumbnailUrl", "getVideoId", "getVideoTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "", "hashCode", "", "toString", "State", "domain"})
public final class YoutubeData implements java.io.Serializable {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String videoId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String thumbnailUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String videoTitle = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String channelTitle = null;
    @org.jetbrains.annotations.NotNull()
    private com.gunwoo.karaoke.domain.model.YoutubeData.State state;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String playlistId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String channelId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String search = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getVideoId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getThumbnailUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVideoTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getChannelTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gunwoo.karaoke.domain.model.YoutubeData.State getState() {
        return null;
    }
    
    public final void setState(@org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.YoutubeData.State p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPlaylistId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getChannelId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSearch() {
        return null;
    }
    
    public YoutubeData(@org.jetbrains.annotations.Nullable()
    java.lang.String videoId, @org.jetbrains.annotations.Nullable()
    java.lang.String thumbnailUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String videoTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String channelTitle, @org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.YoutubeData.State state, @org.jetbrains.annotations.Nullable()
    java.lang.String playlistId, @org.jetbrains.annotations.Nullable()
    java.lang.String channelId, @org.jetbrains.annotations.Nullable()
    java.lang.String search) {
        super();
    }
    
    public YoutubeData(@org.jetbrains.annotations.Nullable()
    java.lang.String videoId, @org.jetbrains.annotations.Nullable()
    java.lang.String thumbnailUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String videoTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String channelTitle, @org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.YoutubeData.State state) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gunwoo.karaoke.domain.model.YoutubeData.State component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gunwoo.karaoke.domain.model.YoutubeData copy(@org.jetbrains.annotations.Nullable()
    java.lang.String videoId, @org.jetbrains.annotations.Nullable()
    java.lang.String thumbnailUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String videoTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String channelTitle, @org.jetbrains.annotations.NotNull()
    com.gunwoo.karaoke.domain.model.YoutubeData.State state, @org.jetbrains.annotations.Nullable()
    java.lang.String playlistId, @org.jetbrains.annotations.Nullable()
    java.lang.String channelId, @org.jetbrains.annotations.Nullable()
    java.lang.String search) {
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
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/gunwoo/karaoke/domain/model/YoutubeData$State;", "", "(Ljava/lang/String;I)V", "NONE", "FAVORITES", "HIDING", "domain"})
    public static enum State {
        /*public static final*/ NONE /* = new NONE() */,
        /*public static final*/ FAVORITES /* = new FAVORITES() */,
        /*public static final*/ HIDING /* = new HIDING() */;
        
        State() {
        }
    }
}