package com.gunwoo.karaoke.domain.usecase.extract;

import com.gunwoo.karaoke.domain.repository.ExtractRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ExtractUseCase_Factory implements Factory<ExtractUseCase> {
  private final Provider<ExtractRepository> extractRepositoryProvider;

  public ExtractUseCase_Factory(Provider<ExtractRepository> extractRepositoryProvider) {
    this.extractRepositoryProvider = extractRepositoryProvider;
  }

  @Override
  public ExtractUseCase get() {
    return new ExtractUseCase(extractRepositoryProvider.get());
  }

  public static ExtractUseCase_Factory create(
      Provider<ExtractRepository> extractRepositoryProvider) {
    return new ExtractUseCase_Factory(extractRepositoryProvider);
  }

  public static ExtractUseCase newExtractUseCase(ExtractRepository extractRepository) {
    return new ExtractUseCase(extractRepository);
  }
}
