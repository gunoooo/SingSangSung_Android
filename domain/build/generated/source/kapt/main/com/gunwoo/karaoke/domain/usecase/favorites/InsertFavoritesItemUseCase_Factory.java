package com.gunwoo.karaoke.domain.usecase.favorites;

import com.gunwoo.karaoke.domain.repository.FavoritesRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class InsertFavoritesItemUseCase_Factory
    implements Factory<InsertFavoritesItemUseCase> {
  private final Provider<FavoritesRepository> favoritesRepositoryProvider;

  public InsertFavoritesItemUseCase_Factory(
      Provider<FavoritesRepository> favoritesRepositoryProvider) {
    this.favoritesRepositoryProvider = favoritesRepositoryProvider;
  }

  @Override
  public InsertFavoritesItemUseCase get() {
    return new InsertFavoritesItemUseCase(favoritesRepositoryProvider.get());
  }

  public static InsertFavoritesItemUseCase_Factory create(
      Provider<FavoritesRepository> favoritesRepositoryProvider) {
    return new InsertFavoritesItemUseCase_Factory(favoritesRepositoryProvider);
  }

  public static InsertFavoritesItemUseCase newInsertFavoritesItemUseCase(
      FavoritesRepository favoritesRepository) {
    return new InsertFavoritesItemUseCase(favoritesRepository);
  }
}
