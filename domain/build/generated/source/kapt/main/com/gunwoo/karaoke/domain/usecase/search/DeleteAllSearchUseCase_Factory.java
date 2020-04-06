package com.gunwoo.karaoke.domain.usecase.search;

import com.gunwoo.karaoke.domain.repository.SearchRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DeleteAllSearchUseCase_Factory implements Factory<DeleteAllSearchUseCase> {
  private final Provider<SearchRepository> searchRepositoryProvider;

  public DeleteAllSearchUseCase_Factory(Provider<SearchRepository> searchRepositoryProvider) {
    this.searchRepositoryProvider = searchRepositoryProvider;
  }

  @Override
  public DeleteAllSearchUseCase get() {
    return new DeleteAllSearchUseCase(searchRepositoryProvider.get());
  }

  public static DeleteAllSearchUseCase_Factory create(
      Provider<SearchRepository> searchRepositoryProvider) {
    return new DeleteAllSearchUseCase_Factory(searchRepositoryProvider);
  }

  public static DeleteAllSearchUseCase newDeleteAllSearchUseCase(
      SearchRepository searchRepository) {
    return new DeleteAllSearchUseCase(searchRepository);
  }
}
