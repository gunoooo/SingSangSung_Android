package com.gunwoo.karaoke.domain.usecase;

import com.gunwoo.karaoke.domain.repository.DownloadRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DeleteDownloadUseCase_Factory implements Factory<DeleteDownloadUseCase> {
  private final Provider<DownloadRepository> downloadRepositoryProvider;

  public DeleteDownloadUseCase_Factory(Provider<DownloadRepository> downloadRepositoryProvider) {
    this.downloadRepositoryProvider = downloadRepositoryProvider;
  }

  @Override
  public DeleteDownloadUseCase get() {
    return new DeleteDownloadUseCase(downloadRepositoryProvider.get());
  }

  public static DeleteDownloadUseCase_Factory create(
      Provider<DownloadRepository> downloadRepositoryProvider) {
    return new DeleteDownloadUseCase_Factory(downloadRepositoryProvider);
  }

  public static DeleteDownloadUseCase newDeleteDownloadUseCase(
      DownloadRepository downloadRepository) {
    return new DeleteDownloadUseCase(downloadRepository);
  }
}
