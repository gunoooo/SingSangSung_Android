package com.gunwoo.karaoke.domain.usecase.searchhistory;

import com.gunwoo.karaoke.domain.repository.SearchHistoryRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetSearchHistoryListUseCase_Factory
    implements Factory<GetSearchHistoryListUseCase> {
  private final Provider<SearchHistoryRepository> searchHistoryRepositoryProvider;

  public GetSearchHistoryListUseCase_Factory(
      Provider<SearchHistoryRepository> searchHistoryRepositoryProvider) {
    this.searchHistoryRepositoryProvider = searchHistoryRepositoryProvider;
  }

  @Override
  public GetSearchHistoryListUseCase get() {
    return new GetSearchHistoryListUseCase(searchHistoryRepositoryProvider.get());
  }

  public static GetSearchHistoryListUseCase_Factory create(
      Provider<SearchHistoryRepository> searchHistoryRepositoryProvider) {
    return new GetSearchHistoryListUseCase_Factory(searchHistoryRepositoryProvider);
  }

  public static GetSearchHistoryListUseCase newGetSearchHistoryListUseCase(
      SearchHistoryRepository searchHistoryRepository) {
    return new GetSearchHistoryListUseCase(searchHistoryRepository);
  }
}
