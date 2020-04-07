package com.gunwoo.karaoke.domain.usecase.searchsetting;

import com.gunwoo.karaoke.domain.repository.SearchSettingRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetSearchSettingListUseCase_Factory
    implements Factory<GetSearchSettingListUseCase> {
  private final Provider<SearchSettingRepository> searchSettingRepositoryProvider;

  public GetSearchSettingListUseCase_Factory(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    this.searchSettingRepositoryProvider = searchSettingRepositoryProvider;
  }

  @Override
  public GetSearchSettingListUseCase get() {
    return new GetSearchSettingListUseCase(searchSettingRepositoryProvider.get());
  }

  public static GetSearchSettingListUseCase_Factory create(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    return new GetSearchSettingListUseCase_Factory(searchSettingRepositoryProvider);
  }

  public static GetSearchSettingListUseCase newGetSearchSettingListUseCase(
      SearchSettingRepository searchSettingRepository) {
    return new GetSearchSettingListUseCase(searchSettingRepository);
  }
}
