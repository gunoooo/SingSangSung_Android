package com.gunwoo.karaoke.domain.usecase.favorites;

import com.gunwoo.karaoke.domain.repository.FavoritesRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DeleteFavoritesUseCase_Factory implements Factory<DeleteFavoritesUseCase> {
  private final Provider<FavoritesRepository> favoritesRepositoryProvider;

  public DeleteFavoritesUseCase_Factory(Provider<FavoritesRepository> favoritesRepositoryProvider) {
    this.favoritesRepositoryProvider = favoritesRepositoryProvider;
  }

  @Override
  public DeleteFavoritesUseCase get() {
    return new DeleteFavoritesUseCase(favoritesRepositoryProvider.get());
  }

  public static DeleteFavoritesUseCase_Factory create(
      Provider<FavoritesRepository> favoritesRepositoryProvider) {
    return new DeleteFavoritesUseCase_Factory(favoritesRepositoryProvider);
  }

  public static DeleteFavoritesUseCase newDeleteFavoritesUseCase(
      FavoritesRepository favoritesRepository) {
    return new DeleteFavoritesUseCase(favoritesRepository);
  }
}
