package com.gunwoo.karaoke.domain.usecase;

import com.gunwoo.karaoke.domain.repository.HidingRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DeleteHidingUseCase_Factory implements Factory<DeleteHidingUseCase> {
  private final Provider<HidingRepository> hidingRepositoryProvider;

  public DeleteHidingUseCase_Factory(Provider<HidingRepository> hidingRepositoryProvider) {
    this.hidingRepositoryProvider = hidingRepositoryProvider;
  }

  @Override
  public DeleteHidingUseCase get() {
    return new DeleteHidingUseCase(hidingRepositoryProvider.get());
  }

  public static DeleteHidingUseCase_Factory create(
      Provider<HidingRepository> hidingRepositoryProvider) {
    return new DeleteHidingUseCase_Factory(hidingRepositoryProvider);
  }

  public static DeleteHidingUseCase newDeleteHidingUseCase(HidingRepository hidingRepository) {
    return new DeleteHidingUseCase(hidingRepository);
  }
}
