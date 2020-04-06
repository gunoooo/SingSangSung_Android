package com.gunwoo.karaoke.domain.usecase.download;

import com.gunwoo.karaoke.domain.repository.DownloadRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DeleteDownloadByVideoIdUseCase_Factory
    implements Factory<DeleteDownloadByVideoIdUseCase> {
  private final Provider<DownloadRepository> downloadRepositoryProvider;

  public DeleteDownloadByVideoIdUseCase_Factory(
      Provider<DownloadRepository> downloadRepositoryProvider) {
    this.downloadRepositoryProvider = downloadRepositoryProvider;
  }

  @Override
  public DeleteDownloadByVideoIdUseCase get() {
    return new DeleteDownloadByVideoIdUseCase(downloadRepositoryProvider.get());
  }

  public static DeleteDownloadByVideoIdUseCase_Factory create(
      Provider<DownloadRepository> downloadRepositoryProvider) {
    return new DeleteDownloadByVideoIdUseCase_Factory(downloadRepositoryProvider);
  }

  public static DeleteDownloadByVideoIdUseCase newDeleteDownloadByVideoIdUseCase(
      DownloadRepository downloadRepository) {
    return new DeleteDownloadByVideoIdUseCase(downloadRepository);
  }
}
