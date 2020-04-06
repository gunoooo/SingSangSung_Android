package com.gunwoo.karaoke.domain.usecase.searchhistory;

import com.gunwoo.karaoke.domain.repository.SearchHistoryRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DeleteAllSearchHistoryUseCase_Factory
    implements Factory<DeleteAllSearchHistoryUseCase> {
  private final Provider<SearchHistoryRepository> searchHistoryRepositoryProvider;

  public DeleteAllSearchHistoryUseCase_Factory(
      Provider<SearchHistoryRepository> searchHistoryRepositoryProvider) {
    this.searchHistoryRepositoryProvider = searchHistoryRepositoryProvider;
  }

  @Override
  public DeleteAllSearchHistoryUseCase get() {
    return new DeleteAllSearchHistoryUseCase(searchHistoryRepositoryProvider.get());
  }

  public static DeleteAllSearchHistoryUseCase_Factory create(
      Provider<SearchHistoryRepository> searchHistoryRepositoryProvider) {
    return new DeleteAllSearchHistoryUseCase_Factory(searchHistoryRepositoryProvider);
  }

  public static DeleteAllSearchHistoryUseCase newDeleteAllSearchHistoryUseCase(
      SearchHistoryRepository searchHistoryRepository) {
    return new DeleteAllSearchHistoryUseCase(searchHistoryRepository);
  }
}
