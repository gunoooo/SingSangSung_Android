package com.gunwoo.karaoke.domain.usecase.search;

import com.gunwoo.karaoke.domain.repository.SearchRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetSearchListUseCase_Factory implements Factory<GetSearchListUseCase> {
  private final Provider<SearchRepository> searchRepositoryProvider;

  public GetSearchListUseCase_Factory(Provider<SearchRepository> searchRepositoryProvider) {
    this.searchRepositoryProvider = searchRepositoryProvider;
  }

  @Override
  public GetSearchListUseCase get() {
    return new GetSearchListUseCase(searchRepositoryProvider.get());
  }

  public static GetSearchListUseCase_Factory create(
      Provider<SearchRepository> searchRepositoryProvider) {
    return new GetSearchListUseCase_Factory(searchRepositoryProvider);
  }

  public static GetSearchListUseCase newGetSearchListUseCase(SearchRepository searchRepository) {
    return new GetSearchListUseCase(searchRepository);
  }
}
