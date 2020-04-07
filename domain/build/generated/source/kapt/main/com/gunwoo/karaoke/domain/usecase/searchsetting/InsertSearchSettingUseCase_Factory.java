package com.gunwoo.karaoke.domain.usecase.searchsetting;

import com.gunwoo.karaoke.domain.repository.SearchSettingRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class InsertSearchSettingUseCase_Factory
    implements Factory<InsertSearchSettingUseCase> {
  private final Provider<SearchSettingRepository> searchSettingRepositoryProvider;

  public InsertSearchSettingUseCase_Factory(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    this.searchSettingRepositoryProvider = searchSettingRepositoryProvider;
  }

  @Override
  public InsertSearchSettingUseCase get() {
    return new InsertSearchSettingUseCase(searchSettingRepositoryProvider.get());
  }

  public static InsertSearchSettingUseCase_Factory create(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    return new InsertSearchSettingUseCase_Factory(searchSettingRepositoryProvider);
  }

  public static InsertSearchSettingUseCase newInsertSearchSettingUseCase(
      SearchSettingRepository searchSettingRepository) {
    return new InsertSearchSettingUseCase(searchSettingRepository);
  }
}
