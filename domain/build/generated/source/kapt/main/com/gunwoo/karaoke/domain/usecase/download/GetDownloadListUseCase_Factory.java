package com.gunwoo.karaoke.domain.usecase.download;

import com.gunwoo.karaoke.domain.repository.DownloadRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetDownloadListUseCase_Factory implements Factory<GetDownloadListUseCase> {
  private final Provider<DownloadRepository> downloadRepositoryProvider;

  public GetDownloadListUseCase_Factory(Provider<DownloadRepository> downloadRepositoryProvider) {
    this.downloadRepositoryProvider = downloadRepositoryProvider;
  }

  @Override
  public GetDownloadListUseCase get() {
    return new GetDownloadListUseCase(downloadRepositoryProvider.get());
  }

  public static GetDownloadListUseCase_Factory create(
      Provider<DownloadRepository> downloadRepositoryProvider) {
    return new GetDownloadListUseCase_Factory(downloadRepositoryProvider);
  }

  public static GetDownloadListUseCase newGetDownloadListUseCase(
      DownloadRepository downloadRepository) {
    return new GetDownloadListUseCase(downloadRepository);
  }
}
