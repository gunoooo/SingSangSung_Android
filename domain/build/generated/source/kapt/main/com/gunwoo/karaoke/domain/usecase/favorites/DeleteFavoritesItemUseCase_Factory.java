package com.gunwoo.karaoke.domain.usecase.favorites;

import com.gunwoo.karaoke.domain.repository.FavoritesRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DeleteFavoritesItemUseCase_Factory
    implements Factory<DeleteFavoritesItemUseCase> {
  private final Provider<FavoritesRepository> favoritesRepositoryProvider;

  public DeleteFavoritesItemUseCase_Factory(
      Provider<FavoritesRepository> favoritesRepositoryProvider) {
    this.favoritesRepositoryProvider = favoritesRepositoryProvider;
  }

  @Override
  public DeleteFavoritesItemUseCase get() {
    return new DeleteFavoritesItemUseCase(favoritesRepositoryProvider.get());
  }

  public static DeleteFavoritesItemUseCase_Factory create(
      Provider<FavoritesRepository> favoritesRepositoryProvider) {
    return new DeleteFavoritesItemUseCase_Factory(favoritesRepositoryProvider);
  }

  public static DeleteFavoritesItemUseCase newDeleteFavoritesItemUseCase(
      FavoritesRepository favoritesRepository) {
    return new DeleteFavoritesItemUseCase(favoritesRepository);
  }
}
