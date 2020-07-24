package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Optional;
import java.util.stream.Stream;

public interface DataSourceServiceConfiguration extends ServiceConfiguration {

    // TODO: Добавить опциональную обертку инициализатора - прокси, который инициализируетт датасорс только после первого обращения к нему
    Stream<DataSource<?, ?>> getDataSources();

    Optional<DataSource<?, ?>> getDefaultDataSource();

}
