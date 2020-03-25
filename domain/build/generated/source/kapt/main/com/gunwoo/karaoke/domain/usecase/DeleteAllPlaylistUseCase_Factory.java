package com.gunwoo.karaoke.domain.usecase;

import com.gunwoo.karaoke.domain.repository.PlaylistRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DeleteAllPlaylistUseCase_Factory implements Factory<DeleteAllPlaylistUseCase> {
  private final Provider<PlaylistRepository> playlistRepositoryProvider;

  public DeleteAllPlaylistUseCase_Factory(Provider<PlaylistRepository> playlistRepositoryProvider) {
    this.playlistRepositoryProvider = playlistRepositoryProvider;
  }

  @Override
  public DeleteAllPlaylistUseCase get() {
    return new DeleteAllPlaylistUseCase(playlistRepositoryProvider.get());
  }

  public static DeleteAllPlaylistUseCase_Factory create(
      Provider<PlaylistRepository> playlistRepositoryProvider) {
    return new DeleteAllPlaylistUseCase_Factory(playlistRepositoryProvider);
  }

  public static DeleteAllPlaylistUseCase newDeleteAllPlaylistUseCase(
      PlaylistRepository playlistRepository) {
    return new DeleteAllPlaylistUseCase(playlistRepository);
  }
}
