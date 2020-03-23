package com.gunwoo.karaoke.domain.usecase;

import com.gunwoo.karaoke.domain.repository.FavoritesRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class InsertFavoritesUseCase_Factory implements Factory<InsertFavoritesUseCase> {
  private final Provider<FavoritesRepository> favoritesRepositoryProvider;

  public InsertFavoritesUseCase_Factory(Provider<FavoritesRepository> favoritesRepositoryProvider) {
    this.favoritesRepositoryProvider = favoritesRepositoryProvider;
  }

  @Override
  public InsertFavoritesUseCase get() {
    return new InsertFavoritesUseCase(favoritesRepositoryProvider.get());
  }

  public static InsertFavoritesUseCase_Factory create(
      Provider<FavoritesRepository> favoritesRepositoryProvider) {
    return new InsertFavoritesUseCase_Factory(favoritesRepositoryProvider);
  }

  public static InsertFavoritesUseCase newInsertFavoritesUseCase(
      FavoritesRepository favoritesRepository) {
    return new InsertFavoritesUseCase(favoritesRepository);
  }
}
