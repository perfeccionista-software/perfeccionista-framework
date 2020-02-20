package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Optional;
import java.util.stream.Stream;

public interface DataSourceServiceConfiguration extends ServiceConfiguration {

    Stream<DataSource<?, ?>> getDataSources();

    Optional<DataSource<?, ?>> getDefaultDataSource();

}
