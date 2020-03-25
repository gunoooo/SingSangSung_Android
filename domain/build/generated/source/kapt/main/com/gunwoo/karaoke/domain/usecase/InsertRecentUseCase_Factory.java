package com.gunwoo.karaoke.domain.usecase;

import com.gunwoo.karaoke.domain.repository.RecentRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class InsertRecentUseCase_Factory implements Factory<InsertRecentUseCase> {
  private final Provider<RecentRepository> recentRepositoryProvider;

  public InsertRecentUseCase_Factory(Provider<RecentRepository> recentRepositoryProvider) {
    this.recentRepositoryProvider = recentRepositoryProvider;
  }

  @Override
  public InsertRecentUseCase get() {
    return new InsertRecentUseCase(recentRepositoryProvider.get());
  }

  public static InsertRecentUseCase_Factory create(
      Provider<RecentRepository> recentRepositoryProvider) {
    return new InsertRecentUseCase_Factory(recentRepositoryProvider);
  }

  public static InsertRecentUseCase newInsertRecentUseCase(RecentRepository recentRepository) {
    return new InsertRecentUseCase(recentRepository);
  }
}
