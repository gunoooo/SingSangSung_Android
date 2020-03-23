package com.gunwoo.karaoke.domain.usecase;

import com.gunwoo.karaoke.domain.repository.DownloadRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class InsertDownloadUseCase_Factory implements Factory<InsertDownloadUseCase> {
  private final Provider<DownloadRepository> downloadRepositoryProvider;

  public InsertDownloadUseCase_Factory(Provider<DownloadRepository> downloadRepositoryProvider) {
    this.downloadRepositoryProvider = downloadRepositoryProvider;
  }

  @Override
  public InsertDownloadUseCase get() {
    return new InsertDownloadUseCase(downloadRepositoryProvider.get());
  }

  public static InsertDownloadUseCase_Factory create(
      Provider<DownloadRepository> downloadRepositoryProvider) {
    return new InsertDownloadUseCase_Factory(downloadRepositoryProvider);
  }

  public static InsertDownloadUseCase newInsertDownloadUseCase(
      DownloadRepository downloadRepository) {
    return new InsertDownloadUseCase(downloadRepository);
  }
}
