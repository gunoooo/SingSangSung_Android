package com.gunwoo.karaoke.domain.usecase.favorites;

import com.gunwoo.karaoke.domain.repository.FavoritesRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetFavoritesListUseCase_Factory implements Factory<GetFavoritesListUseCase> {
  private final Provider<FavoritesRepository> favoritesRepositoryProvider;

  public GetFavoritesListUseCase_Factory(
      Provider<FavoritesRepository> favoritesRepositoryProvider) {
    this.favoritesRepositoryProvider = favoritesRepositoryProvider;
  }

  @Override
  public GetFavoritesListUseCase get() {
    return new GetFavoritesListUseCase(favoritesRepositoryProvider.get());
  }

  public static GetFavoritesListUseCase_Factory create(
      Provider<FavoritesRepository> favoritesRepositoryProvider) {
    return new GetFavoritesListUseCase_Factory(favoritesRepositoryProvider);
  }

  public static GetFavoritesListUseCase newGetFavoritesListUseCase(
      FavoritesRepository favoritesRepository) {
    return new GetFavoritesListUseCase(favoritesRepository);
  }
}
