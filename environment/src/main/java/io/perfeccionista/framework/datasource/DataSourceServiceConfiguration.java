package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Map;
import java.util.Optional;

public interface DataSourceServiceConfiguration extends ServiceConfiguration {

    Optional<DataSource<?, ?>> getDefaultDataSource(Map<String, DataSource<?, ?>> dataSources);

    Map<String, DataSource<?, ?>> getNamedDataSources();

}
