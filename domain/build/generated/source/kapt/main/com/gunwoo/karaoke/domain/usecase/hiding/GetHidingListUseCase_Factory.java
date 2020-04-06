package com.gunwoo.karaoke.domain.usecase.hiding;

import com.gunwoo.karaoke.domain.repository.HidingRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetHidingListUseCase_Factory implements Factory<GetHidingListUseCase> {
  private final Provider<HidingRepository> hidingRepositoryProvider;

  public GetHidingListUseCase_Factory(Provider<HidingRepository> hidingRepositoryProvider) {
    this.hidingRepositoryProvider = hidingRepositoryProvider;
  }

  @Override
  public GetHidingListUseCase get() {
    return new GetHidingListUseCase(hidingRepositoryProvider.get());
  }

  public static GetHidingListUseCase_Factory create(
      Provider<HidingRepository> hidingRepositoryProvider) {
    return new GetHidingListUseCase_Factory(hidingRepositoryProvider);
  }

  public static GetHidingListUseCase newGetHidingListUseCase(HidingRepository hidingRepository) {
    return new GetHidingListUseCase(hidingRepository);
  }
}
