package com.gunwoo.karaoke.domain.usecase;

import com.gunwoo.karaoke.domain.repository.RecentRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetRecentListUseCase_Factory implements Factory<GetRecentListUseCase> {
  private final Provider<RecentRepository> recentRepositoryProvider;

  public GetRecentListUseCase_Factory(Provider<RecentRepository> recentRepositoryProvider) {
    this.recentRepositoryProvider = recentRepositoryProvider;
  }

  @Override
  public GetRecentListUseCase get() {
    return new GetRecentListUseCase(recentRepositoryProvider.get());
  }

  public static GetRecentListUseCase_Factory create(
      Provider<RecentRepository> recentRepositoryProvider) {
    return new GetRecentListUseCase_Factory(recentRepositoryProvider);
  }

  public static GetRecentListUseCase newGetRecentListUseCase(RecentRepository recentRepository) {
    return new GetRecentListUseCase(recentRepository);
  }
}
