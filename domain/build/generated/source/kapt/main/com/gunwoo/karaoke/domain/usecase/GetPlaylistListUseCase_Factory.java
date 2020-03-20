package com.gunwoo.karaoke.domain.usecase;

import com.gunwoo.karaoke.domain.repository.PlaylistRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetPlaylistListUseCase_Factory implements Factory<GetPlaylistListUseCase> {
  private final Provider<PlaylistRepository> playlistRepositoryProvider;

  public GetPlaylistListUseCase_Factory(Provider<PlaylistRepository> playlistRepositoryProvider) {
    this.playlistRepositoryProvider = playlistRepositoryProvider;
  }

  @Override
  public GetPlaylistListUseCase get() {
    return new GetPlaylistListUseCase(playlistRepositoryProvider.get());
  }

  public static GetPlaylistListUseCase_Factory create(
      Provider<PlaylistRepository> playlistRepositoryProvider) {
    return new GetPlaylistListUseCase_Factory(playlistRepositoryProvider);
  }

  public static GetPlaylistListUseCase newGetPlaylistListUseCase(
      PlaylistRepository playlistRepository) {
    return new GetPlaylistListUseCase(playlistRepository);
  }
}
