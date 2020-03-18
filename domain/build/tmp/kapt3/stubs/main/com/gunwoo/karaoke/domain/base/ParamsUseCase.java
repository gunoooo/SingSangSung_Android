package com.gunwoo.karaoke.domain.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b&\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0006\b\u0001\u0010\u0002 \u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/gunwoo/karaoke/domain/base/ParamsUseCase;", "Params", "T", "", "()V", "buildUseCaseObservable", "params", "(Ljava/lang/Object;)Ljava/lang/Object;", "domain"})
public abstract class ParamsUseCase<Params extends java.lang.Object, T extends java.lang.Object> {
    
    public abstract T buildUseCaseObservable(Params params);
    
    public ParamsUseCase() {
        super();
    }
}