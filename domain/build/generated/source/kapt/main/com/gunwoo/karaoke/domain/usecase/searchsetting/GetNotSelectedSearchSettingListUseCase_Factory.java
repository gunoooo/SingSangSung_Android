package com.gunwoo.karaoke.domain.usecase.searchsetting;

import com.gunwoo.karaoke.domain.repository.SearchSettingRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetNotSelectedSearchSettingListUseCase_Factory
    implements Factory<GetNotSelectedSearchSettingListUseCase> {
  private final Provider<SearchSettingRepository> searchSettingRepositoryProvider;

  public GetNotSelectedSearchSettingListUseCase_Factory(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    this.searchSettingRepositoryProvider = searchSettingRepositoryProvider;
  }

  @Override
  public GetNotSelectedSearchSettingListUseCase get() {
    return new GetNotSelectedSearchSettingListUseCase(searchSettingRepositoryProvider.get());
  }

  public static GetNotSelectedSearchSettingListUseCase_Factory create(
      Provider<SearchSettingRepository> searchSettingRepositoryProvider) {
    return new GetNotSelectedSearchSettingListUseCase_Factory(searchSettingRepositoryProvider);
  }

  public static GetNotSelectedSearchSettingListUseCase newGetNotSelectedSearchSettingListUseCase(
      SearchSettingRepository searchSettingRepository) {
    return new GetNotSelectedSearchSettingListUseCase(searchSettingRepository);
  }
}
