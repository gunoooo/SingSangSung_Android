package com.gunwoo.karaoke.domain.usecase.searchsetting;

import com.gunwoo.karaoke.domain.repository.SearchSettingRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UpdateSearchSettingListUseCase_Factory
    implements Factory<UpdateSearchSettingListUseCase> {
  private final Provider<SearchSettingRepository> searchSettingRepositoryProvider;

  public UpdateSearchSettingListUseCase_Factory(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    this.searchSettingRepositoryProvider = searchSettingRepositoryProvider;
  }

  @Override
  public UpdateSearchSettingListUseCase get() {
    return new UpdateSearchSettingListUseCase(searchSettingRepositoryProvider.get());
  }

  public static UpdateSearchSettingListUseCase_Factory create(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    return new UpdateSearchSettingListUseCase_Factory(searchSettingRepositoryProvider);
  }

  public static UpdateSearchSettingListUseCase newUpdateSearchSettingListUseCase(
      SearchSettingRepository searchSettingRepository) {
    return new UpdateSearchSettingListUseCase(searchSettingRepository);
  }
}
