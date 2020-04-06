package com.gunwoo.karaoke.domain.usecase.hiding;

import com.gunwoo.karaoke.domain.repository.HidingRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class InsertHidingUseCase_Factory implements Factory<InsertHidingUseCase> {
  private final Provider<HidingRepository> hidingRepositoryProvider;

  public InsertHidingUseCase_Factory(Provider<HidingRepository> hidingRepositoryProvider) {
    this.hidingRepositoryProvider = hidingRepositoryProvider;
  }

  @Override
  public InsertHidingUseCase get() {
    return new InsertHidingUseCase(hidingRepositoryProvider.get());
  }

  public static InsertHidingUseCase_Factory create(
      Provider<HidingRepository> hidingRepositoryProvider) {
    return new InsertHidingUseCase_Factory(hidingRepositoryProvider);
  }

  public static InsertHidingUseCase newInsertHidingUseCase(HidingRepository hidingRepository) {
    return new InsertHidingUseCase(hidingRepository);
  }
}
