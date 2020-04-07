package com.gunwoo.karaoke.domain.usecase.searchsetting;

import com.gunwoo.karaoke.domain.repository.SearchSettingRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UpdateSearchSettingUseCase_Factory
    implements Factory<UpdateSearchSettingUseCase> {
  private final Provider<SearchSettingRepository> searchSettingRepositoryProvider;

  public UpdateSearchSettingUseCase_Factory(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    this.searchSettingRepositoryProvider = searchSettingRepositoryProvider;
  }

  @Override
  public UpdateSearchSettingUseCase get() {
    return new UpdateSearchSettingUseCase(searchSettingRepositoryProvider.get());
  }

  public static UpdateSearchSettingUseCase_Factory create(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    return new UpdateSearchSettingUseCase_Factory(searchSettingRepositoryProvider);
  }

  public static UpdateSearchSettingUseCase newUpdateSearchSettingUseCase(
      SearchSettingRepository searchSettingRepository) {
    return new UpdateSearchSettingUseCase(searchSettingRepository);
  }
}
