package com.gunwoo.karaoke.domain.usecase.searchsetting;

import com.gunwoo.karaoke.domain.repository.SearchSettingRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DeleteSearchSettingUseCase_Factory
    implements Factory<DeleteSearchSettingUseCase> {
  private final Provider<SearchSettingRepository> searchSettingRepositoryProvider;

  public DeleteSearchSettingUseCase_Factory(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    this.searchSettingRepositoryProvider = searchSettingRepositoryProvider;
  }

  @Override
  public DeleteSearchSettingUseCase get() {
    return new DeleteSearchSettingUseCase(searchSettingRepositoryProvider.get());
  }

  public static DeleteSearchSettingUseCase_Factory create(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    return new DeleteSearchSettingUseCase_Factory(searchSettingRepositoryProvider);
  }

  public static DeleteSearchSettingUseCase newDeleteSearchSettingUseCase(
      SearchSettingRepository searchSettingRepository) {
    return new DeleteSearchSettingUseCase(searchSettingRepository);
  }
}
