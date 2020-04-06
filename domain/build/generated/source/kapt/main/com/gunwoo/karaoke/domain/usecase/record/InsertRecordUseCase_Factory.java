package com.gunwoo.karaoke.domain.usecase.record;

import com.gunwoo.karaoke.domain.repository.RecordRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class InsertRecordUseCase_Factory implements Factory<InsertRecordUseCase> {
  private final Provider<RecordRepository> recordRepositoryProvider;

  public InsertRecordUseCase_Factory(Provider<RecordRepository> recordRepositoryProvider) {
    this.recordRepositoryProvider = recordRepositoryProvider;
  }

  @Override
  public InsertRecordUseCase get() {
    return new InsertRecordUseCase(recordRepositoryProvider.get());
  }

  public static InsertRecordUseCase_Factory create(
      Provider<RecordRepository> recordRepositoryProvider) {
    return new InsertRecordUseCase_Factory(recordRepositoryProvider);
  }

  public static InsertRecordUseCase newInsertRecordUseCase(RecordRepository recordRepository) {
    return new InsertRecordUseCase(recordRepository);
  }
}
