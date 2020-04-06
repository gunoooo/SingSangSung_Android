package com.gunwoo.karaoke.domain.usecase.record;

import com.gunwoo.karaoke.domain.repository.RecordRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetRecordListUseCase_Factory implements Factory<GetRecordListUseCase> {
  private final Provider<RecordRepository> recordRepositoryProvider;

  public GetRecordListUseCase_Factory(Provider<RecordRepository> recordRepositoryProvider) {
    this.recordRepositoryProvider = recordRepositoryProvider;
  }

  @Override
  public GetRecordListUseCase get() {
    return new GetRecordListUseCase(recordRepositoryProvider.get());
  }

  public static GetRecordListUseCase_Factory create(
      Provider<RecordRepository> recordRepositoryProvider) {
    return new GetRecordListUseCase_Factory(recordRepositoryProvider);
  }

  public static GetRecordListUseCase newGetRecordListUseCase(RecordRepository recordRepository) {
    return new GetRecordListUseCase(recordRepository);
  }
}
