package com.gunwoo.karaoke.domain.usecase.searchhistory;

import com.gunwoo.karaoke.domain.repository.SearchHistoryRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DeleteSearchHistoryUseCase_Factory
    implements Factory<DeleteSearchHistoryUseCase> {
  private final Provider<SearchHistoryRepository> searchHistoryRepositoryProvider;

  public DeleteSearchHistoryUseCase_Factory(
      Provider<SearchHistoryRepository> searchHistoryRepositoryProvider) {
    this.searchHistoryRepositoryProvider = searchHistoryRepositoryProvider;
  }

  @Override
  public DeleteSearchHistoryUseCase get() {
    return new DeleteSearchHistoryUseCase(searchHistoryRepositoryProvider.get());
  }

  public static DeleteSearchHistoryUseCase_Factory create(
      Provider<SearchHistoryRepository> searchHistoryRepositoryProvider) {
    return new DeleteSearchHistoryUseCase_Factory(searchHistoryRepositoryProvider);
  }

  public static DeleteSearchHistoryUseCase newDeleteSearchHistoryUseCase(
      SearchHistoryRepository searchHistoryRepository) {
    return new DeleteSearchHistoryUseCase(searchHistoryRepository);
  }
}
