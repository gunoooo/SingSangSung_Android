package com.gunwoo.karaoke.domain.usecase.favorites;

import com.gunwoo.karaoke.domain.repository.FavoritesRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UpdateFavoritesUseCase_Factory implements Factory<UpdateFavoritesUseCase> {
  private final Provider<FavoritesRepository> favoritesRepositoryProvider;

  public UpdateFavoritesUseCase_Factory(Provider<FavoritesRepository> favoritesRepositoryProvider) {
    this.favoritesRepositoryProvider = favoritesRepositoryProvider;
  }

  @Override
  public UpdateFavoritesUseCase get() {
    return new UpdateFavoritesUseCase(favoritesRepositoryProvider.get());
  }

  public static UpdateFavoritesUseCase_Factory create(
      Provider<FavoritesRepository> favoritesRepositoryProvider) {
    return new UpdateFavoritesUseCase_Factory(favoritesRepositoryProvider);
  }

  public static UpdateFavoritesUseCase newUpdateFavoritesUseCase(
      FavoritesRepository favoritesRepository) {
    return new UpdateFavoritesUseCase(favoritesRepository);
  }
}
